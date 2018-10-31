package org.ieforex.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * 用户限制表
 * 
 * @author brilliance
 * @email 
 * @date 2017-05-08 14:23:27
 */
 @Table(name="e_customer_limit")
public class ECustomerLimit implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
	  * 用户限制ID
	  */
    @Id
	@Column(name="limit_id")
	private Long limitId;

    /**
	  * 用户ID
	  */
    @Column(name="customer_id")
	private Long customerId;

    /**
	  * 操作人ID
	  */
    @Column(name="operator_id")
	private Long operatorId;

    /**
	  * 操作时间
	  */
    @Column(name="operate_date")
	private Date operateDate;

    /**
	  * 限制类型ID
	  */
    @Column(name="limit_type")
	private String limitType;

    /**
	  * 摘要
	  */
    private String comment;

    /**
	  * 提现限制时间
	  */
    @Column(name="limit_date")
	private Date limitDate;


	/**
	 * 获取“用户限制ID”
	 */
	public Long getLimitId() {
		return limitId;
	}

	/**
	 * 设置“用户限制ID”
	 */
	public void setLimitId(Long limitId) {
		this.limitId = limitId;
	}
	/**
	 * 获取“用户ID”
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * 设置“用户ID”
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	/**
	 * 获取“操作人ID”
	 */
	public Long getOperatorId() {
		return operatorId;
	}

	/**
	 * 设置“操作人ID”
	 */
	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}
	/**
	 * 获取“操作时间”
	 */
	public Date getOperateDate() {
		return operateDate;
	}

	/**
	 * 设置“操作时间”
	 */
	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}
	/**
	 * 获取“限制类型ID”
	 */
	public String getLimitType() {
		return limitType;
	}

	/**
	 * 设置“限制类型ID”
	 */
	public void setLimitType(String limitType) {
		this.limitType = limitType;
	}
	/**
	 * 获取“摘要”
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * 设置“摘要”
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * 获取“提现限制时间”
	 */
	public Date getLimitDate() {
		return limitDate;
	}

	/**
	 * 设置“提现限制时间”
	 */
	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}
}
