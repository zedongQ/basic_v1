package org.ieforex.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 提现申请表
 * 
 * @author brilliance
 * @email 
 * @date 2017-04-26 14:02:16
 */
 @Table(name="e_withdrawal")
public class EWithdrawal implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @Id
	@Column(name="withdrawal_id")
	private Long withdrawalId;

    /**
	  * 提现时间
	  */
    @Column(name="withdrawal_date")
	private Date withdrawalDate;
    /**
     * 登录IP
     */
    @Transient
    private String loginIp;
    /**
     * 登录时间
     */
    @Transient
    private Date loginTime;

    /**
	  * 提现金额
	  */
    @Column(name="withdrawal_amount")
	private BigDecimal withdrawalAmount;

    /**
	  * 提现币种
	  */
    @Column(name="withdrawal_currency")
	private String withdrawalCurrency;

    /**
	  * 汇率
	  */
    @Column(name="exchange_rate")
	private BigDecimal exchangeRate;

    /**
	  * 提现方式
	  */
    @Column(name="withdrawal_way")
	private String withdrawalWay;

    /**
	  * 提现费用
	  */
    @Column(name="withdrawal_fee")
	private BigDecimal withdrawalFee;

    /**
	  * 开户人姓名
	  */
    @Column(name="holder_name")
	private String holderName;

    /**
	  * 收款人姓名
	  */
    @Column(name="payee_name")
	private String payeeName;

    /**
	  * 收款人身份证号
	  */
    @Column(name="payee_card_no")
	private String payeeCardNo;

    /**
	  * 收款人Email
	  */
    @Column(name="payee_email")
	private String payeeEmail;

    /**
	  * 收款人联系电话
	  */
    @Column(name="payee_phone")
	private String payeePhone;

    /**
	  * 收款人通信地址
	  */
    @Column(name="payee_address")
	private String payeeAddress;

    /**
	  * 收款人银行名称
	  */
    @Column(name="payee_bank_name")
	private String payeeBankName;

    /**
	  * 收款人银行账号
	  */
    @Column(name="payee_bank_acct_no")
	private String payeeBankAcctNo;

    /**
	  * 收款人支付宝账号
	  */
    @Column(name="payee_alipay_acct_no")
	private String payeeAlipayAcctNo;

    /**
	  * 收款人英文姓名
	  */
    @Column(name="payee_eng_name")
	private String payeeEngName;

    /**
	  * 收款人MB账号
	  */
    @Column(name="payee_mb_acct_no")
	private String payeeMbAcctNo;

    /**
	  * 收款人电汇银行名称
	  */
    @Column(name="payee_tt_bank_name")
	private String payeeTtBankName;

    /**
	  * 收款人电汇银行账号
	  */
    @Column(name="payee_tt_bank_acct_no")
	private String payeeTtBankAcctNo;

    /**
	  * 收款人银行SWIFT代码
	  */
    @Column(name="payee_tt_bank_swift_code")
	private String payeeTtBankSwiftCode;

    /**
	  * 收款人电汇通信地址
	  */
    @Column(name="payee_tt_address")
	private String payeeTtAddress;

    /**
	  * 收款人贝宝账号
	  */
    @Column(name="payee_paypal_no")
	private String payeePaypalNo;

    /**
	  * 创建人员
	  */
    @Column(name="create_user_id")
	private Long createUserId;

    /**
	  * 创建时间
	  */
    @Column(name="create_date")
	private Date createDate;

    /**
	  * 更新人员
	  */
    @Column(name="update_user_id")
	private Long updateUserId;

    /**
	  * 更新时间
	  */
    @Column(name="update_date")
	private Date updateDate;

    /**
	  * 状态
	  */
    private String status;

    /**
	  * 备注
	  */
    private String memo;
    
    /**
	  * 英文备注
	  */
    @Column(name="memo_ch")
    private String memoCh;
    /**
	  * 提现资格审核日期
	  */
    @Column(name="check_date")
	private Date checkDate;

    /**
	  * 提现资格审核人员
	  */
    @Column(name="check_user_id")
	private Long checkUserId;

    /**
	  * 初审日期
	  */
    @Column(name="check_first_date")
	private Date checkFirstDate;

    /**
	  * 初审人员
	  */
    @Column(name="check_first_user_id")
	private Long checkFirstUserId;

    /**
	  * 复审日期
	  */
    @Column(name="check_second_date")
	private Date checkSecondDate;

    /**
	  * 复审人员
	  */
    @Column(name="check_second_user_id")
	private Long checkSecondUserId;

    /**
	  * 客户编号
	  */
    @Column(name="customer_id")
	private Long customerId;

    /**
	  * 用户手机号
	  */
    @Column(name="login_phone")
	private String loginPhone;

    /**
	  * 用户邮箱
	  */
    @Column(name="login_email")
	private String loginEmail;

    /**
	  * 提现类型
	  */
    @Column(name="withdrawal_type")
	private String withdrawalType;

    /**
	  * 提现类型说明
	  */
    @Column(name="withdrawal_type_desc")
	private String withdrawalTypeDesc;

    /**
	  * 提现审核建议
	  */
    @Column(name="withdrawal_advise")
	private String withdrawalAdvise;

    /**
	  * 应付提现金额
	  */
    @Column(name="withdrawal_amount_currency")
	private BigDecimal withdrawalAmountCurrency;

    /**
	  * 实际支付金额
	  */
    @Column(name="payment_amount")
	private BigDecimal paymentAmount;

    /**
	  * 实际支付币种
	  */
    @Column(name="payment_currency")
	private String paymentCurrency;

    /**
	  * 实际支付时间
	  */
    @Column(name="payment_date")
	private Date paymentDate;

    /**
	  * 提现编号
	  */
    @Column(name="withdrawal_no")
	private String withdrawalNo;
    
    /**
	  * 折合人民币金额
	  */
   @Column(name="amount_rmb")
	private BigDecimal amountRmb;
    
    /**
     * 用户信息
     */
    @Transient
    private ECustomer customer;
    
    /**
     * 本笔冻结金额（合计美元）
     */
    @Transient
    private BigDecimal freezeDollar;
    
    /**
     * 任务编号
     */
    @Transient
    private String taskName;
    
    /**
     * 网站名
     */
    @Transient
    private String forumName;
    
    /**
	  * 审核日期
	  */
    @Transient
	private Date checkDateTime;


	/**
	 * 获取表字段withdrawal_id
	 */
	public Long getWithdrawalId() {
		return withdrawalId;
	}

	/**
	 * 设置表字段withdrawal_id
	 */
	public void setWithdrawalId(Long withdrawalId) {
		this.withdrawalId = withdrawalId;
	}
	/**
	 * 获取“提现时间”
	 */
	public Date getWithdrawalDate() {
		return withdrawalDate;
	}

	/**
	 * 设置“提现时间”
	 */
	public void setWithdrawalDate(Date withdrawalDate) {
		this.withdrawalDate = withdrawalDate;
	}
	/**
	 * 获取“提现金额”
	 */
	public BigDecimal getWithdrawalAmount() {
		return withdrawalAmount;
	}

	/**
	 * 设置“提现金额”
	 */
	public void setWithdrawalAmount(BigDecimal withdrawalAmount) {
		this.withdrawalAmount = withdrawalAmount;
	}
	/**
	 * 获取“提现币种”
	 */
	public String getWithdrawalCurrency() {
		return withdrawalCurrency;
	}

	/**
	 * 设置“提现币种”
	 */
	public void setWithdrawalCurrency(String withdrawalCurrency) {
		this.withdrawalCurrency = withdrawalCurrency;
	}
	/**
	 * 获取“汇率”
	 */
	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	/**
	 * 设置“汇率”
	 */
	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	/**
	 * 获取“提现方式”
	 */
	public String getWithdrawalWay() {
		return withdrawalWay;
	}

	/**
	 * 设置“提现方式”
	 */
	public void setWithdrawalWay(String withdrawalWay) {
		this.withdrawalWay = withdrawalWay;
	}
	/**
	 * 获取“提现费用”
	 */
	public BigDecimal getWithdrawalFee() {
		return withdrawalFee;
	}

	/**
	 * 设置“提现费用”
	 */
	public void setWithdrawalFee(BigDecimal withdrawalFee) {
		this.withdrawalFee = withdrawalFee;
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
	 * 获取“收款人姓名”
	 */
	public String getPayeeName() {
		return payeeName;
	}

	/**
	 * 设置“收款人姓名”
	 */
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	/**
	 * 获取“收款人身份证号”
	 */
	public String getPayeeCardNo() {
		return payeeCardNo;
	}

	/**
	 * 设置“收款人身份证号”
	 */
	public void setPayeeCardNo(String payeeCardNo) {
		this.payeeCardNo = payeeCardNo;
	}
	/**
	 * 获取“收款人Email”
	 */
	public String getPayeeEmail() {
		return payeeEmail;
	}

	/**
	 * 设置“收款人Email”
	 */
	public void setPayeeEmail(String payeeEmail) {
		this.payeeEmail = payeeEmail;
	}
	/**
	 * 获取“收款人联系电话”
	 */
	public String getPayeePhone() {
		return payeePhone;
	}

	/**
	 * 设置“收款人联系电话”
	 */
	public void setPayeePhone(String payeePhone) {
		this.payeePhone = payeePhone;
	}
	/**
	 * 获取“收款人通信地址”
	 */
	public String getPayeeAddress() {
		return payeeAddress;
	}

	/**
	 * 设置“收款人通信地址”
	 */
	public void setPayeeAddress(String payeeAddress) {
		this.payeeAddress = payeeAddress;
	}
	/**
	 * 获取“收款人银行名称”
	 */
	public String getPayeeBankName() {
		return payeeBankName;
	}

	/**
	 * 设置“收款人银行名称”
	 */
	public void setPayeeBankName(String payeeBankName) {
		this.payeeBankName = payeeBankName;
	}
	/**
	 * 获取“收款人银行账号”
	 */
	public String getPayeeBankAcctNo() {
		return payeeBankAcctNo;
	}

	/**
	 * 设置“收款人银行账号”
	 */
	public void setPayeeBankAcctNo(String payeeBankAcctNo) {
		this.payeeBankAcctNo = payeeBankAcctNo;
	}
	/**
	 * 获取“收款人支付宝账号”
	 */
	public String getPayeeAlipayAcctNo() {
		return payeeAlipayAcctNo;
	}

	/**
	 * 设置“收款人支付宝账号”
	 */
	public void setPayeeAlipayAcctNo(String payeeAlipayAcctNo) {
		this.payeeAlipayAcctNo = payeeAlipayAcctNo;
	}
	/**
	 * 获取“收款人英文姓名”
	 */
	public String getPayeeEngName() {
		return payeeEngName;
	}

	/**
	 * 设置“收款人英文姓名”
	 */
	public void setPayeeEngName(String payeeEngName) {
		this.payeeEngName = payeeEngName;
	}
	/**
	 * 获取“收款人MB账号”
	 */
	public String getPayeeMbAcctNo() {
		return payeeMbAcctNo;
	}

	/**
	 * 设置“收款人MB账号”
	 */
	public void setPayeeMbAcctNo(String payeeMbAcctNo) {
		this.payeeMbAcctNo = payeeMbAcctNo;
	}
	/**
	 * 获取“收款人电汇银行名称”
	 */
	public String getPayeeTtBankName() {
		return payeeTtBankName;
	}

	/**
	 * 设置“收款人电汇银行名称”
	 */
	public void setPayeeTtBankName(String payeeTtBankName) {
		this.payeeTtBankName = payeeTtBankName;
	}
	/**
	 * 获取“收款人电汇银行账号”
	 */
	public String getPayeeTtBankAcctNo() {
		return payeeTtBankAcctNo;
	}

	/**
	 * 设置“收款人电汇银行账号”
	 */
	public void setPayeeTtBankAcctNo(String payeeTtBankAcctNo) {
		this.payeeTtBankAcctNo = payeeTtBankAcctNo;
	}
	/**
	 * 获取“收款人银行SWIFT代码”
	 */
	public String getPayeeTtBankSwiftCode() {
		return payeeTtBankSwiftCode;
	}

	/**
	 * 设置“收款人银行SWIFT代码”
	 */
	public void setPayeeTtBankSwiftCode(String payeeTtBankSwiftCode) {
		this.payeeTtBankSwiftCode = payeeTtBankSwiftCode;
	}
	/**
	 * 获取“收款人电汇通信地址”
	 */
	public String getPayeeTtAddress() {
		return payeeTtAddress;
	}

	/**
	 * 设置“收款人电汇通信地址”
	 */
	public void setPayeeTtAddress(String payeeTtAddress) {
		this.payeeTtAddress = payeeTtAddress;
	}
	/**
	 * 获取“收款人贝宝账号”
	 */
	public String getPayeePaypalNo() {
		return payeePaypalNo;
	}

	/**
	 * 设置“收款人贝宝账号”
	 */
	public void setPayeePaypalNo(String payeePaypalNo) {
		this.payeePaypalNo = payeePaypalNo;
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
	 * 获取“更新人员”
	 */
	public Long getUpdateUserId() {
		return updateUserId;
	}

	/**
	 * 设置“更新人员”
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
	 * 获取“状态”
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 设置“状态”
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取“备注”
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * 设置“备注”
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	/**
	 * 获取“提现资格审核日期”
	 */
	public Date getCheckDate() {
		return checkDate;
	}

	/**
	 * 设置“提现资格审核日期”
	 */
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	/**
	 * 获取“提现资格审核人员”
	 */
	public Long getCheckUserId() {
		return checkUserId;
	}

	/**
	 * 设置“提现资格审核人员”
	 */
	public void setCheckUserId(Long checkUserId) {
		this.checkUserId = checkUserId;
	}
	/**
	 * 获取“初审日期”
	 */
	public Date getCheckFirstDate() {
		return checkFirstDate;
	}

	/**
	 * 设置“初审日期”
	 */
	public void setCheckFirstDate(Date checkFirstDate) {
		this.checkFirstDate = checkFirstDate;
	}
	/**
	 * 获取“初审人员”
	 */
	public Long getCheckFirstUserId() {
		return checkFirstUserId;
	}

	/**
	 * 设置“初审人员”
	 */
	public void setCheckFirstUserId(Long checkFirstUserId) {
		this.checkFirstUserId = checkFirstUserId;
	}
	/**
	 * 获取“复审日期”
	 */
	public Date getCheckSecondDate() {
		return checkSecondDate;
	}

	/**
	 * 设置“复审日期”
	 */
	public void setCheckSecondDate(Date checkSecondDate) {
		this.checkSecondDate = checkSecondDate;
	}
	/**
	 * 获取“复审人员”
	 */
	public Long getCheckSecondUserId() {
		return checkSecondUserId;
	}

	/**
	 * 设置“复审人员”
	 */
	public void setCheckSecondUserId(Long checkSecondUserId) {
		this.checkSecondUserId = checkSecondUserId;
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
	 * 获取“用户手机号”
	 */
	public String getLoginPhone() {
		return loginPhone;
	}

	/**
	 * 设置“用户手机号”
	 */
	public void setLoginPhone(String loginPhone) {
		this.loginPhone = loginPhone;
	}
	/**
	 * 获取“用户邮箱”
	 */
	public String getLoginEmail() {
		return loginEmail;
	}

	/**
	 * 设置“用户邮箱”
	 */
	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}
	/**
	 * 获取“提现类型”
	 */
	public String getWithdrawalType() {
		return withdrawalType;
	}

	/**
	 * 设置“提现类型”
	 */
	public void setWithdrawalType(String withdrawalType) {
		this.withdrawalType = withdrawalType;
	}
	/**
	 * 获取“提现类型说明”
	 */
	public String getWithdrawalTypeDesc() {
		return withdrawalTypeDesc;
	}

	/**
	 * 设置“提现类型说明”
	 */
	public void setWithdrawalTypeDesc(String withdrawalTypeDesc) {
		this.withdrawalTypeDesc = withdrawalTypeDesc;
	}
	/**
	 * 获取“提现审核建议”
	 */
	public String getWithdrawalAdvise() {
		return withdrawalAdvise;
	}

	/**
	 * 设置“提现审核建议”
	 */
	public void setWithdrawalAdvise(String withdrawalAdvise) {
		this.withdrawalAdvise = withdrawalAdvise;
	}
	/**
	 * 获取“应付提现金额”
	 */
	public BigDecimal getWithdrawalAmountCurrency() {
		return withdrawalAmountCurrency;
	}

	/**
	 * 设置“应付提现金额”
	 */
	public void setWithdrawalAmountCurrency(BigDecimal withdrawalAmountCurrency) {
		this.withdrawalAmountCurrency = withdrawalAmountCurrency;
	}
	/**
	 * 获取“实际支付金额”
	 */
	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	/**
	 * 设置“实际支付金额”
	 */
	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	/**
	 * 获取“实际支付币种”
	 */
	public String getPaymentCurrency() {
		return paymentCurrency;
	}

	/**
	 * 设置“实际支付币种”
	 */
	public void setPaymentCurrency(String paymentCurrency) {
		this.paymentCurrency = paymentCurrency;
	}
	/**
	 * 获取“实际支付时间”
	 */
	public Date getPaymentDate() {
		return paymentDate;
	}

	/**
	 * 设置“实际支付时间”
	 */
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	/**
	 * 获取“提现编号”
	 */
	public String getWithdrawalNo() {
		return withdrawalNo;
	}

	/**
	 * 设置“提现编号”
	 */
	public void setWithdrawalNo(String withdrawalNo) {
		this.withdrawalNo = withdrawalNo;
	}

	public BigDecimal getAmountRmb() {
		return amountRmb;
	}

	public void setAmountRmb(BigDecimal amountRmb) {
		this.amountRmb = amountRmb;
	}

	public ECustomer getCustomer() {
		return customer;
	}

	public void setCustomer(ECustomer customer) {
		this.customer = customer;
	}

	public BigDecimal getFreezeDollar() {
		return freezeDollar;
	}

	public void setFreezeDollar(BigDecimal freezeDollar) {
		this.freezeDollar = freezeDollar;
	}

/*	public String getWithdrawalCurrencyName(){	
		if(withdrawalCurrency!=null && !"".equals(withdrawalCurrency)){
			return WithdrawalCurrency.getMapValue(withdrawalCurrency).describe();
		}
		return null;
	}*/
/*	
	public String getPaymentCurrencyName(){	
		if(paymentCurrency!=null && !"".equals(paymentCurrency)){
			return PaymentCurrency.getMapValue(paymentCurrency).describe();
		}
		return null;
	}*/
	
/*	public String getWithdrawalWayName(){	
		if(withdrawalWay!=null && !"".equals(withdrawalWay)){
			return WithdrawalWay.getMapValue(withdrawalWay).describe();
		}
		return null;
	}*/
	
/*	public String getWithdrawalTypeName(){
		if(withdrawalType!=null && !"".equals(withdrawalType)){
			return WithdrawalType.getMapValue(withdrawalType).describe();
		}
		return null;
	}*/
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getForumName() {
		return forumName;
	}

	public void setForumName(String forumName) {
		this.forumName = forumName;
	}

	public Date getCheckDateTime() {
		return checkDateTime;
	}

	public void setCheckDateTime(Date checkDateTime) {
		this.checkDateTime = checkDateTime;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getMemoCh() {
		return memoCh;
	}

	public void setMemoCh(String memoCh) {
		this.memoCh = memoCh;
	}
	
	
}
