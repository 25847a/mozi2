package com.sy.pojo;

public class GroupPhone {
	
	
	private Integer id;
	private String phone;//电话号码
	private String balance;//余额
	private String ICCID;//ICCID
	private String PUK;//PUK
	private String status;//0:未激活 1:激活
	private String restFlow;//剩余总流量
	private String totalFlow;//个人套餐总流量
	private String usedFlow;//号码已用总流量
	private String extraPkgFlow;//套餐外流量
	private String totalVoice;//号码总免费语音时长  单位为：分钟
	private String usedVoice;//号码已用语音时长
	private String restVoice;//号码剩余免费语音时长
	private String extraPkgVoice;//号码套餐外语音使用时长
	private String suspended;//0停机,1开机
	private String totalMoney;	//总充值金额		
	private String usedMoney;		//已使用的金额	
	private String lastCalculateVoice;	//已经扣费的超出语音

	
	
	public String getLastVoice() {
		return lastCalculateVoice;
	}
	public void setLastVoice(String lastCalculateVoice) {
		this.lastCalculateVoice = lastCalculateVoice;
	}
	public String getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getUsedMoney() {
		return usedMoney;
	}
	public void setUsedMoney(String usedMoney) {
		this.usedMoney = usedMoney;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getICCID() {
		return ICCID;
	}
	public void setICCID(String iCCID) {
		ICCID = iCCID;
	}
	public String getPUK() {
		return PUK;
	}
	public void setPUK(String pUK) {
		PUK = pUK;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRestFlow() {
		return restFlow;
	}
	public void setRestFlow(String restFlow) {
		this.restFlow = restFlow;
	}
	public String getTotalFlow() {
		return totalFlow;
	}
	public void setTotalFlow(String totalFlow) {
		this.totalFlow = totalFlow;
	}
	public String getUsedFlow() {
		return usedFlow;
	}
	public void setUsedFlow(String usedFlow) {
		this.usedFlow = usedFlow;
	}
	public String getExtraPkgFlow() {
		return extraPkgFlow;
	}
	public void setExtraPkgFlow(String extraPkgFlow) {
		this.extraPkgFlow = extraPkgFlow;
	}
	public String getTotalVoice() {
		return totalVoice;
	}
	public void setTotalVoice(String totalVoice) {
		this.totalVoice = totalVoice;
	}
	public String getUsedVoice() {
		return usedVoice;
	}
	public void setUsedVoice(String usedVoice) {
		this.usedVoice = usedVoice;
	}
	public String getRestVoice() {
		return restVoice;
	}
	public void setRestVoice(String restVoice) {
		this.restVoice = restVoice;
	}
	public String getExtraPkgVoice() {
		return extraPkgVoice;
	}
	public void setExtraPkgVoice(String extraPkgVoice) {
		this.extraPkgVoice = extraPkgVoice;
	}
	public String getSuspended() {
		return suspended;
	}
	public void setSuspended(String suspended) {
		this.suspended = suspended;
	}
	
	
	
}	
