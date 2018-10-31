package org.ieforex.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import org.ieforex.enums.IntegralType;

@Table(name="integral_flowing")
public class IntegralFlowing implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @Id
	@Column(name="flowing_id")
	private Long flowingId;

    @Column(name="customer_id")
	private Long customerId;

    private Integer amount;

    private String type;

    private String describes;

    @Column(name="insert_date")
	private Date insertDate;
    
    @Column(name="dealer_id")
    private Long dealerId;
    
    @Column(name="acct_no")
    private String acctNo;
    
    
    
	public Long getDealerId() {
		return dealerId;
	}

	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	/**
	 * 获取表字段flowing_id
	 */
	public Long getFlowingId() {
		return flowingId;
	}

	/**
	 * 设置表字段flowing_id
	 */
	public void setFlowingId(Long flowingId) {
		this.flowingId = flowingId;
	}
	/**
	 * 获取表字段customer_id
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * 设置表字段customer_id
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	/**
	 * 获取表字段amount
	 */
	public Integer getAmount() {
		return amount;
	}

	/**
	 * 设置表字段amount
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	/**
	 * 获取表字段type
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置表字段type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 设置表字段type的描述
	 */
	public String getTypeName() {
		if(type!=null){
			IntegralType valueByKey = IntegralType.getMapValue(this.type);
			 String value = valueByKey.describe();
			 return value;
		}
		 return null;
	}
	
	/**
	 * 获取表字段describes
	 */
	public String getDescribes() {
		return describes;
	}

	/**
	 * 设置表字段describes
	 */
	public void setDescribes(String describes) {
		this.describes = describes;
	}
	/**
	 * 获取表字段insert_date
	 */
	public Date getInsertDate() {
		return insertDate;
	}

	/**
	 * 设置表字段insert_date
	 */
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
}
