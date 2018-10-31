package org.ieforex.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.util.Date;

/**
 * 交易账号表
 * 
 * @author brilliance
 * @email 
 * @date 2017-06-01 16:55:09
 */
 @Table(name="e_trade_acct")
public class ETradeAcct implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
	  * 交易账号ID
	  */
    @Id
	@Column(name="trade_acct_id")
	private Long tradeAcctId;

    /**
	  * 客户编号
	  */
    @Column(name="customer_id")
	private Long customerId;

    /**
	  * 交易商ID
	  */
    @Column(name="dealer_id")
	private Long dealerId;
    /**
	  * 交易商中文名
	  */
    @Transient
    private String dealerChnName;
    /**
	  * 交易商英文名
	  */
    @Transient
    private String dealerEnName;
    /**
	  * 交易商LOGO路径
	  */
    @Transient
    private String dealerLogoUrl;

    /**
	  * 交易账号
	  */
    @Column(name="acct_no")
	private String acctNo;

    /**
	  * 开户人姓名
	  */
    @Column(name="holder_name")
	private String holderName;

    /**
	  * 开户邮箱
	  */
    @Column(name="holder_email")
	private String holderEmail;

    /**
	  * 开户日期
	  */
    @Column(name="acct_create_date")
	private Date acctCreateDate;

    /**
	  * 金额总计
	  */
    private BigDecimal amount;

    /**
	  * 可用金额
	  */
    private BigDecimal available;

    /**
	  * 冻结金额
	  */
    private BigDecimal freeze;

    /**
	  * 未到账金额
	  */
    private BigDecimal uncollected;

    /**
	  * 累计返佣金额
	  */
    @Column(name="rebateAmount")
	private BigDecimal rebateamount;

    /**
	  * 累计体现金额
	  */
    @Column(name="withdrawAmount")
	private BigDecimal withdrawamount;

    /**
	  * 客服编号
	  */
    @Column(name="service_id")
	private Long serviceId;

    /**
	  * 数据来源
	  */
    @Column(name="data_source")
	private String dataSource;

    /**
	  * 创建人员
	  */
    @Column(name="creater_user_id")
	private Long createUserId;

    /**
	  * 创建时间
	  */
    @Column(name="create_date")
	private Date createDate;

    /**
	  * 更新人员
	  */
    @Column(name="updater_user_id")
	private Long updaterUserId;

    /**
	  * 更新时间
	  */
    @Column(name="update_date")
	private Date updateDate;

    /**
	  * 姓名是否一致
	  */
    @Column(name="is_same_name")
	private String isSameName;

    /**
	  * 是否代理关系
	  */
    @Column(name="is_agency")
	private String isAgency;

    /**
	  * 交易账号审核任务编号
	  */
    @Column(name="trade_acct_task_id")
	private Long tradeAcctTaskId;


	/**
	 * 获取“交易账号ID”
	 */
	public Long getTradeAcctId() {
		return tradeAcctId;
	}

	/**
	 * 设置“交易账号ID”
	 */
	public void setTradeAcctId(Long tradeAcctId) {
		this.tradeAcctId = tradeAcctId;
	}
	/**
	 * 获取“客户编号”
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * 设置“客户编号”
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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
	 * 获取“开户人姓名”
	 */
	public String getHolderName() {
		return holderName;
	}

	/**
	 * 设置“开户人姓名”
	 */
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
	/**
	 * 获取“开户邮箱”
	 */
	public String getHolderEmail() {
		return holderEmail;
	}

	/**
	 * 设置“开户邮箱”
	 */
	public void setHolderEmail(String holderEmail) {
		this.holderEmail = holderEmail;
	}
	/**
	 * 获取“开户日期”
	 */
	public Date getAcctCreateDate() {
		return acctCreateDate;
	}

	/**
	 * 设置“开户日期”
	 */
	public void setAcctCreateDate(Date acctCreateDate) {
		this.acctCreateDate = acctCreateDate;
	}
	/**
	 * 获取“金额总计”
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * 设置“金额总计”
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * 获取“可用金额”
	 */
	public BigDecimal getAvailable() {
		return available;
	}

	/**
	 * 设置“可用金额”
	 */
	public void setAvailable(BigDecimal available) {
		this.available = available;
	}
	/**
	 * 获取“冻结金额”
	 */
	public BigDecimal getFreeze() {
		return freeze;
	}

	/**
	 * 设置“冻结金额”
	 */
	public void setFreeze(BigDecimal freeze) {
		this.freeze = freeze;
	}
	/**
	 * 获取“未到账金额”
	 */
	public BigDecimal getUncollected() {
		return uncollected;
	}

	/**
	 * 设置“未到账金额”
	 */
	public void setUncollected(BigDecimal uncollected) {
		this.uncollected = uncollected;
	}
	/**
	 * 获取“累计返佣金额”
	 */
	public BigDecimal getRebateamount() {
		return rebateamount;
	}

	/**
	 * 设置“累计返佣金额”
	 */
	public void setRebateamount(BigDecimal rebateamount) {
		this.rebateamount = rebateamount;
	}
	/**
	 * 获取“累计体现金额”
	 */
	public BigDecimal getWithdrawamount() {
		return withdrawamount;
	}

	/**
	 * 设置“累计体现金额”
	 */
	public void setWithdrawamount(BigDecimal withdrawamount) {
		this.withdrawamount = withdrawamount;
	}
	/**
	 * 获取“客服编号”
	 */
	public Long getServiceId() {
		return serviceId;
	}

	/**
	 * 设置“客服编号”
	 */
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	/**
	 * 获取“数据来源”
	 */
	public String getDataSource() {
		return dataSource;
	}

	/**
	 * 设置“数据来源”
	 */
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	/**
	 * 获取“创建人员”
	 */
	public Long getCreateUserId() {
		return createUserId;
	}

	/**
	 * 设置“创建人员”
	 */
	public void setCreateUserId(Long createrUserId) {
		this.createUserId = createrUserId;
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
	 * 获取“更新人员”
	 */
	public Long getUpdaterUserId() {
		return updaterUserId;
	}

	/**
	 * 设置“更新人员”
	 */
	public void setUpdaterUserId(Long updaterUserId) {
		this.updaterUserId = updaterUserId;
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
	 * 获取“姓名是否一致”
	 */
	public String getIsSameName() {
		return isSameName;
	}

	/**
	 * 设置“姓名是否一致”
	 */
	public void setIsSameName(String isSameName) {
		this.isSameName = isSameName;
	}
	/**
	 * 获取“是否代理关系”
	 */
	public String getIsAgency() {
		return isAgency;
	}

	/**
	 * 设置“是否代理关系”
	 */
	public void setIsAgency(String isAgency) {
		this.isAgency = isAgency;
	}
	/**
	 * 获取“交易账号审核任务编号”
	 */
	public Long getTradeAcctTaskId() {
		return tradeAcctTaskId;
	}

	/**
	 * 设置“交易账号审核任务编号”
	 */
	public void setTradeAcctTaskId(Long tradeAcctTaskId) {
		this.tradeAcctTaskId = tradeAcctTaskId;
	}

	public String getDealerChnName() {
		return dealerChnName;
	}

	public void setDealerChnName(String dealerChnName) {
		this.dealerChnName = dealerChnName;
	}

	public String getDealerEnName() {
		return dealerEnName;
	}

	public void setDealerEnName(String dealerEnName) {
		this.dealerEnName = dealerEnName;
	}

	public String getDealerLogoUrl() {
		return dealerLogoUrl;
	}

	public void setDealerLogoUrl(String dealerLogoUrl) {
		this.dealerLogoUrl = dealerLogoUrl;
	}
	
}
