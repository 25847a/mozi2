package com.sy.mapper;

import java.sql.SQLException;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sy.pojo.Comment;
public interface CommentMapper extends BaseMapper<Comment>{
	
	
	public int deleteComment(int userId)throws SQLException;
	
	
}
