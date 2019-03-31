package com.sy.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.sy.mapper.EquipmentMapper;
import com.sy.mapper.SensorstatusMapper;
import com.sy.pojo.Equipment;
import com.sy.pojo.Sensorstatus;
import com.sy.pojo.UserEq;
import com.sy.service.EquipmentService;
import com.sy.service.UserEqService;
import com.sy.utils.PageModel;
import com.sy.vo.EquipmentVo;
import com.sy.vo.Equipmentstatus;
@Service
public class EquipmentServiceImpl implements EquipmentService {
	@Autowired
	private SensorstatusMapper sensorstatusmapper;
	@Autowired
	private EquipmentMapper equipmentMapper;
	@Autowired
	private UserEqService usereqservice;

	@Override
	public boolean updateEquipment(Equipment e) {
		e.setUpdatetime(new Date());
		if (equipmentMapper == null) {
			WebApplicationContext webApplicationContext = ContextLoader
					.getCurrentWebApplicationContext();
			equipmentMapper = (EquipmentMapper) webApplicationContext
					.getBean("equipmentMapper");
		}
		Integer num = equipmentMapper.updateByPrimaryKeySelective(e);
		if (num != 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Equipment selectequipment(Integer id) {
		// TODO Auto-generated method stub
		return equipmentMapper.selectByPrimaryKey(id);
	}

	@Override
	public Equipment selectquipmentimei(String imei) {
		if (equipmentMapper == null) {
			WebApplicationContext webApplicationContext = ContextLoader
					.getCurrentWebApplicationContext();
			equipmentMapper = (EquipmentMapper) webApplicationContext
					.getBean("equipmentMapper");
		}
		Equipment eq = null;
		try {
			eq = equipmentMapper.getequipment(imei);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return eq;
	}

	@Override
	public List<Equipment> selectequipment() {
		if (equipmentMapper == null) {
			WebApplicationContext webApplicationContext = ContextLoader
					.getCurrentWebApplicationContext();
			equipmentMapper = (EquipmentMapper) webApplicationContext
					.getBean("equipmentMapper");
		}
		return equipmentMapper.selectequipment();
	}

	@Override
	public void updatEequipmentst(Equipment e) {
		if (equipmentMapper == null) {
			WebApplicationContext webApplicationContext = ContextLoader
					.getCurrentWebApplicationContext();
			equipmentMapper = (EquipmentMapper) webApplicationContext
					.getBean("equipmentMapper");
		}
		 equipmentMapper.updateByPrimaryKey(e);
	}

	@Override
	public List<EquipmentVo> selelctequipments(Integer userId) {
		List<UserEq>  ues =usereqservice.selectuserqe(userId);
		if( ues !=null   && ues.size() >= 0){
			List<EquipmentVo>  es = new ArrayList<EquipmentVo> ();
			for(UserEq ue :ues){
				Equipment e =	equipmentMapper.selectByPrimaryKey(ue.getEqId());
				EquipmentVo evo = new EquipmentVo();
				evo.setCreatetime(e.getCreatetime());
				if(e.getEqStatus().equals("H:0")){
					evo.setEqStatus(false);
				}else {
					evo.setEqStatus(true);
				}
				
				evo.setEqtype(Integer.parseInt(e.getEqtype()));
				evo.setId(e.getId());
				evo.setImei(e.getImei());
				evo.setUpdatetime(e.getUpdatetime());
				if(ue.getTypeof() ==0){
					evo.setRole("监护者");
				}else if (ue.getTypeof() ==1) {
					evo.setRole("观察者");
				}else if (ue.getTypeof() ==2) {
					evo.setRole("使用者");
				}
				es.add(evo);
			}

			return es;
		}else {

			return null;
		}
			
		
		
	}

	@Override
	public boolean addEquipment(Equipment e) {
		Integer num = equipmentMapper.insertSelective(e);
		if (num != 0) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 后台录入设备调用,返回已经存在新建失败的imei号
	 * @param e
	 * @return
	 */
	public List<String> allentry(List<String> list,Integer agentid,String model) {
		
		Set<String> set = new HashSet<>();
		for (String string : list) {
			set.add(string);
		}
		
		List<Object> selectImei = equipmentMapper.selectImei(list);
		List<String> imeiList = new ArrayList<>();
		
		if(selectImei!=null&&selectImei.size()>0){
			for (String imei : set) {
				if(!selectImei.contains(imei)){
					Equipment adde = new Equipment();
					adde.setImei(imei);
					adde.setCreatetime(new Date());
					adde.setUpdatetime(new Date());
					adde.setEqStatus("H:0");
					adde.setBluetoothElectricity(0);
					adde.setBluetoothStatus("0");
					adde.setBluetoothType("0");
					adde.setClock("闹钟");
					adde.setName("设备信息");
					adde.setEqtype("1");
					adde.setLordpower(0);
					adde.setVersion("0.0");
					adde.setSignalxhao("0");
					adde.setBluetoothName("000000000000");
					adde.setBluetoothmac("000000000000");
					adde.setAgentid(agentid);
					adde.setModel(model);
					equipmentMapper.insertSelective(adde);
			}else{
				imeiList.add(imei);
			}
		}
		}else{
			for (String imei : set) {
					Equipment adde = new Equipment();
					adde.setImei(imei);
					adde.setCreatetime(new Date());
					adde.setUpdatetime(new Date());
					adde.setEqStatus("H:0");
					adde.setBluetoothElectricity(0);
					adde.setBluetoothStatus("0");
					adde.setBluetoothType("0");
					adde.setClock("闹钟");
					adde.setName("设备信息");
					adde.setEqtype("1");
					adde.setLordpower(0);
					adde.setVersion("0.0");
					adde.setSignalxhao("0");
					adde.setBluetoothName("蓝牙");
					adde.setAgentid(agentid);
					adde.setModel(model);
					equipmentMapper.insertSelective(adde);
			}
		}
		return imeiList;
	}

	@Override
	public PageModel<Equipmentstatus> getusersone(Integer pageNo, String keyWord) {

		if(pageNo == null ||  pageNo.intValue() == 0){
			pageNo=1;
		}
		 //获取数据总数
		    Integer count=equipmentMapper.getcount(keyWord);
		    Integer pageSize=10;
		    List<Equipment>Feedbacks = null;
		    List<Equipmentstatus>eqs = new ArrayList<Equipmentstatus>();
		    Integer pageNo1 = ( pageNo - 1) * pageSize;
		    //获取页数
		    HashMap<String, Object> parameter = new HashMap<>();
		    parameter.put("pageNo", pageNo1);
		    parameter.put("keyWord", keyWord);
		    parameter.put("pageSize", pageSize);
		    Feedbacks = equipmentMapper.list(parameter);
		    for(Equipment e : Feedbacks){
		    	Sensorstatus sensorstatus =sensorstatusmapper.selecttimesensorstatus(e.getImei());
		    	Equipmentstatus eq=null;
		    	if(sensorstatus !=null){
		    		String g =sensorstatus.getG();
			    	String h = sensorstatus.getH();
			    			
			    	 eq = new Equipmentstatus(e.getId(), e.getImei(), e.getLordpower(), e.getSignalxhao(), e.getBluetoothType(), e.getEqStatus(), e.getCreatetime(), e.getUpdatetime(), e.getEqtype(), e.getBluetoothName(), 
			    			e.getBluetoothStatus(), e.getBluetoothElectricity(), e.getClock(), e.getPhone1(), e.getPhone2(), e.getName(), e.getVersion(), e.getUploadtime(), e.getBluetoothmac(), h.split(":")[1], g.split(":")[1]);
			    		
		    	}else {
		    		 eq = new Equipmentstatus(e.getId(), e.getImei(), e.getLordpower(), e.getSignalxhao(), e.getBluetoothType(), e.getEqStatus(), e.getCreatetime(), e.getUpdatetime(), e.getEqtype(), e.getBluetoothName(), 
				    			e.getBluetoothStatus(), e.getBluetoothElectricity(), e.getClock(), e.getPhone1(), e.getPhone2(), e.getName(), e.getVersion(), e.getUploadtime(), e.getBluetoothmac(), "暂时没有数据", "暂时没有数据");
				    
				}
		    	eqs.add(eq);
		    }
		    PageModel<Equipmentstatus> pageModel = new PageModel<Equipmentstatus>(pageNo, pageSize,count, eqs,"equipment/list");
		if(pageModel.getCount() !=0){
			pageModel.init();
		}
		return pageModel;
	}
	@Override
	public PageModel<Equipment> getusersone(Integer pageNo, Map<String, Object> map) {
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		
		if(pageNo == null ||  pageNo.intValue() == 0){
			pageNo=1;
		}
		
		//获取数据总数
		Integer count=equipmentMapper.getcount2(map);
		Integer pageSize=10;
		List<Equipment>Feedbacks = null;
		//List<Equipmentstatus>eqs = new ArrayList<Equipmentstatus>();
		Integer pageNo1 = ( pageNo - 1) * pageSize;
		//获取页数
		map.put("pageNo", pageNo1);
		map.put("pageSize", pageSize);
		Feedbacks = equipmentMapper.list(map);
		/*for(Equipment e : Feedbacks){
			
			String formatStr =formatter.format(e.getUpdatetime());
			
			Sensorstatus sensorstatus =sensorstatusmapper.selecttimesensorstatus(e.getImei());
			Equipmentstatus eq=null;
			if(sensorstatus !=null){
				String g =sensorstatus.getG();
				String h = sensorstatus.getH();
				
				eq = new Equipmentstatus(e.getId(), e.getImei(), e.getLordpower(), e.getSignalxhao(), e.getBluetoothType(), e.getEqStatus(), e.getCreatetime(), formatStr, e.getEqtype(), e.getBluetoothName(), 
						e.getBluetoothStatus(), e.getBluetoothElectricity(), e.getClock(), e.getPhone1(), e.getPhone2(), e.getName(), e.getVersion(), e.getUploadtime(), e.getBluetoothmac(), h.split(":")[1], g.split(":")[1]);
				
			}else {
				eq = new Equipmentstatus(e.getId(), e.getImei(), e.getLordpower(), e.getSignalxhao(), e.getBluetoothType(), e.getEqStatus(), e.getCreatetime(), formatStr, e.getEqtype(), e.getBluetoothName(), 
						e.getBluetoothStatus(), e.getBluetoothElectricity(), e.getClock(), e.getPhone1(), e.getPhone2(), e.getName(), e.getVersion(), e.getUploadtime(), e.getBluetoothmac(), "暂时没有数据", "暂时没有数据");
				
			}
			eqs.add(eq);
		}*/
		//PageModel<Equipmentstatus> pageModel = new PageModel<Equipmentstatus>(pageNo, pageSize,count, eqs,"equipment/list");
		PageModel<Equipment> pageModel = new PageModel<Equipment>(pageNo, pageSize,count, Feedbacks,"equipment/list");
		if(pageModel.getCount() !=0){
			pageModel.init();
		}
		return pageModel;
	}

	/**
	 * 根据代理商id统计设备数量
	 */
	public Integer countEqNumber(Integer agentid) {
		return equipmentMapper.selectEqNumber(agentid);
	}

	@Override
	public PageModel<Equipment> getAgentEquipment(Integer pageNo, Map<String, Object> map) {
		if(pageNo == null ||  pageNo.intValue() == 0){
			pageNo=1;
		}
		//获取数据总数
		Integer count=equipmentMapper.getcount2(map);
		Integer pageSize=10;
		Integer pageNo1 = ( pageNo - 1) * pageSize;
		//获取页数
		map.put("pageNo", pageNo1);
		map.put("pageSize", pageSize);
		List<Equipment>list = equipmentMapper.list(map);
		
		PageModel<Equipment> pageModel = new PageModel<Equipment>(pageNo, pageSize,count, list,"management/equipmentlist");
			pageModel.init();
		return pageModel;
	}

	public boolean imeiUpdateAgentAccount(Equipment e) {
		return equipmentMapper.imeiUpdateAgentAccount(e);
	}
	
	public Equipment equipmentstatus(String eqStatus, String eqtype, String imei,Integer lordpower,String signalxhao,String version) {
		if (equipmentMapper == null) {
			WebApplicationContext webApplicationContext = ContextLoader
					.getCurrentWebApplicationContext();
			equipmentMapper = (EquipmentMapper) webApplicationContext
					.getBean("equipmentMapper");
		}
		Equipment e  = equipmentMapper.getequipment(imei);
		
			if(signalxhao !=null ){
				e.setSignalxhao(signalxhao);
				e.setLordpower(lordpower);
			}
			if(eqStatus!=null){
				e.setEqStatus(eqStatus);
			}
			if(version!=null){
			e.setVersion(version);
			}
			e.setEqtype(eqtype);
			e.setUpdatetime(new Date());
			equipmentMapper.updateByPrimaryKey(e);
			return e;
	}
	public Equipment updateEqStatus(String eqStatus, String eqtype, String imei,Integer lordpower,String signalxhao,String version,Equipment e) {
		
		if(signalxhao !=null ){
			e.setSignalxhao(signalxhao);
			e.setLordpower(lordpower);
		}
		if(eqStatus!=null){
			e.setEqStatus(eqStatus);
		}
		if(version!=null){
		e.setVersion(version);
		}
		e.setEqtype(eqtype);
		e.setUpdatetime(new Date());
		equipmentMapper.updateByPrimaryKey(e);
		return e;
	}

	@Override
	public void updateBluetoothList(Map map) {
		equipmentMapper.updateBluetoothList(map);
	}

}
