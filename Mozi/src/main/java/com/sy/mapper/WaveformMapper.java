package com.sy.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Select;

import com.sy.pojo.Waveform;

public interface WaveformMapper {
	
	public void insertSelective(Waveform waveform);
	
	@Select("select * from waveform where imei = #{imei}")
	public Waveform getWaveform(String imei);
	public void update(Waveform w);
	/**
	 * 查询波形图数据
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public Waveform queryWaveformInfo(int userId)throws SQLException;
}