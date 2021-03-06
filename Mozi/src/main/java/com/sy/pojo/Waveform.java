package com.sy.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;


@TableName("waveform")
public class Waveform extends Model<Waveform>{
	
	private static final long serialVersionUID = 1L;
	
	
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private Integer userId;
	private String imei;
	private String data;
	private String instructions;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return this.id;
	}
	
	
}
