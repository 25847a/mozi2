package com.sy.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sy.common.ResultBase;
import com.sy.common.ResultData;
import com.sy.mapper.EquipmentMapper;
import com.sy.mapper.SensorstatusMapper;
import com.sy.mapper.UserMapper;
import com.sy.nettyulit.BluetoothMap;
import com.sy.nettyulit.NettyChannelMap;
import com.sy.pojo.Equipment;
import com.sy.pojo.Sensorstatus;
import com.sy.pojo.User;
import com.sy.service.EquipmentService;
import com.sy.utils.DataRow;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.socket.SocketChannel;

@Service
public class EquipmentServiceImpl extends ServiceImpl<EquipmentMapper, Equipment> implements EquipmentService {
	@Autowired
	private EquipmentMapper equipmentMapper;
	@Autowired
	private SensorstatusMapper sensorstatusMapper;
	@Autowired
	UserMapper userMapper;
	/**
	 * 获取设备基本信息
	 * 
	 * @param m
	 * @return
	 */
	public ResultData<DataRow> selectdata(DataRow map, ResultData<DataRow> re) throws Exception {
		// 获取到设备基本信息
		Equipment e = equipmentMapper.selectById(map.getInt("eqId"));
		if (e != null) {
			map.put("eqId", e.getId());
			map.put("eqtype", e.getEqtype());
			map.put("eqStatus", e.getEqStatus().equals("H:0") ? 0 : 1);
			map.put("signalxhao", e.getSignalxhao() + "%");
			map.put("lordpower", e.getLordpower() + "%");
			map.put("bluetoothType", Integer.valueOf(e.getBluetoothType()));
			map.put("imei", e.getImei());
			map.put("version", e.getVersion());
			// 获取imei设备最新的传感器状态
			Sensorstatus sen = sensorstatusMapper.selecttimesensorstatus(e.getImei());
			if(sen!=null){
				map.put("H", sen.getH().equals("H:1") ? "正常" : "错误");
				map.put("G", sen.getG().equals("G:1") ? "正常" : "错误");
			}else{
				sen =new Sensorstatus();
				sen.setG("G:1");
				sen.setH("H:1");
				sen.setImei(e.getImei());
				sen.setAdddate(new Date());
				sensorstatusMapper.insert(sen);
				map.put("H", sen.getH().equals("H:1") ? "正常" : "错误");
				map.put("G", sen.getG().equals("G:1") ? "正常" : "错误");
			}
			
			re.setCode(200);
			re.setData(map);
			re.setMessage("获取设备基本信息成功");
		} else {
			re.setCode(400);
			re.setMessage("获取不到设备基本信息");
		}
		return re;
	}
	/**
	 * 修改设备紧急联系人
	 * 
	 * @return
	 */
	@Override
	public ResultBase updateurgentInfo(Equipment equipment, ResultBase re) throws Exception {
		EntityWrapper<Equipment> ew = new EntityWrapper<Equipment>();
		ew.eq("imei", equipment.getImei());
		int row =equipmentMapper.update(equipment, ew);
		equipment = this.selectOne(ew);
		String[] phone1 = null;
		String[] phone2 = null;
		if(row>0){
			if (equipment.getPhone1() != null && !equipment.getPhone1().equals("")) {
				 phone1 = equipment.getPhone1().split(",");
			} else {
				phone1=new String[2];
				phone1[0]="0";
				phone1[1]="0";
			}

			if (equipment.getPhone2() != null && !equipment.getPhone2().equals("")) {
				 phone2 = equipment.getPhone2().split(",");
			} else {
				phone2=new String[2];
				phone2[0]="0";
				phone2[1]="0";
			}

				re.setCode(200);
				re.setMessage("修改紧急联系成功!!!");
					Channel c = NettyChannelMap.get(equipment.getImei());
					c.writeAndFlush("$R24|" + phone1[1] + ","
							+ getCode(phone1[0]) + ":" + phone1[1] + ","
							+ getCode(phone2[0]) + "\r\n");
			re.setCode(200);
			re.setMessage("修改成功");
		}else{
			re.setCode(400);
			re.setMessage("修改失败");
		}
		return re;
	}
	/**
	 * 修改设备紧急联系人
	 * @return
	 */
	public ResultBase updateurgent(DataRow map,ResultBase re)throws Exception{
		String phone1 = map.getString("phone1");
		String phone1name =map.getString("phone1name");
		String phone2name =map.getString("phone2name");
		String phone2 =map.getString("phone2");
		EntityWrapper<Equipment> ew = new EntityWrapper<Equipment>();
		ew.eq("imei", map.getString("imei"));
		Equipment e =this.selectOne(ew);
		if (e != null) {
			if (phone1 != null && !phone1.equals("") && phone1name != null && !phone1name.equals("")) {
				e.setPhone1(phone1name + "," + phone1);
			} else {
				e.setPhone1(0 + "," + 0);
			}

			if (phone2 != null && !phone2.equals("") && phone2name != null && !phone2name.equals("")) {
				e.setPhone2(phone2name + "," + phone2);
			} else {
				e.setPhone2(0 + "," + 0);
			}

			int st =equipmentMapper.updateById(e);
			if (st>0) {
				re.setCode(200);
				re.setMessage("修改紧急联系成功!!!");
					Channel c = NettyChannelMap.get(e.getImei());
					c.writeAndFlush("$R24|" + e.getPhone1().split(",")[1] + ","
							+ getCode(e.getPhone1().split(",")[0]) + ":" + e.getPhone2().split(",")[1] + ","
							+ getCode(e.getPhone2().split(",")[0]) + "\r\n");
			} else {
				re.setCode(350);
				re.setMessage("修改紧急联系异常!!!");
			}
		} else {
			re.setCode(350);
			re.setMessage("该设备不存在!!!");
		}
		return re;
		
	}
	/**
	 * 修改设备紧急联系人调用的方法
	 * @param content
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getCode(String content) throws UnsupportedEncodingException {
		byte[] bytes = content.getBytes("gb2312");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toHexString(bytes[i] & 0xff).toUpperCase() + "");
		}

		return sb.toString();
	}
	/**
	 * 获取智能服饰信息
	 * 
	 * @return
	 */
	@Override
	public ResultData<DataRow> queryClothesInfo(DataRow map, ResultData<DataRow> re) throws Exception {
		Equipment equipment = equipmentMapper.selectById(map.getInt("eqId"));
		List<DataRow> list = new ArrayList<DataRow>();
		if (equipment != null) {
			if (equipment.getBluetoothList() != null && !equipment.getBluetoothList().equals("[]")
					&& !equipment.getBluetoothList().equals("")) {
				String bluetooths = equipment.getBluetoothList().substring(1, equipment.getBluetoothList().length() - 1)
						.replace("\"", "");
				String[] bluetoothList = bluetooths.split(",");
				for (String bluetooth : bluetoothList) {
					if (bluetooth.equals(equipment.getBluetoothName())) {
						map = new DataRow();
						map.put("BluetoothName", equipment.getBluetoothName());
						map.put("bluetoothType", Integer.valueOf(equipment.getBluetoothType()));
					} else {
						map = new DataRow();
						map.put("BluetoothName", bluetooth);
						map.put("bluetoothType", 0);
					}
					list.add(map);
				}
				re.setCode(200);
				re.setData(list);
				re.setMessage("获取智能服饰成功");
			} else {
				re.setCode(350);
				re.setMessage("没有绑定保存智能服饰");
			}
		} else {
			re.setCode(400);
			re.setMessage("查询不到该设备信息");
		}
		return re;
	}
	/**
	 * 连接蓝牙
	 * 
	 * @return
	 */
	public ResultBase updatebluetooth(Equipment equipment, ResultBase re) throws Exception {
		Equipment e = equipmentMapper.selectById(equipment.getId());
		BluetoothMap.deletesb(e.getImei());
		SocketChannel c = (SocketChannel) NettyChannelMap.get(e.getImei());
		System.out.println(e.getBluetoothType());
		System.out.println(equipment.getBluetoothmac());
		System.out.println(equipment.getBluetoothName());
		ChannelFuture writeAndFlush = c.writeAndFlush("$R18|" + e.getBluetoothType() + ":"
				+ equipment.getBluetoothName() + ":" + equipment.getBluetoothmac() + "\r\n");
		writeAndFlush.addListener(new ChannelFutureListener() {
			public void operationComplete(ChannelFuture future) {
				System.out.println("发送成功,连接设备,开始操作子设备");
			}
		});
		// 如果是切换蓝牙,就先更新为0,因为主控会断开蓝牙,防止新蓝牙连接不上出现不同步问题
		Equipment eq = new Equipment();
		if (!e.getBluetoothmac().equals("000000000000")) {
			eq.setId(e.getId());
			eq.setBluetoothName("000000000000");
			eq.setBluetoothStatus("0");
			eq.setBluetoothType("0");
			eq.setBluetoothmac("000000000000");
			equipmentMapper.updateById(eq);
		}
		boolean st;
		int ss = 0;
		while (true) {
			Thread.sleep(1000);
			st = task(e.getImei());
			if (st) {
				re.setMessage("操作成功！！！");
				re.setCode(200);
				break;
			} else {
				if (ss == 40) {
					re.setCode(601);
					re.setMessage("操作失败！！！");
					return re;
				}
				ss++;
			}
		}
		return re;
	}
	/**
	 * 连接蓝牙调用的方法
	 * 
	 * @return
	 */
	public static boolean task(String newimei) {
		boolean st = false;

		String msg = BluetoothMap.getBs(newimei);
		System.out.println("获取的imei" + newimei + ">>>>>>>>>>>>>>>>" + msg);
		if (msg != null) {
			if (msg.contains("T08") || msg.contains("R18|OK")) {
				st = true;
			}
			if (msg.contains("R18|ERR1")) {
				st = false;
			}
		}
		BluetoothMap.deletesb(newimei);
		return st;
	}
	/**
	 * 更新蓝牙列表
	 * 
	 * @param bluetoothList
	 * @return
	 */
	@Override
	public ResultBase updateBluetoothList(DataRow map,ResultBase re) {
		Equipment equipment = equipmentMapper.selectById(map.getInt("eqId"));
		if(equipment!=null){
			String bluetoothList = map.getString("bluetoothList");
			if(equipment.getBluetoothList() != null && !equipment.getBluetoothList().equals("[]") && !equipment.getBluetoothList().equals("")){//整个两件衣服
				String bluetoothA = equipment.getBluetoothList().substring(1,equipment.getBluetoothList().length()-1).replace("\"", "");
				bluetoothList = "[\""+bluetoothA+"\",\""+bluetoothList+"\"]";
			}else{//整合一件衣服
				bluetoothList = "[\""+bluetoothList+"\"]";
			}
			equipment.setBluetoothList(bluetoothList);
			equipmentMapper.updateById(equipment);
			SocketChannel c = (SocketChannel) NettyChannelMap.get(equipment.getImei());
			if (c != null) {
				
				if (StringUtils.isNotBlank(bluetoothList)) {
					bluetoothList = bluetoothList.substring(1, bluetoothList.length() - 1).replace("\"", "");
				} else {
					bluetoothList = "";
				}
				c.writeAndFlush("$R28|" + bluetoothList + "\r\n");
				re.setCode(200);
				re.setMessage("操作成功");
			} else {
				re.setCode(200);
				re.setMessage("操作成功!");
			}
		}else{
			re.setCode(400);
			re.setMessage("查询不到设备");
		}
		
		return re;
	}
	/**
	 * 删除蓝牙
	 * @param bluetoothList
	 * @return
	 */
	public ResultBase deleteBluetoothList(DataRow map,ResultBase re)throws Exception{
		Equipment equipment = equipmentMapper.selectById(map.getInt("eqId"));
		if(equipment!=null){
			String bluetooth = map.getString("bluetoothList");
			if(equipment.getBluetoothList() != null && !equipment.getBluetoothList().equals("[]") && !equipment.getBluetoothList().equals("")){// 证明有衣服
				String bluetoothA = equipment.getBluetoothList().substring(1,equipment.getBluetoothList().length()-1).replace("\"", "");
				String[] bluetoothList = bluetoothA.split(",");
				if(bluetoothList.length==1){
					bluetooth = "[]";
				}else{
					if(bluetooth.equals(bluetoothList[0])){
						bluetooth = "[\""+bluetoothList[1]+"\"]";
					}else{
						bluetooth = "[\""+bluetoothList[0]+"\"]";
					}
				}
			}
			equipment.setBluetoothList(bluetooth);
			equipmentMapper.updateById(equipment);
			SocketChannel c = (SocketChannel) NettyChannelMap.get(equipment.getImei());
			if (c != null) {
				
				if (StringUtils.isNotBlank(bluetooth)) {
					bluetooth = bluetooth.substring(1, bluetooth.length() - 1).replace("\"", "");
				} else {
					bluetooth = "";
				}
				c.writeAndFlush("$R28|" + bluetooth + "\r\n");
				re.setCode(200);
				re.setMessage("操作成功");
			} else {
				re.setCode(200);
				re.setMessage("操作成功!");
			}
		}else{
			re.setCode(400);
			re.setMessage("查询不到设备");
		}
		return re;
		
	}
	/**
	 * 发送学习指令
	 * @param map
	 * @return
	 */
	public ResultBase healthcali(DataRow map,ResultBase re)throws Exception{
		String imei = map.getString("imei");
		EntityWrapper<Equipment> ew = new EntityWrapper<Equipment>();
		ew.eq("imei", imei);
		Equipment e =this.selectOne(ew);
		System.out.println(imei + "=====imei ");
		if (e != null) {
			String bluetoothType = e.getBluetoothType();
			if (bluetoothType!=null&& bluetoothType.equals("1")) {
				BluetoothMap.deletehealthmap(imei);
				Channel c = NettyChannelMap.get(imei);
				if (c != null) {
					ChannelFuture writeAndFlush = c.writeAndFlush("$R17\r\n");
					writeAndFlush.addListener(new ChannelFutureListener() {
						public void operationComplete(ChannelFuture future) {
							System.out.println("发送成功,开始学习");
						}
					});
					boolean st = taskhealthcali(imei);
					if (st) {
						User user = userMapper.getUser(imei);
						user.setCalibration(1);
						userMapper.updateCalibration(user);
						re.setCode(200);
						re.setMessage("学习完成！！！");
					} else {
						re.setCode(602);
						re.setMessage("学习失败！！！");
					}
				} else {
					System.out.println(imei+"=====不在线");
					re.setCode(602);
					re.setMessage("设备不在线");
				}
			} else {
				System.out.println(imei+ ">>>>>学习失败>>>>>>bluetoothType===="+bluetoothType);
				re.setCode(602);
				re.setMessage("没有衣服连接");
			}
		} else {
			re.setCode(602);
			re.setMessage("imei号错误");
		}
		return re;
	}
	/**
	 * 发送学习指令用到的方法
	 * @throws Exception
	 */
	public static boolean taskhealthcali(String imei) throws Exception {
		boolean st = false;
		int i = 0;
		while (true) {
			Thread.sleep(1000);
			String msg = BluetoothMap.gethealthmap(imei);
			if (msg != null && msg.contains("A")) {
				st = true;
				System.out.println("设备号<" + imei + ">健康数据学习成功=======================" + msg);
				break;
			} else if (msg != null && msg.contains("b")) {
				System.out.println("设备号<" + imei + ">学习失败,返回的数据解析失败======");
				break;
			} else {
				if (i == 52) {
					System.out.println("设备号<" + imei + ">学习失败,学习超时======");
					break;
				}
				i++;
			}
		}
		BluetoothMap.deletehealthmap(imei);
		return st;

	}
	/**
	 * 更新设备基本数据
	 * 
	 * @param e
	 * @return
	 */
	@Override
	public boolean updateEquipment(Equipment e) {
		e.setUpdatetime(new Date());
		if (equipmentMapper == null) {
			WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
			equipmentMapper = (EquipmentMapper) webApplicationContext.getBean("equipmentMapper");
		}
		Integer num = equipmentMapper.updateById(e);
		if (num != 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 根据imei获取数据
	 * 
	 * @param imei
	 * @return
	 */
	@Override
	public Equipment selectquipmentimei(String imei) {
		if (equipmentMapper == null) {
			WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
			equipmentMapper = (EquipmentMapper) webApplicationContext.getBean("equipmentMapper");
		}
		Equipment eq = new Equipment();
		try {
			eq =equipmentMapper.getequipment(imei);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return eq;
	}

	/**
	 * 获取所有设备数据
	 * 
	 * @return
	 */
	@Override
	public List<Equipment> selectequipment() {
		if (equipmentMapper == null) {
			WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
			equipmentMapper = (EquipmentMapper) webApplicationContext.getBean("equipmentMapper");
		}
		return equipmentMapper.selectList(null);
	}

	/**
	 * 更新设备
	 * 
	 * @param eq
	 */
	@Override
	public void updatEequipmentst(Equipment e) {
		if (equipmentMapper == null) {
			WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
			equipmentMapper = (EquipmentMapper) webApplicationContext.getBean("equipmentMapper");
		}
		equipmentMapper.updateById(e);
	}

	/**
	 * 添加设备
	 * 
	 * @param e
	 * @return
	 */
	@Override
	public boolean addEquipment(Equipment e) {
		Integer num = equipmentMapper.insert(e);
		if (num != 0) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 硬件上传数据用到的
	 * 
	 * @return
	 */
	public Equipment equipmentstatus(String eqStatus, Integer eqtype, String imei, Integer lordpower, String signalxhao,
			String version) {
		if (equipmentMapper == null) {
			WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
			equipmentMapper = (EquipmentMapper) webApplicationContext.getBean("equipmentMapper");
		}
		Equipment e = equipmentMapper.getequipment(imei);
		if (signalxhao != null) {
			e.setSignalxhao(signalxhao);
			e.setLordpower(lordpower);
		}
		if (eqStatus != null) {
			e.setEqStatus(eqStatus);
		}
		if (version != null) {
			e.setVersion(version);
		}
		e.setEqtype(eqtype);
		e.setUpdatetime(new Date());
		equipmentMapper.updateById(e);
		return e;
	}

	/**
	 * 修改设备在线
	 * 
	 * @return
	 */
	@Override
	public Equipment updateEqStatus(String eqStatus, String imei, Equipment e) {
		e.setEqStatus(eqStatus);
		e.setUpdatetime(new Date());
		int i =equipmentMapper.updateById(e);
		if(i>0){
			System.out.println("更改成功>>>>>>>>>>>>>>>在线状态"+i);
		}else{
			System.out.println("更改失败>>>>>>>>>>>>>>>>>"+i);
		}
		return e;
	}

	/**
	 * 修改设备在线、设备类型、版本号
	 * 
	 * @return
	 */
	@Override
	public Equipment updateEqStatus(String eqStatus, Integer eqtype, String imei, Integer lordpower, String signalxhao,
			String version, Equipment e) {
		e.setSignalxhao(signalxhao);
		e.setLordpower(lordpower);
		e.setEqStatus(eqStatus);
		e.setVersion(version);
		e.setEqtype(eqtype);
		e.setUpdatetime(new Date());
		equipmentMapper.updateById(e);
		return e;
	}
	

	

}
