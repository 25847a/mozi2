package com.sy.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sy.pojo.Jfhealth;
import com.sy.pojo.JfhealthExample;
import com.sy.pojo.JfhealthNew;
import com.sy.vo.Chart;
import com.sy.vo.SHChart;

public interface JfhealthNewMapper {
    int countByExample(JfhealthExample example);

    int deleteByExample(JfhealthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(JfhealthNew record);

    int insertSelective(JfhealthNew record);

    List<Jfhealth> selectByExampleWithBLOBs(JfhealthExample example);

    List<Jfhealth> selectByExample(JfhealthExample example);

    Jfhealth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") JfhealthNew record, @Param("example") JfhealthExample example);

    int updateByExampleWithBLOBs(@Param("record") JfhealthNew record, @Param("example") JfhealthExample example);

    int updateByExample(@Param("record") JfhealthNew record, @Param("example") JfhealthExample example);

    int updateByPrimaryKeySelective(JfhealthNew record);

    int updateByPrimaryKeyWithBLOBs(JfhealthNew record);

    int updateByPrimaryKey(JfhealthNew record);
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
    
    public JfhealthNew selecthealthMax(Map map);
    /**
     * 根据时间获取最小血压数据
     * @param map
     * @return
     */
    public JfhealthNew selecthealthMin(Map map);
    /**
     * 根据树洪步数，睡眠健康数据
     * @param map
     * @return
     */
    public List<SHChart> selectSHChart(Map map);
    
    
    public Integer getcount(String keyWord);

   	public List<JfhealthNew> list(Map map);
   	
   	public JfhealthNew newJfhealthNew(String imei);
   	/**
   	 * 啊健(极光推送获取惊凡最新健康数据)
   	 * @param alias
   	 * @return
   	 */
   	public JfhealthNew pushJfhealth(String alias)throws SQLException;
   	public Integer countImei(String imei);
   	
   	@Select("select * from jfhealth_new where phone=#{phone}")
   	public JfhealthNew selectJfhealthNew(String phone);

	void deletejfhealth(String string);
   	
}