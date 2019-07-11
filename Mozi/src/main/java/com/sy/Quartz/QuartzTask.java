package com.sy.Quartz;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.sy.pojo.Equipment;
import com.sy.pojo.GroupPhone;
import com.sy.pojo.User;
import com.sy.service.EquipmentService;
import com.sy.service.GroupPhoneService;
import com.sy.service.UserService;
import com.sy.service.impl.EquipmentServiceImpl;
import com.sy.service.impl.GroupPhoneServiceImpl;
import com.sy.service.impl.UserServiceImpl;

/**
 * 判断设备是否在线
 * 
 * @author Administrator
 *
 */

@Component
public class QuartzTask {

	private static Integer i = 1;
	private static GroupPhoneService groupPhoneservice = new GroupPhoneServiceImpl();
	private static EquipmentService equipmentservice = new EquipmentServiceImpl();
	private static UserService userService = new UserServiceImpl();
	@Scheduled(fixedDelay = 180000)
	protected void executeInternal() {
		try {
			if (i == 0) {
                 List<Equipment> es = (List<Equipment>)QuartzTask.equipmentservice.selectequipment();
                for (Equipment e : es) {
                     User user = QuartzTask.userService.getUser(e.getImei());
                    if (user != null) {
                        long d1 = new Date().getTime();
                         long d2 = e.getUpdatetime().getTime();
                        if (d1 - d2 >= Integer.valueOf(user.getJfdataUpdateTime()) * 1000 * 60) {
                        	e.setEqStatus("H:0");
                            e.setLordpower(Integer.valueOf(0));
                            e.setBluetoothStatus("0");
                            QuartzTask.equipmentservice.updatEequipmentst(e);
                        }
                    }
                }
            } else {
				i = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//@Scheduled(fixedDelay = 1800000)
	protected void batchUpdate() {

		try {
			//更新激活
			groupPhoneservice.updateStatus();
			Thread.sleep(3000);
			//更新号码语音使用情况
			groupPhoneservice.batchUpdate();
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//查询已经激活的号码列表
		List<GroupPhone> groupPhonelist = groupPhoneservice.selectActivateList();
		
		//需要办理停机的列表
		List<GroupPhone> suspendedlist = new ArrayList<>();
		
		//有需要更新的列表
		List<GroupPhone> updatelist = new ArrayList<>();
		
			for (GroupPhone groupPhone : groupPhonelist) {
				
				//已经扣费的套餐外语音
				String lastVoicestr = groupPhone.getLastVoice();
				if(lastVoicestr==null){
					lastVoicestr = "0.00";
				}
				BigDecimal lastVoice = new BigDecimal(lastVoicestr);
				
				//总共套餐外的语音
				BigDecimal extraPkgVoice = new BigDecimal(groupPhone.getExtraPkgVoice());
				
				//本次需要扣费的套餐外语音 = 总的套餐外语音 - 已经扣费的套餐外语音
				BigDecimal calculateVoice = extraPkgVoice.subtract(lastVoice);
				
				
				//本次需要扣费的套餐外语音大于0
				if(calculateVoice.intValue()>0){
					
					//单价  语音为0.39元/分钟
					BigDecimal price = new BigDecimal("0.39");
					
					//余额
					String balancestr = groupPhone.getBalance();
					if(!StringUtils.isNotBlank(balancestr.trim())){
						balancestr = "0.00";
					}
					BigDecimal balance = new BigDecimal(balancestr);
					
					
					//需要扣除的金额
					BigDecimal money = calculateVoice.multiply(price);
				
					//余额  减掉 需要扣除的金额  等于剩下的 余额
					Double b = balance.subtract(money).doubleValue();
					
					
					
					groupPhone.setBalance(b.toString()); //余额
					
					//已经扣费的套餐外语音 = 总的套餐外语音   = 本次需要扣费的语音+原先已经扣费的语音
					groupPhone.setLastVoice(groupPhone.getExtraPkgVoice()); 
					
					//用户已经使用的金额
					String usedMoney = groupPhone.getUsedMoney();
					if(usedMoney==null){
						usedMoney="0.00";
						}
					//已经使用的金额+本次该扣除的金额等 = 新的已经使用的金额
					groupPhone.setUsedMoney(money.add(new BigDecimal(usedMoney)).toString());
					
					
					//如果余额小于0
					if(b<=0){
						//设置为停机
						groupPhone.setSuspended("0");
						//添加到停机列表
						suspendedlist.add(groupPhone);
					}
					updatelist.add(groupPhone);
				}
			}
			
			//需要停机的列表
			if(suspendedlist.size()>0){
				System.out.println("停机"+suspendedlist.size()+"个");
				for (GroupPhone g : updatelist) {
					//通知移动需要办理停机的手机号码
					groupPhoneservice.suspended(g.getPhone());
				}
			}
			//更新到数据库
			groupPhoneservice.updateList(updatelist);
		}


}
