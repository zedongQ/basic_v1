package org.ieforex.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.ieforex.enums.BanksHelp;
import org.ieforex.enums.WithdrawalWay;

import java.util.Date;

/**
 * 客户收款帐号表
 * 
 * @author brilliance
 * @email 
 * @date 2017-05-10 13:33:08
 */
 @Table(name="e_customer_account_info")
public class ECustomerAccountInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
	  * 收款帐号ID
	  */
    @Id
	@Column(name="account_id")
	private Long accountId;

    /**
	  * 用户ID
	  */
    @Column(name="customer_id")
	private Long customerId;

    /**
	  * 名称
	  */
    @Column(name="account_name")
	private String accountName;

    /**
	  * 帐号
	  */
    @Column(name="account_acct")
	private String accountAcct;
    /**
     * 帐号
     */
    @Column(name="account_acct_view")
    private String accountAcctView;

    /**
	  * 手机号
	  */
    @Column(name="account_phone")
	private String accountPhone;

    /**
	  * 收款类型
	  */
    private String type;

    /**
	  * 开户行
	  */
    @Column(name="opening_bank")
	private String openingBank;

    /**
	  * 银行通信地址
	  */
    @Column(name="bank_address")
	private String bankAddress;

    /**
	  * 银行SWIFT代码
	  */
    @Column(name="bank_swift_code")
	private String bankSwiftCode;

    /**
	  * 数据状态
	  */
    @Column(name="data_status")
	private String dataStatus;

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
	  * 是否为默认
	  */
    @Column(name="is_default")
	private String isDefault;

    /**
	  * 数据来源
	  */
    @Column(name="data_source")
	private String dataSource;

    /**
	  * 基本信息ID
	  */
    @Column(name="basic_id")
	private Long basicId;

    /**
	  * 收款人地址
	  */
    @Column(name="payee_address")
	private String payeeAddress;
    /**
	  * 国籍
	  */
    @Transient
    private String nationality;
    
    @Transient
	private String acctName;
    /**
	  * 英文姓名
	  */
	private String enName;
	 /**
	  * 英文名
	  */
	@Transient
	private String engName;
   /**
	  * 英文姓
	  */
	@Transient
	private String engSurname;

	@Transient
	private String cardNo;
	
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * 获取“收款帐号ID”
	 */
	public Long getAccountId() {
		return accountId;
	}

	/**
	 * 设置“收款帐号ID”
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
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
	 * 获取“银行名称（枚举值）”
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * 设置“名称”
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	/**
	 * 获取“帐号”
	 */
	public String getAccountAcct() {
		return accountAcct;
	}

	/**
	 * 设置“帐号”
	 */
	public void setAccountAcct(String accountAcct) {
		this.accountAcct = accountAcct;
	}
	/**
	 * 获取“手机号”
	 */
	public String getAccountPhone() {
		return accountPhone;
	}

	/**
	 * 设置“手机号”
	 */
	public void setAccountPhone(String accountPhone) {
		this.accountPhone = accountPhone;
	}
	/**
	 * 获取“收款类型”
	 */
	public String getType() {
		return type;
	}
	/**
	 * 获取“收款类型名称”
	 */
	public String getTypeName() {
		if(type!=null){
			WithdrawalWay valueByKey = WithdrawalWay.getMapValue(this.type);
			String value = valueByKey.describe();
			return value;
		}
		return type;
	}
	
	/**
	 * 获取“收款类型 图片名称”
	 */
	public String getBankPng() {
		if(accountName!=null){
			BanksHelp valueByKey = BanksHelp.getBanksPng(accountName);
			if(valueByKey==null){
				return "";
			}
			String value = valueByKey.describe();
			return value;
		}
		return "";
	}
	
	/**
	 * 获取“收款类型 名称”
	 */
	public String getBankName() {
		if(accountName!=null){
			BanksHelp valueByKey = BanksHelp.getBanksPng(accountName);
			if(valueByKey==null){
				return accountName;
			}
			String value = valueByKey.toString();
			return value;
		}
		return "";
	}
	
	/**
	 * 设置“收款类型”
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取“开户行”
	 */
	public String getOpeningBank() {
		return openingBank;
	}

	/**
	 * 设置“开户行”
	 */
	public void setOpeningBank(String openingBank) {
		this.openingBank = openingBank;
	}
	/**
	 * 获取“银行通信地址”
	 */
	public String getBankAddress() {
		return bankAddress;
	}

	/**
	 * 设置“银行通信地址”
	 */
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}
	/**
	 * 获取“银行SWIFT代码”
	 */
	public String getBankSwiftCode() {
		return bankSwiftCode;
	}

	/**
	 * 设置“银行SWIFT代码”
	 */
	public void setBankSwiftCode(String bankSwiftCode) {
		this.bankSwiftCode = bankSwiftCode;
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
	 * 获取“是否为默认”
	 */
	public String getIsDefault() {
		return isDefault;
	}

	/**
	 * 设置“是否为默认”
	 */
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
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
	 * 获取“基本信息ID”
	 */
	public Long getBasicId() {
		return basicId;
	}

	/**
	 * 设置“基本信息ID”
	 */
	public void setBasicId(Long basicId) {
		this.basicId = basicId;
	}
	/**
	 * 获取“收款人地址”
	 */
	public String getPayeeAddress() {
		return payeeAddress;
	}

	/**
	 * 设置“收款人地址”
	 */
	public void setPayeeAddress(String payeeAddress) {
		this.payeeAddress = payeeAddress;
	}
	
	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}
	
	public String getAcctNameView() {
		if(acctName==null){
			return null;
		}
		if(acctName.length() <= 1) {  
			return "*";  
	    }else if(acctName.length()==2){  
	    	return acctName.replaceAll("([\\u4e00-\\u9fa5]{1})(.*)", "$1*");  
	    }else{
	    	int xingLength = acctName.length()-2;
	    	String xing = "";
	    	for( int i=0;i<xingLength;i++ ){
	    		xing+="*";
	    	}
	    	return acctName.replaceAll("([\\u4e00-\\u9fa5]{1})(.+)([\\u4e00-\\u9fa5]{1})", "$1"+xing+"$3");  
	    }  
	}

	public String getEngName() {
		return engName;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	public String getAccountAcctView() {
		return accountAcctView;
	}

	public void setAccountAcctView(String accountAcctView) {
		this.accountAcctView = accountAcctView;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getEngSurname() {
		return engSurname;
	}

	public void setEngSurname(String engSurname) {
		this.engSurname = engSurname;
	}
	
}
