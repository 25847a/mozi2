package com.sy.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sy.pojo.Config;
public interface ConfigMapper extends BaseMapper<Config>{
	/**
	 * 通过ID查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Config queryConfigId(Integer id)throws Exception;
	/**
	 * 查询系统配置表集合
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Config> queryConfigList(@Param(value="type")String type,@Param(value="lable")String lable)throws Exception;
	/**
	 * 查询系统配置表信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Config queryConfigInfo(@Param(value="type")String type,@Param(value="lable")String lable)throws Exception;
}
