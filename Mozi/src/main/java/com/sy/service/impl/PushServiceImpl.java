package com.sy.service.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.sy.common.JpushClientUtil;
import com.sy.mapper.PushMapper;
import com.sy.pojo.JfhealthNew;
import com.sy.pojo.Push;
import com.sy.pojo.User;
import com.sy.service.PushService;

@Service
public class PushServiceImpl implements PushService {

	@Autowired
	private PushMapper pushMapper;

	/**
	 * 查询推送表
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ParseException
	 */
	@Override
	public Push selectPush(Map<String, Object> data) throws SQLException {
		Push push = pushMapper.selectPush(data);
		return push;
	}

	/**
	 * 修改推送表
	 * 
	 * @param date
	 * @return
	 * @throws SQLException
	 */
	public int updatePushById(Map<String, Object> map) throws SQLException {
		Push push = pushMapper.selectPush(map);
		int row = 0;
		if (push == null) {
			map.put("createTime", new Date());
			row = pushMapper.addPushMap(map);
		} else {
			row = pushMapper.updatePushById(map);
		}
		return row;
	}

	/**
	 * 推送消息
	 * 
	 * @param map
	 *            alias(使用者ID)
	 * @throws SQLException
	 */
	@Override
	public String queryPushNews(Map<String, Object> map, JfhealthNew jfhealth) throws SQLException {
		String str = "0";
		if (pushMapper == null) {
			WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
			pushMapper = (PushMapper) webApplicationContext.getBean("pushMapper");
		}
		User u = (User) map.get("user");
		List<Push> pushList = pushMapper.selectPushList(u.getId());// 这个使用者的所有开关数据
		//userId  使用者 ; alias 监护者
		if (pushList != null && pushList.size() > 0) {
			for (Push push : pushList) {
				
				if (push.getAllNotifyOn()) {// 总开关true开着
					
					if (jfhealth.getHeartrate() < push.getHeartLowThd()
							|| jfhealth.getHeartrate() > push.getHeartHigThd()) {
						// 心率低了 或者 心率高了
						Thread t = new Thread() {
							public void run() {
								JpushClientUtil.sendToAlias(push.getAlias().toString(), u.getName() + "的心率异常",
										"当前心率" + jfhealth.getHeartrate(),
										"已经超过正常范围值" + push.getHeartLowThd() + "-" + push.getHeartHigThd(), "2",
										"{\"userId\":\""+u.getId()+"\"}");
							}
						};
						str = "1";
						t.start();
					}
					
					// 舒张压低了 或者 舒张压高了
					if (jfhealth.getDbpAve() < push.getLbpstart() || jfhealth.getDbpAve() > push.getLbpend()) {

						Thread t = new Thread() {
							public void run() {
								JpushClientUtil.sendToAlias(push.getAlias().toString(), u.getName() + "血压异常",
										"当前舒张压" + jfhealth.getDbpAve(),
										"已经超过预警范围值:" + push.getLbpstart() + "-" + push.getLbpend(), "3",
										"{\"userId\":\""+u.getId()+"\"}");
							}
						};
						str = "1";
						t.start();
					}
			
					// 收缩压低了 或者 收缩压高了
					if (jfhealth.getSbpAve() < push.getHbpstart() || jfhealth.getSbpAve() > push.getHbpend()) {

						Thread t = new Thread() {
							public void run() {
								JpushClientUtil.sendToAlias(push.getAlias().toString(), u.getName() + "血压异常",
										"当前收缩压" + jfhealth.getSbpAve(),
										"已经超过预警范围值" + push.getHbpstart() + "-" + push.getHbpend(), "3",
										"{\"userId\":\""+u.getId()+"\"}");
							}
						};
						str = "1";
						t.start();
					}
				} else {
					System.out.println(u.getName() + "该用户没有设置推送开关");
				}
			}
		}
		return str;
	}

}
