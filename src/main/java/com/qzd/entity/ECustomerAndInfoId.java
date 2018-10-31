package org.ieforex.entity;

public class ECustomerAndInfoId {
	
	private Long basicId;
	private Long customerId;
	private Long accountId;
	private String dataStatus;
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getBasicId() {
		return basicId;
	}
	public void setBasicId(Long basicId) {
		this.basicId = basicId;
	}
	public String getDataStatus() {
		return dataStatus;
	}
	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
}
