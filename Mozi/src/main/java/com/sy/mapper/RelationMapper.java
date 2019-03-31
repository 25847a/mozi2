package com.sy.mapper;

import org.apache.ibatis.annotations.Insert;

import com.sy.pojo.Relation;

public interface RelationMapper {
	
	
	@Insert("insert into relation (userId,mid,type) values (#{userId},#{mid},#{type})")
	public void insert(Relation relation);
	
}
