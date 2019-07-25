package com.sy.mapper;

import java.sql.SQLException;
import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sy.pojo.Chat;

public interface ChatMapper extends BaseMapper<Chat>{

    int deleteByPrimaryKey(Integer id);
    /**
     * 通过IMEI号删除
     * @param imei
     * @return
     */
    int deleteCharInfo(String imei);

    Chat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Chat record);

    int updateByPrimaryKeyWithBLOBs(Chat record);

    int updateByPrimaryKey(Chat record);
    
    public List<Chat> selectChat(String imei)throws SQLException;
}