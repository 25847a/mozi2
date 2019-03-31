package com.sy.vo;

import java.util.List;

public class Heartvo {
	// 心率集合数据
	private List<Hear> hs;
	// 心率平均值、分
	private Integer average;
	// 中等强度
	private String inHeart;
	// 较大强度
	private String BigHeart;
	// 极大强度
	private String StrongHeart;

	public Heartvo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Heartvo(List<Hear> hs, Integer average, String inHeart,
			String bigHeart, String strongHeart) {
		super();
		this.hs = hs;
		this.average = average;
		this.inHeart = inHeart;
		BigHeart = bigHeart;
		StrongHeart = strongHeart;
	}

	public List<Hear> getHs() {
		return hs;
	}

	public void setHs(List<Hear> hs) {
		this.hs = hs;
	}

	public Integer getAverage() {
		return average;
	}

	public void setAverage(Integer average) {
		this.average = average;
	}

	public String getInHeart() {
		return inHeart;
	}

	public void setInHeart(String inHeart) {
		this.inHeart = inHeart;
	}

	public String getBigHeart() {
		return BigHeart;
	}

	public void setBigHeart(String bigHeart) {
		BigHeart = bigHeart;
	}

	public String getStrongHeart() {
		return StrongHeart;
	}

	public void setStrongHeart(String strongHeart) {
		StrongHeart = strongHeart;
	}

}
