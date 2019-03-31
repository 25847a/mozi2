package com.sy.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.sy.mapper.EquipmentDataMapper;
import com.sy.pojo.EquipmentData;
import com.sy.service.EquipmentDataService;
import com.sy.utils.PageModel;

@Service
public class EquipmentDataServiceImpl implements EquipmentDataService {
	@Autowired
	private EquipmentDataMapper dataMapper;
	//@Autowired
	//private EquipmentMapper equipmentmapper;
	
	private final static Logger logger = LoggerFactory.getLogger(EquipmentDataServiceImpl.class);
	

	@Override
	public boolean addEquipmentData(EquipmentData e) {
		e.setCreatetime(new Date());

		if (dataMapper == null) {
			WebApplicationContext webApplicationContext = ContextLoader
					.getCurrentWebApplicationContext();
			dataMapper = (EquipmentDataMapper) webApplicationContext
					.getBean("equipmentDataMapper");
		}
		Integer num = dataMapper.insertSelective(e);
		if (num != 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public PageModel<EquipmentData> getusersone(Integer pageNo, String keyWord) {
		if(pageNo == null ||  pageNo.intValue() == 0){
			pageNo=1;
		}
		 //获取数据总数
		    Integer count=dataMapper.getcount(keyWord);
		    Integer pageSize=10;
		    Integer pageNo1 = ( pageNo - 1) * pageSize;
		    //获取页数
		    HashMap<String, Object> parameter = new HashMap<>();
		    parameter.put("pageNo", pageNo1);
		    parameter.put("keyWord", keyWord);
		    parameter.put("pageSize", pageSize);
		    List<EquipmentData>Feedbacks=dataMapper.list(parameter);
//		    for(EquipmentData ds:ldate){
//		    	try {
//		    		EquipmentDataimei eq = new EquipmentDataimei(); 
//			    	Integer eqid =usereqservice.geteqid(ds.getUserId());
//			    	Equipment e=equipmentservice.selectequipment(eqid);
//			    	eq.setEquipmentData(ds);
//			    	eq.setImei(e.getImei());
//			    	Feedbacks.add(eq);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//		    
//		    }
		    PageModel<EquipmentData> pageModel = new PageModel<EquipmentData>(pageNo, pageSize,count, Feedbacks,"equipmentData/list");
		if(pageModel.getCount() !=0){
			pageModel.init();
		}
		return pageModel;
	}

	@Override
	public EquipmentData selectdata(Integer userId) {
		EquipmentData edata =dataMapper.selectdata(userId);
		return edata;
	}

	public List<EquipmentData> selectdateequipmentDate(Map m) {
		// TODO Auto-generated method stub
		return dataMapper.selectdateequipmentDate(m);
	}

	public boolean updateEquipmentData(EquipmentData record) {
		int num = dataMapper.updateByPrimaryKey(record);
		return num==0?false:true;
	}

	/**
	 * 查询当天的步行数
	 * @param   m.put("countdate", dd+"%");   m.put("userid",userId);
	 * @return
	 */
	public List<EquipmentData> selecttheycount(Map m) {
		return dataMapper.selecttheycount(m);
	}
	
}
