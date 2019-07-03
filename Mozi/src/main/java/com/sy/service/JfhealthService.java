package com.sy.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.sy.common.ResultData;
import com.sy.pojo.Jfhealth;
import com.sy.utils.DataRow;
import com.sy.vo.Chart;
import com.sy.vo.SHChart;

public interface JfhealthService extends IService<Jfhealth>{

	
	/**
	 * 二级页面数据展示
	 * @return
	 * @throws Exception
	 */
	public ResultData<DataRow> querySecondaryData(DataRow map,ResultData<DataRow> re)throws Exception;
	
	/**根据时间获取获取健康数据
	 * @param map
	 * @return
	 */
	public List<Chart>selecthealth(Map map);
	/**
	 * 血压MAX,MIN,AVG,COUNT
	 * @param map
	 * @return
	 */
    public Map<String,String> selectBloodpressureInfo(Map<String, Object> map);
    /**
	 * 心率MAX,MIN,AVG,COUNT
	 * @param map
	 * @return
	 */
    public Map<String,String> selectHeartRateInfo(Map<String, Object> map);
    /**
   	 * HRVMAX,MIN,AVG,COUNT
   	 * @param map
   	 * @return
   	 */
    public Map<String,String> selectHrvInfo(Map<String, Object> map);
    /**
   	 * 步数MAX,MIN,AVG,COUNT
   	 * @param map
   	 * @return
   	 */
    public Map<String,String> selectStepWhenInfo(Map<String, Object> map);
    /**
     * 根据树洪步数，睡眠健康数据
     * @param map
     * @return
     */
    public List<SHChart> selectSHChart(Map map);
	/**获取惊凡最新一条数据
	 * @param imei
	 * @return
	 */
	public Jfhealth newjfhealth(String imei);
	/**
   	 * 啊健(极光推送获取惊凡最新健康数据)
   	 * @param alias
   	 * @return
   	 */
	public Jfhealth pushJfhealth(String alias)throws SQLException;
}
