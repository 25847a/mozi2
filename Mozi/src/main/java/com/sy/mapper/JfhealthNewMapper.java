package com.sy.mapper;

import java.sql.SQLException;
import org.apache.ibatis.annotations.Select;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sy.pojo.Jfhealth;
import com.sy.pojo.JfhealthNew;

public interface JfhealthNewMapper extends BaseMapper<JfhealthNew>{

    int deleteByPrimaryKey(Integer id);

  //  int insertSelective(JfhealthNew record);


    Jfhealth selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(JfhealthNew record);


    int updateByPrimaryKey(JfhealthNew record);
    
    public Integer getcount(String keyWord);
   	
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