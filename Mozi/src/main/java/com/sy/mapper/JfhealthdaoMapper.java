package com.sy.mapper;

import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sy.pojo.Jfhealthdao;
public interface JfhealthdaoMapper extends BaseMapper<Jfhealthdao>{
    int deleteByPrimaryKey(Integer id);
    //根据imei和phone删除校准值
    int deleteJfhealthdaoInfo(String phone);
  

    Jfhealthdao selectByPrimaryKey(Integer id);

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
	//@Delete("delete from jfhealthdao where phone = #{phone}")
 //   public void delectjfhealthdao(String phone);
}