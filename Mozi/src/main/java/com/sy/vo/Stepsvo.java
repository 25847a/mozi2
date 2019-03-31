package com.sy.vo;

import java.util.List;

/**步数界面图形化数据
 * @author Administrator
 *
 */
public class Stepsvo {
    //今天不行数据跟步行时间
	private List<Steps> stepss;
	//消耗热量
	private String Heat;
	//步行时长
	private String stepsduration;
	//跑步时长
	private String runduration;
	//行动距离
	private String actiondistance;
	//目标步数
	private String  Targetsteps;
	//总步数
	private String  totalsteps;
	public List<Steps> getStepss() {
		return stepss;
	}
	public void setStepss(List<Steps> stepss) {
		this.stepss = stepss;
	}
	public String getHeat() {
		return Heat;
	}
	public void setHeat(String heat) {
		Heat = heat;
	}
	public String getStepsduration() {
		return stepsduration;
	}
	public void setStepsduration(String stepsduration) {
		this.stepsduration = stepsduration;
	}
	public String getRunduration() {
		return runduration;
	}
	public void setRunduration(String runduration) {
		this.runduration = runduration;
	}
	public String getActiondistance() {
		return actiondistance;
	}
	public void setActiondistance(String actiondistance) {
		this.actiondistance = actiondistance;
	}
	public String getTargetsteps() {
		return Targetsteps;
	}
	public void setTargetsteps(String targetsteps) {
		Targetsteps = targetsteps;
	}
	public String getTotalsteps() {
		return totalsteps;
	}
	public void setTotalsteps(String totalsteps) {
		this.totalsteps = totalsteps;
	}
	public Stepsvo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Stepsvo(List<Steps> stepss, String heat, String stepsduration,
			String runduration, String actiondistance, String targetsteps,
			String totalsteps) {
		super();
		this.stepss = stepss;
		Heat = heat;
		this.stepsduration = stepsduration;
		this.runduration = runduration;
		this.actiondistance = actiondistance;
		Targetsteps = targetsteps;
		this.totalsteps = totalsteps;
	}
	
}
