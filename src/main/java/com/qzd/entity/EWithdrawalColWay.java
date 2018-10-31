package org.ieforex.entity;

public class EWithdrawalColWay {
	/**
	  * 提款方式
	  */
	private String currency;
	/**
	  * 提款账号
	  */
	private String acctNum;
	/**
	  * 提款金额
	  */
	private String wantAmount;
	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getAcctNum() {
		return acctNum;
	}
	public void setAcctNum(String acctNum) {
		this.acctNum = acctNum;
	}
	public String getWantAmount() {
		return wantAmount;
	}
	public void setWantAmount(String wantAmount) {
		this.wantAmount = wantAmount;
	}
	
}
