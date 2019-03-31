package com.sy.vo;

import java.util.Date;

public class BloodpressureHL {
		//id
		private Integer id;
		//血压大
		private String BloodpressureMax;
		//血压小
		private String 	BloodpressureMin;
		//创建时间==更新时间
		private Date createtime;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getBloodpressureMax() {
			return BloodpressureMax;
		}
		public void setBloodpressureMax(String bloodpressureMax) {
			BloodpressureMax = bloodpressureMax;
		}
		public String getBloodpressureMin() {
			return BloodpressureMin;
		}
		public void setBloodpressureMin(String bloodpressureMin) {
			BloodpressureMin = bloodpressureMin;
		}
		public Date getCreatetime() {
			return createtime;
		}
		public void setCreatetime(Date createtime) {
			this.createtime = createtime;
		}
		
		
}
