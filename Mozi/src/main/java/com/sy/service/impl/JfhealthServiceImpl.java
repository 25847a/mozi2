package com.sy.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sy.mapper.EquipmentMapper;
import com.sy.mapper.JfhealthMapper;
import com.sy.pojo.Jfhealth;
import com.sy.service.JfhealthService;
import com.sy.utils.PageModel;
import com.sy.vo.Chart;
import com.sy.vo.SHChart;

@Service
public class JfhealthServiceImpl implements JfhealthService {
	@Autowired
	private JfhealthMapper jfhealthmapper;
	@Autowired
	private EquipmentMapper equipmentMapper;

	@Override
	public boolean addJfhealth(Jfhealth jf) {
		Integer num = jfhealthmapper.insert(jf);
		if (num != 0) {
			return true;
		} else {
			return false;
		}

	}
	/**根据时间获取获取健康数据
	 * @param map
	 * @return
	 */
	@Override
	public List<Chart> selecthealth(Map map) {
		// TODO Auto-generated method stub
		return jfhealthmapper.selecthealth(map);
	}
	/**
     * 根据时间获取最大血压数据
     * @param map
     * @return
     */
	@Override
	public Jfhealth selecthealthMax(Map map) {
		return jfhealthmapper.selecthealthMax(map);
	}
	/**
     * 根据时间获取最小血压数据
     * @param map
     * @return
     */
	@Override
	public Jfhealth selecthealthMin(Map map) {
		return jfhealthmapper.selecthealthMin(map);
	}
	/**
     * 根据树洪步数，睡眠健康数据
     * @param map
     * @return
     */
	@Override
	public List<SHChart> selectSHChart(Map map) {
		return jfhealthmapper.selectSHChart(map);
	}
	/* 获取健康数据
	 * (non-Javadoc)
	 * @see com.sy.service.Jfhealthservice#getusersone(java.lang.Integer, java.lang.String)
	 */
	@Override
	public PageModel<Jfhealth> getusersone(Integer pageNo, String keyword) {
		if(pageNo == null ||  pageNo.intValue() == 0){
			pageNo=1;
		}
		 //获取数据总数
		    Integer count=jfhealthmapper.getcount(keyword);
		    Integer pageSize=10;
		    Integer pageNo1 = ( pageNo - 1) * pageSize;
		    //获取页数
		    HashMap<String, Object> parameter = new HashMap<>();
		    parameter.put("pageNo", pageNo1);
		    parameter.put("keyWord", keyword);
		    parameter.put("pageSize", pageSize);
		    List<Jfhealth>Feedbacks=jfhealthmapper.list(parameter);
		    PageModel<Jfhealth> pageModel = new PageModel<Jfhealth>(pageNo, pageSize,count, Feedbacks,"health/list");
		if(pageModel.getCount() !=0){
			pageModel.init();
		}
		return pageModel;
	}
	@Override
	public Jfhealth newjfhealth(String imei) {
		
		return jfhealthmapper.newjfhealth(imei);
	}
	/**
   	 * 啊健(极光推送获取惊凡最新健康数据)
   	 * @param alias
   	 * @return
   	 */
	@Override
	public Jfhealth pushJfhealth(String alias) throws SQLException{
		return jfhealthmapper.pushJfhealth(alias);
	}
	
	public PageModel<Jfhealth> getJfhealthVoLsit(Integer pageNo,Map map){
		
		if(pageNo == null ||  pageNo.intValue() == 0){
			pageNo=1;
		}
		Integer mid = (Integer) map.get("mid");
		
		List<String> list  = null;
		
		if(mid!=null){
			list = equipmentMapper.selectImeiList(mid);
		}
		
		String keyword = (String) map.get("keyword");
		Integer id =  (Integer) map.get("userid");
		String time = (String) map.get("time");
		
		//获取数据总数
		//Integer count=jfhealthmapper.getcount(keyword);
		Integer pageSize=10;
		Integer pageNo1 = ( pageNo - 1) * pageSize;
		
		//获取页数
		HashMap<String, Object> parameter = new HashMap<>();
		parameter.put("pageNo", pageNo1);
		String phone = null;
		if(id!=null){
			phone = "mozistar"+id;
		}
		parameter.put("list", list);
		parameter.put("phone", phone);
		parameter.put("time", time);
		parameter.put("keyWord", keyword);
		parameter.put("pageSize", pageSize);
		
		
		Integer count=jfhealthmapper.getcount2(parameter);
		List<Jfhealth>Feedbacks=jfhealthmapper.list(parameter);
		PageModel<Jfhealth> pageModel = new PageModel<Jfhealth>(pageNo, pageSize,count, Feedbacks,"health/list");
		if(pageModel.getCount() !=0){
			pageModel.init();
		}
		return pageModel;
	}
}
