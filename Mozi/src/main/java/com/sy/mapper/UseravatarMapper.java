package com.sy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sy.pojo.Useravatar;
import com.sy.pojo.UseravatarExample;

public interface UseravatarMapper extends BaseMapper<Useravatar>{
    int countByExample(UseravatarExample example);

    int deleteByExample(UseravatarExample example);

    int deleteByPrimaryKey(Integer id);

    Integer insert(Useravatar record);

    int insertSelective(Useravatar record);

    List<Useravatar> selectByExample(UseravatarExample example);

    Useravatar selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Useravatar record, @Param("example") UseravatarExample example);

    int updateByExample(@Param("record") Useravatar record, @Param("example") UseravatarExample example);

    int updateByPrimaryKeySelective(Useravatar record);

    int updateByPrimaryKey(Useravatar record);
    
    public Useravatar selectavartar();
}