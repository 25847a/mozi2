package com.sy.service.impl;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sy.date.converter.DataSourceType;
import com.sy.date.converter.DynamicDataSourceHolder;
import com.sy.mapper.ChatMapper;
import com.sy.mapper.JfhealthNewMapper;
import com.sy.mapper.JfhealthdaoMapper;
import com.sy.mapper.MemberMapper;
import com.sy.mapper.MessageMapper;
import com.sy.mapper.SensorstatusMapper;
import com.sy.mapper.UserMapper;
import com.sy.mapper.UsercodeMapper;
import com.sy.mapper.WaveformMapper;
import com.sy.pojo.Chat;
import com.sy.pojo.EquipmentData;
import com.sy.pojo.EquipmentRecord;
import com.sy.pojo.Jfhealth;
import com.sy.pojo.JfhealthNew;
import com.sy.pojo.Jfhealthdao;
import com.sy.pojo.Member;
import com.sy.pojo.Positionig;
import com.sy.pojo.Push;
import com.sy.pojo.PushRecord;
import com.sy.pojo.Realhealth;
import com.sy.pojo.Sensorstatus;
import com.sy.pojo.User;
import com.sy.pojo.Waveform;
import com.sy.service.ChatService;
import com.sy.service.EquipmentDataService;
import com.sy.service.EquipmentRecordService;
import com.sy.service.JfhealthService;
import com.sy.service.MigratingDataService;
import com.sy.service.PositionigService;
import com.sy.service.PushRecordService;
import com.sy.service.PushService;
import com.sy.service.RealhealthService;
import com.sy.service.UseravatarService;
import com.sy.utils.DataRow;

/**
 * 该类作用:把删除数据迁移到从数据库存放
 * @author Administrator
 */
@Service
public class MigratingDataServiceImpl implements MigratingDataService{
	@Autowired
	private JfhealthNewMapper jfNewMapper;
	@Autowired
	private EquipmentDataService equipmentDataService;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PushService pushService;
	@Autowired
	ChatMapper chatMapper;
	@Autowired
	JfhealthdaoMapper jfhealthdaoMapper;
	@Autowired
	SensorstatusMapper sensorstatusMapper;
	@Autowired
	UsercodeMapper usercodeMapper;
	@Autowired
	PositionigService positionigService;
	@Autowired
	WaveformMapper waveformMapper;
	@Autowired
	UseravatarService useravatarService;
	@Autowired
	MessageMapper messageMapper;
	@Autowired
	EquipmentRecordService equipmentRecordService;
	@Autowired
	JfhealthService jfhealthService;
	@Autowired
	MemberMapper memberMapper;
	@Autowired 
	RealhealthService realhealthService;
	@Autowired
	PushRecordService pushRecordService;
	@Autowired
	ChatService chatService;
	/**
	 * 迁移数据
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean migratingData(DataRow dataRow) throws SQLException {
		DynamicDataSourceHolder.setDbType(DataSourceType.SLAVE);//切换从数据库
		JfhealthNew j =(JfhealthNew) dataRow.get("jfhealthNew");
		if(j!=null){
			jfNewMapper.insert(j);
		}
		User user = (User) dataRow.get("user");
		if(user!=null){
			userMapper.insert(user);
		}
		Jfhealthdao dao = (Jfhealthdao) dataRow.get("dao");
		if(dao!=null){
			jfhealthdaoMapper.insert(dao);
		}
		Sensorstatus sensor= (Sensorstatus) dataRow.get("sensor");
		if(sensor!=null){
			sensorstatusMapper.insert(sensor);
		}
		Member member= (Member) dataRow.get("member");
		if(member!=null){
			memberMapper.insert(member);
		}
		Waveform waveform = (Waveform) dataRow.get("waveform");
		if(waveform!=null){
			waveformMapper.insert(waveform);
		}
		List<Chat> chat = (List<Chat>) dataRow.get("chat");
		if(!chat.isEmpty()){
			boolean as =chatService.insertBatch(chat);
			System.out.println(as);
		}
		List<Positionig> p = (List<Positionig>) dataRow.get("positionig");
		if(!p.isEmpty()){
			boolean as =positionigService.insertBatch(p);
			System.out.println(as);
		}
		List<Push> push  = (List<Push>) dataRow.get("push");
		if(!push.isEmpty()){
			boolean as =pushService.insertBatch(push);
			System.out.println(as);
		}
		List<EquipmentData> equipmentData= (List<EquipmentData>) dataRow.get("equipmentData");
		if(!equipmentData.isEmpty()){
			boolean as =equipmentDataService.insertBatch(equipmentData);
			System.out.println(as);
		}
		List<EquipmentRecord> equipmentRecord = (List<EquipmentRecord>) dataRow.get("equipmentRecord");
		if(!equipmentRecord.isEmpty()){
			boolean as =equipmentRecordService.insertBatch(equipmentRecord);
			System.out.println(as);
		}
		List<Jfhealth> jfhealth = (List<Jfhealth>) dataRow.get("jfhealth");
		if(!jfhealth.isEmpty()){
			boolean as =jfhealthService.insertBatch(jfhealth);
			System.out.println(as);
		}
		List<PushRecord> pushRecord= (List<PushRecord>) dataRow.get("pushRecord");
		if(!pushRecord.isEmpty()){
			boolean as =pushRecordService.insertBatch(pushRecord);
			System.out.println(as);
		}
		List<Realhealth> realhealth= (List<Realhealth>) dataRow.get("realhealth");
		if(!realhealth.isEmpty()){
			boolean as =realhealthService.insertBatch(realhealth);
			System.out.println(as);
		}
		DynamicDataSourceHolder.clearDataSource();//切换回主数据库
		return true;
	}

	
	
	
	
}
