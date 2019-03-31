package com.sy.mapper;

import org.apache.ibatis.annotations.Select;

import com.sy.pojo.Waveform;

public interface WaveformMapper {
	
	public void insertSelective(Waveform waveform);
	
	@Select("select * from waveform where imei = #{imei}")
	public Waveform getWaveform(String imei);
	public void update(Waveform w);
	
}