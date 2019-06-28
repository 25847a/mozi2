package com.sy.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sy.pojo.Jfhealth;
import com.sy.vo.Chart;
import com.sy.vo.SHChart;

public interface JfhealthMapper extends BaseMapper<Jfhealth>{

    int deleteByPrimaryKey(Integer id);

    int insertSelective(Jfhealth record);

    Jfhealth selectByPrimaryKey(Integer id);

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