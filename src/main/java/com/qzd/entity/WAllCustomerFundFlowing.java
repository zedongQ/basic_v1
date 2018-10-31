package org.ieforex.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import org.ieforex.enums.Currency;
import org.ieforex.enums.StatusType;
import org.ieforex.enums.TradeType;
import org.ieforex.enums.WithdrawalWay;
import org.ieforex.utils.MathUtil;

import java.util.Date;

/**
 * 
 * 
 * @author brilliance
 * @email 
 * @date 2017-05-19 09:57:43
 */
 @Table(name="w_all_customer_fund_flowing")
public class WAllCustomerFundFlowing implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
	  * 客户资金流水编号
	  */
    @Id
	@Column(name="flowing_id")
	private Long flowingId;

    /**
	  * 客户编号
	  */
    @Column(name="customer_id")
	private Long customerId;

    /**
	  * 操作日期
	  */
    @Column(name="operate_date")
	private Date operateDate;
    /**
     * 操作日期展示
     */
    @Transient
    private String operateDateView;
    /**
     * 开始日期展示
     */
    @Transient
    private String startDateView;
    /**
     * 结束日期展示
     */
    @Transient
    private String endDateView;
    
    /**
	  * 操作人员
	  */
   @Column(name="operate_user_id")
	private Long operateUserId;

    /**
	  * 流水号
	  */
    @Column(name="flowing_no")
	private String flowingNo;

    /**
	  * 交易商编号
	  */
    @Column(name="dealer_id")
	private Long dealerId;

    /**
	  * 交易商名称
	  */
    @Column(name="dealer_name")
	private String dealerName;

    /**
	  * 账号信息
	  */
    @Column(name="acct_no")
	private String acctNo;
    /**
	  * 提现币种
	  */
    @Column(name="withdrawal_currency")
    private String withdrawalCurrency;
    
    /**
	  * 交易类型
	  * 0---返现
	  * 1---提现
	  * 2---补录
	  */
    @Column(name="trade_type")
	private String tradeType;

    /**
	  * 金额
	  */
    private BigDecimal amount;

    /**
	  * 状态
	  * 0---预估（返现）
	  * 1---已到账（返现）
	  * 2---处理中（提现）
	  * 3---已支付（提现）
	  * 4---失败（提现）
	  */
    private String status;
    /**
	  * 收款方式
	  */
    @Column(name="withdrawal_way")
    private String withdrawalWay;
    /**
	  * 收款人
	  */
    @Column(name="payee_name")
    private String payeeName;
    /**
     * 收款人英文名
     */
    @Transient
    private String payeeEngName;
    /**
	  * 描述
	  */
    private String describes;

    
    public String getStartDateView() {
		return startDateView;
	}

	public void setStartDateView(String startDateView) {
		this.startDateView = startDateView;
	}

	public String getEndDateView() {
		return endDateView;
	}

	public void setEndDateView(String endDateView) {
		this.endDateView = endDateView;
	}

	public String getPayeeEngName() {
		return payeeEngName;
	}

	public void setPayeeEngName(String payeeEngName) {
		this.payeeEngName = payeeEngName;
	}

	/**
	  * 操作时间
	  */
    @Column(name="update_date")
	private Date updateDate;

    /**
	  * 更新人员
	  */
    @Column(name="update_user_id")
	private Long updateUserId;

    /**
	  * 预估返现：E_Rebate_Result 【rebate_result_id】提现申请：E_Withdrawal【withdrawal_id】
	  */
    @Column(name="object_id")
	private Long objectId;

    /**
	  * 总页数
	  */
    @Transient
    private int total;
    /**
 	  * 当前页
      */
    @Transient
    private int current;
 
    /**
     * 实际提现金额
     */
    @Column(name="withdrawal_amount_currency")
    private BigDecimal withdrawalAmountCurrency;
    /**
     * 收款银行名称
     */
    @Transient 
    private String payeeBankName;
    /**
     * 收款电汇银行名称
     */
    @Transient 
    private String payeeTtBankName;
    /**
     * 实际支付日期
     */
    @Transient
    private Date paymentDate;
    /**
     * 实际支付金额
     */
    @Transient
    private BigDecimal paymentAmount;
    /**
     * 实际支付币种
     */
    /**
     * 提现费用
     */
    @Column(name="withdrawal_fee")
    private BigDecimal withdrawalFee;
    
    
    /**
	 * 获取“客户资金流水编号”
	 */
	public Long getFlowingId() {
		return flowingId;
	}

	/**
	 * 设置“客户资金流水编号”
	 */
	public void setFlowingId(Long flowingId) {
		this.flowingId = flowingId;
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
	 * 获取“操作日期”
	 */
	public Date getOperateDate() {
		return operateDate;
	}

	/**
	 * 设置“操作日期”
	 */
	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}
	/**
	 * 获取“流水号”
	 */
	public String getFlowingNo() {
		return flowingNo;
	}

	/**
	 * 设置“流水号”
	 */
	public void setFlowingNo(String flowingNo) {
		this.flowingNo = flowingNo;
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
	 * 获取“交易商名称”
	 */
	public String getDealerName() {
		return dealerName;
	}

	/**
	 * 设置“交易商名称”
	 */
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	/**
	 * 获取“账号信息”
	 */
	public String getAcctNo() {
		return acctNo;
	}
	/**
	 * 获取“账号信息展示”
	 */
	public String getAcctNoView() {
		if("1".equals(tradeType)) {
			if(acctNo!=null) {
				if(acctNo.length()>0 && acctNo.contains("@")){
					String emailPrefix = acctNo.substring(0,acctNo.lastIndexOf("@"));
					String emailLastfix = acctNo.substring(acctNo.lastIndexOf("@"));
					String xing = "";
					if(emailPrefix.length()==1){
						return "*"+emailLastfix;
					}else if(emailPrefix.length()<5){
						for(int i =0;i<emailPrefix.length()-2;i++){
							xing +="*";
						}
						return acctNo.replaceAll("(\\w)(\\w+)(\\w)(@\\w+\\.[a-z]+(\\.[a-z]+)?)", "$1"+xing+"$3$4");
					}else if(emailPrefix.length()>4){
						for(int i =0;i<emailPrefix.length()-4;i++){
							xing +="*";
						}
						return acctNo.replaceAll("(\\w{3})(\\w+)(\\w)(@\\w+\\.[a-z]+(\\.[a-z]+)?)", "$1"+xing+"$3$4");
					}
				}else if(acctNo.length()>5){
					return acctNo.replaceAll("(\\d{2})\\d+(\\d{3})", "$1******$2");
				}else {
					return acctNo.replaceAll("(\\d{1})\\d+(\\d{2})", "$1***$2");
				}
			}
			return "";
		}
		return acctNo;
	}

	/**
	 * 设置“账号信息”
	 */
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	/**
	 * 获取“交易类型”
	 */
	public String getTradeType() {
		return tradeType;
	}
	/**
	 * 获取“交易名称”
	 */
	public String getTradeTypeName() {
		
		if(tradeType!=null){
			 TradeType valueByKey = TradeType.getMapValue(this.tradeType);
			 String value = valueByKey.describe();
			 return value;
		}
		 return null;
	}

	/**
	 * 设置“交易类型”
	 */
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	/**
	 * 获取“金额”
	 */
	public BigDecimal getAmount() {
		return MathUtil.parseBigDecimal(amount, 2, true);
	}

	/**
	 * 设置“金额”
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * 获取“状态”
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 获取“状态名称”
	 */
	public String getStatusName() {
		if(status!=null){
			StatusType valueByKey = StatusType.getMapValue(this.status);
			 String value = valueByKey.describe();
			 return value;
		}
		 return null;
	}
	
	
	/**
	 * 设置“状态”
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取“描述”
	 */
	public String getDescribes() {
		return describes;
	}

	/**
	 * 设置“描述”
	 */
	public void setDescribes(String describes) {
		this.describes = describes;
	}
	/**
	 * 获取“操作时间”
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * 设置“操作时间”
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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
	 * 获取“预估返现：E_Rebate_Result 【rebate_result_id】提现申请：E_Withdrawal【withdrawal_id】”
	 */
	public Long getObjectId() {
		return objectId;
	}

	/**
	 * 设置“预估返现：E_Rebate_Result 【rebate_result_id】提现申请：E_Withdrawal【withdrawal_id】”
	 */
	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public Long getOperateUserId() {
		return operateUserId;
	}

	public void setOperateUserId(Long operateUserId) {
		this.operateUserId = operateUserId;
	}

	public String getWithdrawalWay() {
		return withdrawalWay;
	}
	/**
	 * 获取“收款方式名称”
	 */
	public String getWithdrawalWayName() {
		if(withdrawalWay!=null){
			WithdrawalWay valueByKey = WithdrawalWay.getMapValue(this.withdrawalWay);
			 String value = valueByKey.describe();
			 return value;
		}
		 return null;
	}
	
	public void setWithdrawalWay(String withdrawalWay) {
		this.withdrawalWay = withdrawalWay;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getPayeeBankName() {
		return payeeBankName;
	}

	public void setPayeeBankName(String payeeBankName) {
		this.payeeBankName = payeeBankName;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public BigDecimal getPaymentAmount() {
		return MathUtil.parseBigDecimal(paymentAmount, 2, true);
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getWithdrawalCurrencyName() {
		if(withdrawalCurrency!=null){
			Currency valueByKey = Currency.getMapValue(this.withdrawalCurrency);
			 String value = valueByKey.describe();
			 return value;
		}
		return "";
	}

	public String getWithdrawalCurrency() {
		return withdrawalCurrency;
	}

	public void setWithdrawalCurrency(String withdrawalCurrency) {
		this.withdrawalCurrency = withdrawalCurrency;
	}

	public BigDecimal getWithdrawalFee() {
		return MathUtil.parseBigDecimal(withdrawalFee, 2, true);
	}

	public void setWithdrawalFee(BigDecimal withdrawalFee) {
		this.withdrawalFee = withdrawalFee;
	}

	public String getPayeeTtBankName() {
		return payeeTtBankName;
	}

	public void setPayeeTtBankName(String payeeTtBankName) {
		this.payeeTtBankName = payeeTtBankName;
	}

	public String getOperateDateView() {
		return operateDateView;
	}

	public void setOperateDateView(String operateDateView) {
		this.operateDateView = operateDateView;
	}

	public BigDecimal getWithdrawalAmountCurrency() {
		return MathUtil.parseBigDecimal(withdrawalAmountCurrency, 2, true);
	}

	public void setWithdrawalAmountCurrency(BigDecimal withdrawalAmountCurrency) {
		this.withdrawalAmountCurrency = withdrawalAmountCurrency;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}
}
