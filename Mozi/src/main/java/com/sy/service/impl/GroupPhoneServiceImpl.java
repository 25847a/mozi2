package com.sy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.sy.mapper.EquipmentMapper;
import com.sy.mapper.GroupPhoneMapper;
import com.sy.pojo.GroupPhone;
import com.sy.service.GroupPhoneService;
import com.sy.utils.ApiUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class GroupPhoneServiceImpl implements GroupPhoneService {
	
	
	private Logger logger = LoggerFactory.getLogger(GroupPhoneServiceImpl.class);
	
	@Autowired
	private GroupPhoneMapper groupPhoneMapper;

	/**
	 * 查询激活的列表
	 */
	public List<GroupPhone> selectActivateList() {
		if (groupPhoneMapper == null) {
			WebApplicationContext webApplicationContext = ContextLoader
					.getCurrentWebApplicationContext();
			groupPhoneMapper = (GroupPhoneMapper) webApplicationContext
					.getBean("groupPhoneMapper");
		}
		return groupPhoneMapper.selectActivateList();
	}


	/**
	 * 查询单个手机号语音,流量和余额
	 * 
	 * @param map
	 * @return
	 */
	public GroupPhone selectOne(String phone) {
		// ResultData<String> re = new ResultData<>();
		
		GroupPhone groupPhone = groupPhoneMapper.selectPhone(phone);
		try {
			// 语音
			JSONObject selectVoiceinfo = selectVoiceinfo(phone);

			String voiceData = selectVoiceinfo.getString("data");
			JSONObject voiceObject = JSONObject.fromObject(voiceData);
			groupPhone.setTotalVoice(voiceObject.getString("totalVoice"));
			groupPhone.setUsedVoice(voiceObject.getString("usedVoice"));
			groupPhone.setRestVoice(voiceObject.getString("restVoice"));
			groupPhone.setExtraPkgVoice(voiceObject.getString("extraPkgVoice"));
			// 流量
			JSONObject selectFlow = selectFlow(phone);
			String flowData = selectFlow.getString("data");
			JSONObject flowObject = JSONObject.fromObject(flowData);

			JSONArray jsonArray = flowObject.getJSONArray("apnList");
			JSONObject fromObject = JSONObject.fromObject(jsonArray.get(0));
			groupPhone.setExtraPkgFlow(fromObject.getString("extraPkgFlow"));
			groupPhone.setRestFlow(fromObject.getString("restFlow"));
			groupPhone.setTotalFlow(fromObject.getString("totalFlow"));
			groupPhone.setUsedFlow(fromObject.getString("usedFlow"));

		} catch (Exception e) {
			e.printStackTrace();
			groupPhone = null;
		}
		return groupPhone;
	}

	// 语音
	private JSONObject selectVoiceinfo(String phone) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "triopi.member.voiceinfo.realtime.single.query");
		params.put("msisdn", phone);
		JSONObject fromObject = ApiUtils.doPost(params);
		return fromObject;

	}

	// 流量
	private JSONObject selectFlow(String phone) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "triopi.member.dailyflow.realtime.query");
		params.put("msisdn", phone);
		JSONObject fromObject = ApiUtils.doPost(params);
		return fromObject;
	}
	/**
	 * 更新激活状态
	 * 
	 */
	public void updateStatus() {

		try {
			Map<String, String> paramValues = new HashMap<>();
			List<String> activateList = new ArrayList<>();
			//未激活的号码列表
			List<String> phoneList = selectNoActivateAllPhone();
			StringBuffer sb = new StringBuffer();
			int i = 0;
			for (String phone : phoneList) {
				i++;
				sb.append(phone + ",");
				if (i%100 == 0) {
					//查询激活的号码,add到activateList
					activateList(paramValues, activateList, sb);
					sb.setLength(0);
				} 
			}
			if(sb.length()>0){
				//查询激活的号码,add到activateList
				activateList(paramValues, activateList, sb);
			}
			//激活
			groupPhoneMapper.updateStatus(activateList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void activateList(Map<String,String> paramValues,List<String> activateList,StringBuffer sb){
		paramValues.clear();
		paramValues.put("msisdns", sb.toString());
		paramValues.put("method", "triopi.member.lifecycle.batch.query");
		JSONObject fromObject = ApiUtils.doPost(paramValues);
		JSONArray jsonArray = fromObject.getJSONObject("data").getJSONObject("statusList").getJSONArray("list");
		for (Object object : jsonArray) {
			JSONObject jn = JSONObject.fromObject(object);
			if (jn.getString("status").equals("normal")) {
				activateList.add(jn.getString("msisdn"));
			}
		}
	}

	/**
	 * 查询所有未激活的号码
	 * 
	 * @return
	 */
	public List<String> selectNoActivateAllPhone() {
		List<String> allPhone = groupPhoneMapper.selectNoActivateAllPhone();
		return allPhone;
	}


	/**
	 * 批量更新
	 */
	public int batchUpdate(List<GroupPhone> gplist) {
		return groupPhoneMapper.batchUpdate(gplist);
	}
	
	/**
	 * 批量更新号码的语音使用情况
	 */
	public int batchUpdate() {

		int batchUpdate = 0;
		List<GroupPhone> gplist = new ArrayList<>();
		try {
			// 查询所有已经激活了的手机号.
			List<GroupPhone> list = selectActivateList();
			Map<String, String> paramValues = new HashMap<>();
			StringBuffer sb = new StringBuffer();

			for (int i = 0; i < list.size(); i++) {
				sb.append(list.get(i).getPhone() + ",");
				if ((i+1) % 100 == 0) {
					paramValues.clear();
					paramValues.put("msisdns", sb.toString());
					paramValues.put("method", "triopi.member.voiceinfo.realtime.batch.query");
					JSONObject fromObject = ApiUtils.doPost(paramValues);
					JSONArray jsonArray = JSONObject.fromObject(fromObject.getString("data"))
							.getJSONArray("msisdnInfoList");
					for (Object object : jsonArray) {
						JSONObject params = JSONObject.fromObject(object);
						String extraPkgVoice = params.getString("extraPkgVoice");
						String restVoice = params.getString("restVoice");
						String totalVoice = params.getString("totalVoice");
						String usedVoice = params.getString("usedVoice");
						String phone = params.getString("msisdn");
						GroupPhone gp = new GroupPhone();
						gp.setExtraPkgVoice(extraPkgVoice);
						gp.setRestVoice(restVoice);
						gp.setTotalVoice(totalVoice);
						gp.setUsedVoice(usedVoice);
						gp.setPhone(phone);
						gplist.add(gp);
					}
					sb.setLength(0);
				}
			}
			if (sb.length() > 0) {
				paramValues.clear();
				paramValues.put("msisdns", sb.toString());
				paramValues.put("method", "triopi.member.voiceinfo.realtime.batch.query");
				JSONObject fromObject = ApiUtils.doPost(paramValues);
				JSONArray jsonArray = JSONObject.fromObject(fromObject.getString("data"))
						.getJSONArray("msisdnInfoList");
				for (Object object : jsonArray) {
					JSONObject params = JSONObject.fromObject(object);
					String extraPkgVoice = params.getString("extraPkgVoice");
					String restVoice = params.getString("restVoice");
					String totalVoice = params.getString("totalVoice");
					String usedVoice = params.getString("usedVoice");
					String phone = params.getString("msisdn");
					GroupPhone gp = new GroupPhone();
					gp.setExtraPkgVoice(extraPkgVoice);
					gp.setRestVoice(restVoice);
					gp.setTotalVoice(totalVoice);
					gp.setUsedVoice(usedVoice);
					gp.setPhone(phone);
					gplist.add(gp);
				}
			}
			//更新到数据库
			batchUpdate = batchUpdate(gplist);
			logger.info("更新成功"+batchUpdate+"个");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return batchUpdate;
		
	}
	/**
	 * 查询语音超出的号码
	 * @return
	 */
	public List<GroupPhone> selectExtraPkgVoice(){
		return groupPhoneMapper.selectExtraPkgVoice();
	}
	
	/**
	 * 设置为停机
	 */
	public int batchUpdateSuspended(List<GroupPhone> list){
		return groupPhoneMapper.batchUpdateSuspended(list);
	}


	/**
	 * 更新数据 
	 *   主要更新字段:
	 *	 	balance 余额   
	 * 		usedMoney 已经使用的金额 
	 * 		lastCalculateVoice 已经扣费的超出语音 
	 * 		suspended 停开机状态  
	 */
	public int updateList(List<GroupPhone> updatelist) {
		return groupPhoneMapper.updateList(updatelist);
	}
	
	public void suspended(String phone){
		Map<String, String> paramValues = new HashMap<>();
		paramValues.put("msisdn",phone);
		paramValues.put("method", "triopi.member.voiceinfo.realtime.batch.query");
		paramValues.put("optType","1");
		JSONObject fromObject = ApiUtils.doPost(paramValues);
		if(fromObject.getString("code").equals("0")){
			logger.info(phone+"停机成功");
		}else{
			logger.info(phone+"停机失败");
		}
	}
	
}
