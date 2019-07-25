package com.sy.mapper;

import java.sql.SQLException;
import org.apache.ibatis.annotations.Select;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sy.pojo.Waveform;

public interface WaveformMapper extends BaseMapper<Waveform>{
	
	public void insertSelective(Waveform waveform);
	
	@Select("select * from waveform where imei = #{imei}")
	public Waveform getWaveform(String imei);
	@Select("select * from waveform where userId=#{userId}")
	public Waveform getWaveform1(int userId);
	
	public void update(Waveform w);
	/**
	 * 查询波形图数据
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public Waveform queryWaveformInfo(int userId)throws SQLException;
	/**
	 * 删除波形图数据
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public int deleteWaveform(int userId)throws SQLException;
}