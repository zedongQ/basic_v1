package org.ieforex.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 交易账号审核表
 * 
 * @author brilliance
 * @email 
 * @date 2017-04-28 16:54:54
 */
 @Table(name="e_trade_acct_task")
public class ETradeAcctTask implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
	  * 交易账户ID
	  */
    @Id
	@Column(name="trade_acct_task_id")
	private Long tradeAcctTaskId;

    /**
	  * 客户编号
	  */
    @Column(name="customer_id")
	private Long customerId;

    /**
	  * 交易商编号
	  */
    @Column(name="dealer_id")
	private Long dealerId;
    /**
     * 交易账号的交易商编号
     */
    @Transient
    private Long dealerTaskId;
     /**
	  * 交易商中文名
	  */
    @Transient
    private String dealerChnName;
    /**
     * 交易商Logo
     */
    @Transient
    private String dealerLogoUrl;
    /**
     /**
     * 交易商英文名
     */
    @Transient
    private String dealerEnName;
    /**
	  * 数据来源
	  */
    @Column(name="data_source")
    private String dataSource;
    /**
	  * 交易账号ID
	  */
    @Transient
    private Long tradeAcctId;
    /**
	  * 网站名
	  */
    @Transient
    private String forumName;
    /**
	  * 账号邮箱
	  */
    @Transient
    private String acctEmail; 
    @Transient
    private String engName; 
    @Transient
    private String acctName; 
    @Transient
    private String cardNo; 
    @Transient
    private Long acctCustomerId; 
    @Transient
    private BigDecimal uncollected;
    /**
	  * 交易账号
	  */
    @Column(name="acct_no")
	private String acctNo;
    /**
     * 交易账号_审核
     */
    @Transient
    private String acctTaskNo;
    @Transient
    private String loginPhone;
    /**
	  * 开户姓名
	  */
    @Column(name="holder_name")
	private String holderName;

    /**
	  * 开户邮箱
	  */
    @Column(name="holder_email")
	private String holderEmail;
    @Transient
    private String loginEmail;
    /**
	  * 开户日期
	  */
    @Column(name="acct_create_date")
	private Date acctCreateDate;

    /**
	  * 客服编号
	  */
    @Column(name="service_id")
	private Long serviceId;

    /**
	  * 审核人
	  */
    @Column(name="check_user_id")
	private Long checkUserId;

    /**
	  * 审核状态
	  */
    @Column(name="check_status")
	private String checkStatus;

    /**
	  * 提交时间
	  */
    @Column(name="submit_date")
	private Date submitDate;

    /**
	  * 数据状态
	  */
    @Transient
	private String dataStatus;
    /**
     * 审核建议
     */
    @Transient
    private String taskSuggest;

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
	  * 状态说明
	  */
    @Transient
	private String stateNote;

    /**
	  * 审核原因详情
	  */
    @Column(name="reason_detail")
	private String reasonDetail;
    /**
     * 审核原因类型
     */
    @Column(name="reason_type")
    private String reasonType;

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

    
    
	public String getDealerLogoUrl() {
		return dealerLogoUrl;
	}

	public void setDealerLogoUrl(String dealerLogoUrl) {
		this.dealerLogoUrl = dealerLogoUrl;
	}

	/**
	 * 获取“交易账户ID”
	 */
	public Long getTradeAcctTaskId() {
		return tradeAcctTaskId;
	}

	/**
	 * 设置“交易账户ID”
	 */
	public void setTradeAcctTaskId(Long tradeAcctTaskId) {
		this.tradeAcctTaskId = tradeAcctTaskId;
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
	 * 获取“交易商编号”
	 */
	public Long getDealerId() {
		return dealerId;
	}

	/**
	 * 设置“交易商编号”
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
	 * 获取“开户姓名”
	 */
	public String getHolderName() {
		return holderName;
	}

	/**
	 * 设置“开户姓名”
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
	 * 获取“审核人”
	 */
	public Long getCheckUserId() {
		return checkUserId;
	}
	/**
	 * 设置“审核人”
	 */
	public void setCheckUserId(Long checkUserId) {
		this.checkUserId = checkUserId;
	}
	/**
	 * 获取“审核状态”
	 */
	public String getCheckStatus() {
		return checkStatus;
	}
	
	/**
	 * 设置“审核状态”
	 */
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	/**
	 * 获取“提交时间”
	 */
	public Date getSubmitDate() {
		return submitDate;
	}

	/**
	 * 设置“提交时间”
	 */
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
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
	 * 获取“状态说明”
	 */
	public String getStateNote() {
		return stateNote;
	}

	/**
	 * 设置“状态说明”
	 */
	public void setStateNote(String stateNote) {
		this.stateNote = stateNote;
	}
	public String getForumName() {
		return forumName;
	}

	public void setForumName(String forumName) {
		this.forumName = forumName;
	}

	public String getIsSameName() {
		return isSameName;
	}

	public void setIsSameName(String isSameName) {
		this.isSameName = isSameName;
	}

	public String getIsAgency() {
		return isAgency;
	}

	public void setIsAgency(String isAgency) {
		this.isAgency = isAgency;
	}

	public String getReasonDetail() {
		return reasonDetail;
	}

	public void setReasonDetail(String reasonDetail) {
		this.reasonDetail = reasonDetail;
	}

	public String getReasonType() {
		return reasonType;
	}

	public void setReasonType(String reasonType) {
		this.reasonType = reasonType;
	}

	public String getAcctTaskNo() {
		return acctTaskNo;
	}

	public void setAcctTaskNo(String acctTaskNo) {
		this.acctTaskNo = acctTaskNo;
	}

	public String getTaskSuggest() {
		return taskSuggest;
	}

	public void setTaskSuggest(String taskSuggest) {
		this.taskSuggest = taskSuggest;
	}

	public String getLoginPhone() {
		return loginPhone;
	}

	public void setLoginPhone(String loginPhone) {
		this.loginPhone = loginPhone;
	}

	public String getAcctEmail() {
		return acctEmail;
	}

	public void setAcctEmail(String acctEmail) {
		this.acctEmail = acctEmail;
	}

	public String getLoginEmail() {
		return loginEmail;
	}

	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getEngName() {
		return engName;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Long getTradeAcctId() {
		return tradeAcctId;
	}

	public void setTradeAcctId(Long tradeAcctId) {
		this.tradeAcctId = tradeAcctId;
	}

	public Long getAcctCustomerId() {
		return acctCustomerId;
	}

	public void setAcctCustomerId(Long acctCustomerId) {
		this.acctCustomerId = acctCustomerId;
	}

	public BigDecimal getUncollected() {
		return uncollected;
	}

	public void setUncollected(BigDecimal uncollected) {
		this.uncollected = uncollected;
	}

	public Long getDealerTaskId() {
		return dealerTaskId;
	}

	public void setDealerTaskId(Long dealerTaskId) {
		this.dealerTaskId = dealerTaskId;
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
	
}
