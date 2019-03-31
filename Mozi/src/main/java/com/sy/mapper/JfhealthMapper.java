package com.sy.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sy.pojo.Jfhealth;
import com.sy.pojo.JfhealthExample;
import com.sy.vo.Chart;
import com.sy.vo.SHChart;

public interface JfhealthMapper {
    int countByExample(JfhealthExample example);

    int deleteByExample(JfhealthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Jfhealth record);

    int insertSelective(Jfhealth record);

    List<Jfhealth> selectByExampleWithBLOBs(JfhealthExample example);

    List<Jfhealth> selectByExample(JfhealthExample example);

    Jfhealth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Jfhealth record, @Param("example") JfhealthExample example);

    int updateByExampleWithBLOBs(@Param("record") Jfhealth record, @Param("example") JfhealthExample example);

    int updateByExample(@Param("record") Jfhealth record, @Param("example") JfhealthExample example);

    int updateByPrimaryKeySelective(Jfhealth record);

    int updateByPrimaryKeyWithBLOBs(Jfhealth record);

    int updateByPrimaryKey(Jfhealth record);
    /**
     * 获取血压,心率,血氧,呼吸,心跳变异(HRV),微循环数据 （根据年月日 周）查找 
     * @param map
     * @return
     */
    public List<Chart> selecthealth(Map map);
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
    
    
    public Integer getcount(String keyWord);
    public Integer getcount2(Map map);

   	public List<Jfhealth> list(Map map);
   	
   	public Jfhealth newjfhealth(String imei);
   	/**
   	 * 啊健(极光推送获取惊凡最新健康数据)
   	 * @param alias
   	 * @return
   	 */
   	public Jfhealth pushJfhealth(String alias)throws SQLException;
   	public void deletejfhealth(String phone);
}