package com.sy.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import com.sy.pojo.Jfhealthdao;
import com.sy.pojo.JfhealthdaoExample;

public interface JfhealthdaoMapper {
    int countByExample(JfhealthdaoExample example);

    int deleteByExample(JfhealthdaoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Jfhealthdao record);

    int insertSelective(Jfhealthdao record);

    List<Jfhealthdao> selectByExampleWithBLOBs(JfhealthdaoExample example);

    List<Jfhealthdao> selectByExample(JfhealthdaoExample example);

    Jfhealthdao selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Jfhealthdao record, @Param("example") JfhealthdaoExample example);

    int updateByExampleWithBLOBs(@Param("record") Jfhealthdao record, @Param("example") JfhealthdaoExample example);

    int updateByExample(@Param("record") Jfhealthdao record, @Param("example") JfhealthdaoExample example);

    int updateByPrimaryKeySelective(Jfhealthdao record);

    int updateByPrimaryKeyWithBLOBs(Jfhealthdao record);

    int updateByPrimaryKey(Jfhealthdao record);
    
    int updateByPhone(Map map);
    
    /**
     * 
     * @param imei 根据imei号查询
     * @return 返回id
     */
    public  Integer  selelctJfhealthdao(String imei);
    /**
     * 
     * @param imei 根据imei号查询
     * @return 返回实体
     */
    public  Jfhealthdao  getJfhealthdao(String imei);
    /**
     * 啊健
     * @param imei
     * @param phone
     * @return
     */
    public Jfhealthdao JfhealthdaoInfo(@Param("imei")String imei,@Param("account")String account);
    public Jfhealthdao getjfhealthdao(String phone);
	@Delete("delete from jfhealthdao where phone = #{phone}")
    public void delectjfhealthdao(String phone);
}