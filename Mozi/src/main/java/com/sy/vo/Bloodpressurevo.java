package com.sy.vo;

import java.util.List;

/**血压
 * @author Administrator
 *
 */
public class Bloodpressurevo {
    private List<Bloodpressure> bs ;
    private String  average;
    private String bhigh;
    private String blow;
	public List<Bloodpressure> getBs() {
		return bs;
	}
	public void setBs(List<Bloodpressure> bs) {
		this.bs = bs;
	}
	public String getAverage() {
		return average;
	}
	public void setAverage(String average) {
		this.average = average;
	}
	public String getBhigh() {
		return bhigh;
	}
	public void setBhigh(String bhigh) {
		this.bhigh = bhigh;
	}
	public String getBlow() {
		return blow;
	}
	public void setBlow(String blow) {
		this.blow = blow;
	}
	public Bloodpressurevo(List<Bloodpressure> bs, String average,
			String bhigh, String blow) {
		super();
		this.bs = bs;
		this.average = average;
		this.bhigh = bhigh;
		this.blow = blow;
	}
	public Bloodpressurevo() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
