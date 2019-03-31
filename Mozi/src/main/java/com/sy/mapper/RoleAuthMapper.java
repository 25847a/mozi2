package com.sy.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sy.pojo.RoleAuth;
import com.sy.pojo.RoleAuthExample;

public interface RoleAuthMapper {
    int countByExample(RoleAuthExample example);

    int deleteByExample(RoleAuthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RoleAuth record);

    int insertSelective(RoleAuth record);

    List<RoleAuth> selectByExample(RoleAuthExample example);

    RoleAuth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoleAuth record, @Param("example") RoleAuthExample example);

    int updateByExample(@Param("record") RoleAuth record, @Param("example") RoleAuthExample example);

    int updateByPrimaryKeySelective(RoleAuth record);

    int updateByPrimaryKey(RoleAuth record);
    
    public List<RoleAuth> getroleauth(Integer roleId );
    
    //根据menu的id进行删除
    public void deleteid(Integer id);
    
    public List<RoleAuth>selectRoleMenu();
    //获取数据的总数
    public int getcount(String keyWord);
    //获取想要的数据

    public List<RoleAuth> listRoleMenur(Map<String, Object> parameter);
    //根据Roleid获取对应的目录数据
    public List<RoleAuth> getrmid(Integer roleid);
}