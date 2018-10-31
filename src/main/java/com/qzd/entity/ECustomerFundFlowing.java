package org.ieforex.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import java.util.Date;


/**
 * 
 * 
 * @author zero
 * @email 15638559970
 * @date 2017-08-11 16:15:46
 */
 @Table(name="e_customer_fund_flowing")
public class ECustomerFundFlowing implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @Id
	@Column(name="flowing_id")
	private Long flowingId;

    @Column(name="customer_id")
	private Long customerId;

    @Column(name="dealer_name")
	private String dealerName;

    @Column(name="acct_no")
	private String acctNo;

    @Column(name="trade_type")
	private String tradeType;

    private BigDecimal amount;

    private String describes;

    @Column(name="update_user_id")
	private Long updateUserId;

    @Column(name="update_date")
	private Date updateDate;


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
	 * 获取表字段dealer_name
	 */
	public String getDealerName() {
		return dealerName;
	}

	/**
	 * 设置表字段dealer_name
	 */
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	/**
	 * 获取表字段acct_no
	 */
	public String getAcctNo() {
		return acctNo;
	}

	/**
	 * 设置表字段acct_no
	 */
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	/**
	 * 获取表字段trade_type
	 */
	public String getTradeType() {
		return tradeType;
	}

	/**
	 * 设置表字段trade_type
	 */
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	/**
	 * 获取表字段amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * 设置表字段amount
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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
	 * 获取表字段update_user_id
	 */
	public Long getUpdateUserId() {
		return updateUserId;
	}

	/**
	 * 设置表字段update_user_id
	 */
	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}
	/**
	 * 获取表字段update_date
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * 设置表字段update_date
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
