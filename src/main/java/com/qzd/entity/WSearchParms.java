package org.ieforex.entity;

public class WSearchParms {
	private String allorthree;
	private String starttime;
	private String endtime;
	private String dealerId;
	private String tradingAccount;
	private String tradeType;
	private String tradeStatus;
	private String crrentPage;
	private String moneyType;
	private String currency;
	private int offset;
	private int limit;
	private String selectTime;
	private String rebateType;
	private String igalType;
	
	public String getIgalType() {
		return igalType;
	}
	public void setIgalType(String igalType) {
		this.igalType = igalType;
	}
	public String getSelectTime() {
		return selectTime;
	}
	public void setSelectTime(String selectTime) {
		this.selectTime = selectTime;
	}
	public String getRebateType() {
		return rebateType;
	}
	public void setRebateType(String rebateType) {
		this.rebateType = rebateType;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getMoneyType() {
		return moneyType;
	}
	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}
	public String getAllorthree() {
		return allorthree;
	}
	public void setAllorthree(String allorthree) {
		this.allorthree = allorthree;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getDealerId() {
		return dealerId;
	}
	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}
	public String getTradingAccount() {
		return tradingAccount;
	}
	public void setTradingAccount(String tradingAccount) {
		this.tradingAccount = tradingAccount;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getTradeStatus() {
		return tradeStatus;
	}
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	public String getCrrentPage() {
		return crrentPage;
	}
	public void setCrrentPage(String crrentPage) {
		this.crrentPage = crrentPage;
	}
	
}
