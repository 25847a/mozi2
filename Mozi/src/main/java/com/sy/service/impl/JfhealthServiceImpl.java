package com.sy.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sy.common.ResultData;
import com.sy.mapper.EquipmentDataMapper;
import com.sy.mapper.JfhealthMapper;
import com.sy.mapper.JfhealthNewMapper;
import com.sy.mapper.UserMapper;
import com.sy.pojo.Jfhealth;
import com.sy.pojo.JfhealthNew;
import com.sy.pojo.User;
import com.sy.service.JfhealthService;
import com.sy.utils.DataRow;
import com.sy.utils.DataUtil;
import com.sy.vo.Chart;
import com.sy.vo.SHChart;

@Service
public class JfhealthServiceImpl extends ServiceImpl<JfhealthMapper, Jfhealth> implements JfhealthService {
	@Autowired
	private JfhealthMapper jfhealthmapper;
	@Autowired
	EquipmentDataMapper equipmentDataMapper;
	@Autowired
	JfhealthNewMapper jfhealthNewMapper;
	@Autowired
	UserMapper userMapper;
	/**
	 * 二级页面数据展示
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultData<DataRow> querySecondaryData(DataRow map, ResultData<DataRow> re) throws Exception {
		User  user = userMapper.selectById(map.getInt("userId"));
		JfhealthNew jfhealth = jfhealthNewMapper.newJfhealthNew(user.getImei());
		DataRow data =  equipmentDataMapper.queryStepWhenCarrieroadSum(map.getInt("userId"));
		// =new DataRow();//获取数据库
		switch(map.getInt("category")){
		case 1:
			re =DataUtil.heartSecondary(data,jfhealth,Integer.valueOf(user.getAge()),re);//获取数据库,心率
			break;
		case 2:
			re =DataUtil.bloodSecondary(data,jfhealth,Integer.valueOf(user.getAge()),re);//获取数据库,血氧
			break;
		case 3:
			re =DataUtil.microcSecondary(data,jfhealth,Integer.valueOf(user.getAge()),re);//获取数据库,微循环
			break;
		case 4:
			re =DataUtil.breathSecondary(data,jfhealth,Integer.valueOf(user.getAge()),re);//获取数据库,呼吸
			break;
		case 5:
			re =DataUtil.stepWhenSecondary(data,jfhealth,Integer.valueOf(user.getAge()),re);//获取数据库,步数
			break;
		case 6:
			re =DataUtil.pressureSecondary(data,jfhealth,Integer.valueOf(user.getAge()),re);//获取数据库,血压
			break;
		case 7:
			re =DataUtil.warmSecondary(data,jfhealth,Integer.valueOf(user.getAge()),Float.valueOf(17),re);//获取数据库,体温
			break;
		case 8:
			re =DataUtil.humiditySecondary(data,jfhealth,Integer.valueOf(user.getAge()),"偏高",re);//获取数据库,湿度
			break;
		case 9:
			re =DataUtil.hrvSecondary(data,jfhealth,Integer.valueOf(user.getAge()),re);//获取数据库,hrv
			break;
		case 10:
			re =DataUtil.emotionSecondary(data,jfhealth,Integer.valueOf(user.getAge()),17,re);//获取数据库,情绪
			break;
		case 11:
			re =DataUtil.carrieroadSecondary(data,jfhealth,Integer.valueOf(user.getAge()),re);//获取数据库,卡里路
			break;
		}
		
		return re;
	}
	/**根据时间获取获取健康数据
	 * @param map
	 * @return
	 */
	@Override
	public List<Chart> selecthealth(Map map) {
		return jfhealthmapper.selecthealth(map);
	}
	/**
	 * 血压MAX,MIN,AVG,COUNT
	 * @param map
	 * @return
	 */
	@Override
	public Map<String, String> selectBloodpressureInfo(Map<String, Object> map) {
		return jfhealthmapper.selectBloodpressureInfo(map);
	}
	/**
	 * 心率MAX,MIN,AVG,COUNT
	 * @param map
	 * @return
	 */
	@Override
	public Map<String, String> selectHeartRateInfo(Map<String, Object> map) {
		return jfhealthmapper.selectHeartRateInfo(map);
	}
	/**
   	 * HRVMAX,MIN,AVG,COUNT
   	 * @param map
   	 * @return
   	 */
	@Override
	public Map<String, String> selectHrvInfo(Map<String, Object> map) {
		return jfhealthmapper.selectHrvInfo(map);
	}
	/**
   	 * 步数MAX,MIN,AVG,COUNT
   	 * @param map
   	 * @return
   	 */
	@Override
	public Map<String, String> selectStepWhenInfo(Map<String, Object> map) {
		return jfhealthmapper.selectStepWhenInfo(map);
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
}
