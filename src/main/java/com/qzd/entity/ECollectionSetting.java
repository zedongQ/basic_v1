package org.ieforex.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.ieforex.enums.Currency;
import org.ieforex.enums.WithdrawalWay;

/**
 * 提款方式设置
 * 
 * @author brilliance
 * @email 
 * @date 2017-05-15 15:56:49
 */
 @Table(name="e_collection_setting")
public class ECollectionSetting implements Serializable {
	private static final long serialVersionUID = 1L;
    /**
	  * ID
	  */
    @Id
	@Column(name="setting_id")
	private Long settingId;

    /**
	  * 取款方式
	  */
    @Column(name="collection_way")
	private String collectionWay;
    public String getCollectionWayName(){
    	if(collectionWay !=null){
    		return WithdrawalWay.getMapValue(collectionWay).describe();
    	}
    	return null;
    }

    /**
	  * 货币
	  */
    private String currency;
    public String getCurrencyName(){
    	if(currency !=null){
    		return Currency.getMapValue(currency).describe();
    	}
    	return null;
    }
    
    /**
	  * 最小金额
	  */
    @Column(name="min_amount")
	private BigDecimal minAmount;

    /**
	  * 最大金额
	  */
    @Column(name="max_amount")
	private BigDecimal maxAmount;

    /**
	  * 费用收取方式
	  */
    @Column(name="fee_way")
	private String feeWay;

    /**
	  * 人名币汇率类型（0按比例；1固定值）
	  */
    @Column(name="exchange_rate_type")
	private String exchangeRateType;
    
    /**
	  * 人名币汇率比例
	  */
    @Column(name="rmb_exchange_rate")
	private BigDecimal rmbExchangeRate;
    
    /**
	  * 人名币汇率
	  */
    @Column(name="rmb_exchange")
	private BigDecimal rmbExchange;
    
    /**
	  * 费用
	  */
    private BigDecimal fee;

    /**
	  * 费用比例
	  */
    @Column(name="fee_percent")
	private BigDecimal feePercent;
    
    /**
	  * 创建人
	  */
    @Column(name="create_user_id")
	private Long createUserId;

    /**
	  * 创建时间
	  */
    @Column(name="create_date")
	private Date createDate;

    /**
	  * 修改人
	  */
    @Column(name="update_user_id")
	private Long updateUserId;

    /**
	  * 修改时间
	  */
    @Column(name="update_date")
	private Date updateDate;
    
    /**
	  * serviceName(客服姓名)
	  */
    @Transient
	private String serviceName;
    
	public String getExchangeRateType() {
		return exchangeRateType;
	}

	public void setExchangeRateType(String exchangeRateType) {
		this.exchangeRateType = exchangeRateType;
	}

	public BigDecimal getRmbExchange() {
		return rmbExchange;
	}

	public void setRmbExchange(BigDecimal rmbExchange) {
		this.rmbExchange = rmbExchange;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/**
	 * 获取“ID”
	 */
	public Long getSettingId() {
		return settingId;
	}

	/**
	 * 设置“ID”
	 */
	public void setSettingId(Long settingId) {
		this.settingId = settingId;
	}
	/**
	 * 获取“取款方式”
	 */
	public String getCollectionWay() {
		return collectionWay;
	}

	/**
	 * 设置“取款方式”
	 */
	public void setCollectionWay(String collectionWay) {
		this.collectionWay = collectionWay;
	}
	/**
	 * 获取“货币”
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * 设置“货币”
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	/**
	 * 获取“最小金额”
	 */
	public BigDecimal getMinAmount() {
		return minAmount;
	}

	/**
	 * 设置“最小金额”
	 */
	public void setMinAmount(BigDecimal minAmount) {
		this.minAmount = minAmount;
	}
	/**
	 * 获取“最大金额”
	 */
	public BigDecimal getMaxAmount() {
		return maxAmount;
	}

	/**
	 * 设置“最大金额”
	 */
	public void setMaxAmount(BigDecimal maxAmount) {
		this.maxAmount = maxAmount;
	}
	/**
	 * 获取“费用收取方式”
	 */
	public String getFeeWay() {
		return feeWay;
	}

	/**
	 * 设置“费用收取方式”
	 */
	public void setFeeWay(String feeWay) {
		this.feeWay = feeWay;
	}
	/**
	 * 获取“费用”
	 */
	public BigDecimal getFee() {
		return fee;
	}

	/**
	 * 设置“费用”
	 */
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	/**
	 * 获取“费用比例”
	 */
	public BigDecimal getFeePercent() {
		return feePercent;
	}

	/**
	 * 设置“费用比例”
	 */
	public void setFeePercent(BigDecimal feePercent) {
		this.feePercent = feePercent;
	}
	/**
	 * 获取“创建人”
	 */
	public Long getCreateUserId() {
		return createUserId;
	}

	/**
	 * 设置“创建人”
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
	 * 获取“修改人”
	 */
	public Long getUpdateUserId() {
		return updateUserId;
	}

	/**
	 * 设置“修改人”
	 */
	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}
	/**
	 * 获取“修改时间”
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * 设置“修改时间”
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public BigDecimal getRmbExchangeRate() {
		return rmbExchangeRate;
	}

	public void setRmbExchangeRate(BigDecimal rmbExchangeRate) {
		this.rmbExchangeRate = rmbExchangeRate;
	}
	
}
