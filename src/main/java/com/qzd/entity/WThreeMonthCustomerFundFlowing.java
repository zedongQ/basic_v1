package org.ieforex.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

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
 * @date 2017-05-19 09:57:31
 */
 @Table(name="w_three_month_customer_fund_flowing")
public class WThreeMonthCustomerFundFlowing implements Serializable {
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
	     * 操作日期
	     */
	    @Transient
	    private String operateDateView;
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
		  * 描述（英文）
		  */
	    private String describes;
	    
	    /**
		  * 描述(中文)
		  */
	    @Column(name="describes_ch")
	    private String describesCh;
	    
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
		  * 提现金额
		  */
	    @Transient
	    private BigDecimal withdrawalAmount;
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
	    @Transient
	    private String paymentCurrency;
	    /**
	     * 提现费用
	     */
	    @Transient
	    private BigDecimal withdrawalFee;
	    
	    /**
	     * 收款人英文名
	     */
	    @Transient
	    private String payeeEngName;
	    
	    
	    public String getPayeeEngName() {
			return payeeEngName;
		}

		public void setPayeeEngName(String payeeEngName) {
			this.payeeEngName = payeeEngName;
		}

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
			return amount;
		}

		/**
		 * 设置“金额”
		 */
		public void setAmount(BigDecimal amount) {
			this.amount = MathUtil.parseBigDecimal(amount, 2, true);
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

		public int getCurrent() {
			return current;
		}

		public void setCurrent(int current) {
			this.current = current;
		}

		public BigDecimal getWithdrawalAmount() {
			return withdrawalAmount;
		}

		public void setWithdrawalAmount(BigDecimal withdrawalAmount) {
			this.withdrawalAmount =  MathUtil.parseBigDecimal(withdrawalAmount, 2, true);
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
			return paymentAmount;
		}

		public void setPaymentAmount(BigDecimal paymentAmount) {
			this.paymentAmount = paymentAmount;
		}

		public String getPaymentCurrency() {
			return paymentCurrency;
		}

		public void setPaymentCurrency(String paymentCurrency) {
			this.paymentCurrency = paymentCurrency;
		}

		public BigDecimal getWithdrawalFee() {
			return withdrawalFee;
		}

		public void setWithdrawalFee(BigDecimal withdrawalFee) {
			this.withdrawalFee = MathUtil.parseBigDecimal(withdrawalFee, 2, true);
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
		 public String getDescribesCh() {
			return describesCh;
		}

		public void setDescribesCh(String describesCh) {
			this.describesCh = describesCh;
		}
	
}
