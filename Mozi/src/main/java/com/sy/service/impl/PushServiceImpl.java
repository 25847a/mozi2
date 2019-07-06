package com.sy.service.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sy.common.JpushClientUtil;
import com.sy.mapper.MessageMapper;
import com.sy.mapper.PushMapper;
import com.sy.mapper.PushRecordMapper;
import com.sy.pojo.JfhealthNew;
import com.sy.pojo.Message;
import com.sy.pojo.Push;
import com.sy.pojo.PushRecord;
import com.sy.pojo.User;
import com.sy.service.PushRecordService;
import com.sy.service.PushService;
import com.sy.service.UseravatarService;

@Service
public class PushServiceImpl extends ServiceImpl<PushMapper, Push> implements PushService {

	private final static Logger logger = LoggerFactory.getLogger(PushServiceImpl.class);

	@Autowired
	private PushMapper pushMapper;
	@Autowired
	PushRecordService pushRecordService;
	@Autowired
	private PushRecordMapper pushRecordMapper;
	@Autowired
	MessageMapper messageMapper;
	@Autowired
	UseravatarService useravatarService;

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
		List<Push> pushList = pushMapper.selectPushList(u.getId());// 这个使用者的所有开关数据,使用者的ID
		//userId  使用者 ; alias 监护者
		if (pushList != null && pushList.size() > 0) {
			for (Push push : pushList) {
				if (push.getAllNotifyOn()) {// 总开关true开着
					PushRecord pushRecord = new PushRecord();
					Message me = new Message();//消息中心
					pushRecord.setUserId(push.getUserId());
					if (jfhealth.getHeartrate() < push.getHeartLowThd() || jfhealth.getHeartrate() > push.getHeartHigThd()) {
						// 心率低了 或者 心率高了
						JpushClientUtil.sendToAlias(push.getAlias().toString(), u.getName() + "的心率异常","当前心率" + jfhealth.getHeartrate(),
								"已经超过正常范围值" + push.getHeartLowThd() + "-" + push.getHeartHigThd(), "2","{\"userId\":\""+u.getId()+"\"}");
						str = "1";
						pushRecord.setHeartUnusual(jfhealth.getHeartrate());
						me.setAlias(push.getAlias());
						me.setTitle("预警通知");
						me.setContent("u.getName()  心率异常    "+jfhealth.getHeartrate()+"次/分钟");
						messageMapper.insert(me);
					}
					// 舒张压低了 或者 舒张压高了
					if (jfhealth.getDbpAve() < push.getLbpstart() || jfhealth.getDbpAve() > push.getLbpend()) {
						JpushClientUtil.sendToAlias(push.getAlias().toString(), u.getName() + "血压异常","当前舒张压" + jfhealth.getDbpAve(),
										"已经超过预警范围值:" + push.getLbpstart() + "-" + push.getLbpend(), "3","{\"userId\":\""+u.getId()+"\"}");
						str = "1";
						pushRecord.setHighBloodUnusual(jfhealth.getDbpAve());
						me.setAlias(push.getAlias());
						me.setTitle("预警通知");
						me.setContent("u.getName()  舒张压异常    "+jfhealth.getDbpAve()+"mmHg");
						messageMapper.insert(me);
					}
					// 收缩压低了 或者 收缩压高了
					if (jfhealth.getSbpAve() < push.getHbpstart() || jfhealth.getSbpAve() > push.getHbpend()) {
						JpushClientUtil.sendToAlias(push.getAlias().toString(), u.getName() + "血压异常","当前收缩压" + jfhealth.getSbpAve(),
										"已经超过预警范围值" + push.getHbpstart() + "-" + push.getHbpend(), "3","{\"userId\":\""+u.getId()+"\"}");
						str = "1";
						pushRecord.setLowBloodUnusual(jfhealth.getSbpAve());
						me.setAlias(push.getAlias());
						me.setTitle("预警通知");
						me.setContent("u.getName()  收缩压异常    "+jfhealth.getHeartrate()+"mmHg");
						messageMapper.insert(me);
					}
					if(pushRecord.getHeartUnusual()!=null || pushRecord.getHighBloodUnusual()!=null || pushRecord.getLowBloodUnusual()!=null){
						if (pushRecordMapper == null) {
							WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
							pushRecordMapper = (PushRecordMapper) webApplicationContext.getBean("pushRecordMapper");
						}
						pushRecordMapper.insertPushRecord(pushRecord);
					}
				} else {
					logger.info(u.getName() + "该用户没有设置推送开关");
				}
			}
		}
		return str;
	}

}
