package com.sy.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sy.pojo.Jfhealth;
import com.sy.utils.PageModel;
import com.sy.vo.Chart;
import com.sy.vo.SHChart;

public interface JfhealthService {

	/**添加惊凡健康数据
	 * @param jf
	 * @return
	 */
	public boolean addJfhealth(Jfhealth jf);
	
	/**根据时间获取获取健康数据
	 * @param map
	 * @return
	 */
	public List<Chart>selecthealth(Map map);
	/**
     * 根据时间获取最大血压数据
     * @param map
     * @return
     */
    public Jfhealth selecthealthMax(Map map);
    /**
     * 根据时间获取最小血压数据
     * @param map
     * @return
     */
    public Jfhealth selecthealthMin(Map map);
    /**
     * 根据树洪步数，睡眠健康数据
     * @param map
     * @return
     */
    public List<SHChart> selectSHChart(Map map);
    
    /**分页获取数据
	 * @param pageNo
	 * @param keyword
	 * @return
	 */
	public PageModel<Jfhealth>  getusersone(Integer pageNo,String keyword);
	
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
	
	public PageModel<Jfhealth> getJfhealthVoLsit(Integer pageNo, Map map);

}
