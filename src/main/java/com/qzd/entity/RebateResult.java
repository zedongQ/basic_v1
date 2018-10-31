package org.ieforex.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import java.util.Date;

/**
 * 返佣信息结果表
 * 
 * @author brilliance
 * @email 
 * @date 2017-05-04 11:40:43
 */
 @Table(name="e_rebate_result")
public class RebateResult implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @Id
	@Column(name="rebate_result_id")
	private Long rebateResultId;

    /**
	  * 返佣信息ID
	  */
    @Column(name="rebate_id")
	private Long rebateId;

    /**
	  * 任务父类id
	  */
    @Column(name="task_id")
	private Long taskId;

    /**
	  * 用户ID
	  */
    @Column(name="customer_id")
	private Long customerId;

    /**
	  * 交易账户ID
	  */
    @Column(name="trade_acct_id")
	private Long tradeAcctId;

    /**
	  * 交易账号
	  */
    @Column(name="acct_no")
	private String acctNo;

    /**
	  * 交易商ID
	  */
    @Column(name="dealer_id")
	private Long dealerId;

    /**
	  * 返佣数据上传记录ID
	  */
    @Column(name="record_id")
	private Long recordId;

    /**
	  * 账户结余
	  */
    private BigDecimal balance;

    /**
	  * 佣金总额
	  */
    @Column(name="comm_amount")
	private BigDecimal commAmount;

    /**
	  * 返佣标准
	  */
    @Column(name="rebate_standard")
	private BigDecimal rebateStandard;

    /**
	  * 返佣金额
	  */
    @Column(name="rebate_amount")
	private BigDecimal rebateAmount;

    /**
	  * 数据状态
	  */
    @Column(name="data_status")
	private String dataStatus;

    /**
	  * 开始日期
	  */
    @Column(name="start_date")
	private Date startDate;

    /**
	  * 结束日期
	  */
    @Column(name="end_date")
	private Date endDate;

    /**
	  * 创建人ID
	  */
    @Column(name="create_user_id")
	private Long createUserId;

    /**
	  * 创建时间
	  */
    @Column(name="create_date")
	private Date createDate;

    /**
	  * 更新人ID
	  */
    @Column(name="update_user_id")
	private Long updateUserId;

    /**
	  * 更新时间
	  */
    @Column(name="update_date")
	private Date updateDate;

    /**
	  * 原始佣金
	  */
    @Column(name="original_commission")
	private BigDecimal originalCommission;


	/**
	 * 获取表字段rebate_result_id
	 */
	public Long getRebateResultId() {
		return rebateResultId;
	}

	/**
	 * 设置表字段rebate_result_id
	 */
	public void setRebateResultId(Long rebateResultId) {
		this.rebateResultId = rebateResultId;
	}
	/**
	 * 获取“返佣信息ID”
	 */
	public Long getRebateId() {
		return rebateId;
	}

	/**
	 * 设置“返佣信息ID”
	 */
	public void setRebateId(Long rebateId) {
		this.rebateId = rebateId;
	}
	/**
	 * 获取“任务父类id”
	 */
	public Long getTaskId() {
		return taskId;
	}

	/**
	 * 设置“任务父类id”
	 */
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
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
	 * 获取“交易账户ID”
	 */
	public Long getTradeAcctId() {
		return tradeAcctId;
	}

	/**
	 * 设置“交易账户ID”
	 */
	public void setTradeAcctId(Long tradeAcctId) {
		this.tradeAcctId = tradeAcctId;
	}
	/**
	 * 获取“交易账号”
	 */
	public String getAcctNo() {
		return acctNo;
	}

	/**
	 * 设置“交易账号”
	 */
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	/**
	 * 获取“交易商ID”
	 */
	public Long getDealerId() {
		return dealerId;
	}

	/**
	 * 设置“交易商ID”
	 */
	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}
	/**
	 * 获取“返佣数据上传记录ID”
	 */
	public Long getRecordId() {
		return recordId;
	}

	/**
	 * 设置“返佣数据上传记录ID”
	 */
	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}
	/**
	 * 获取“账户结余”
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * 设置“账户结余”
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	/**
	 * 获取“佣金总额”
	 */
	public BigDecimal getCommAmount() {
		return commAmount;
	}

	/**
	 * 设置“佣金总额”
	 */
	public void setCommAmount(BigDecimal commAmount) {
		this.commAmount = commAmount;
	}
	/**
	 * 获取“返佣标准”
	 */
	public BigDecimal getRebateStandard() {
		return rebateStandard;
	}

	/**
	 * 设置“返佣标准”
	 */
	public void setRebateStandard(BigDecimal rebateStandard) {
		this.rebateStandard = rebateStandard;
	}
	/**
	 * 获取“返佣金额”
	 */
	public BigDecimal getRebateAmount() {
		return rebateAmount;
	}

	/**
	 * 设置“返佣金额”
	 */
	public void setRebateAmount(BigDecimal rebateAmount) {
		this.rebateAmount = rebateAmount;
	}
	/**
	 * 获取“数据状态”
	 */
	public String getDataStatus() {
		return dataStatus;
	}

	/**
	 * 设置“数据状态”
	 */
	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}
	/**
	 * 获取“开始日期”
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * 设置“开始日期”
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * 获取“结束日期”
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置“结束日期”
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * 获取“创建人ID”
	 */
	public Long getCreateUserId() {
		return createUserId;
	}

	/**
	 * 设置“创建人ID”
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取“创建时间”
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * 设置“创建时间”
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取“更新人ID”
	 */
	public Long getUpdateUserId() {
		return updateUserId;
	}

	/**
	 * 设置“更新人ID”
	 */
	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}
	/**
	 * 获取“更新时间”
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * 设置“更新时间”
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * 获取“原始佣金”
	 */
	public BigDecimal getOriginalCommission() {
		return originalCommission;
	}

	/**
	 * 设置“原始佣金”
	 */
	public void setOriginalCommission(BigDecimal originalCommission) {
		this.originalCommission = originalCommission;
	}
}
