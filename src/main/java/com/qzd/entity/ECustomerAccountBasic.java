package org.ieforex.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * 客户收款帐号基本信息表
 * 
 * @author brilliance
 * @email 
 * @date 2017-05-10 13:33:07
 */
 @Table(name="e_customer_account_basic")
public class ECustomerAccountBasic implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
	  * 收款帐号基本信息ID
	  */
    @Id
	@Column(name="basic_id")
	private Long basicId;

    /**
	  * 客户ID
	  */
    @Column(name="customer_id")
	private Long customerId;

    /**
	  * 手机号
	  */
    @Column(name="account_phone")
	private String accountPhone;

    /**
	  * 姓名
	  */
    @Column(name="acct_name")
	private String acctName;

    /**
	  * 用户QQ
	  */
    @Column(name="user_qq")
	private String userQq;

    /**
	  * 收款人邮箱
	  */
    @Column(name="account_email")
	private String accountEmail;

    /**
	  * 英文名
	  */
    @Column(name="eng_name")
	private String engName;
    /**
	  * 英文姓
	  */
    @Column(name="eng_surname")
	private String engSurname;

    /**
	  * 银行地址
	  */
    @Column(name="bank_address")
	private String bankAddress;

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
	  * 数据来源
	  */
    @Column(name="data_source")
	private String dataSource;

    /**
	  * 身份证号
	  */
    @Column(name="card_no")
	private String cardNo;

    /**
	  * 是否是默认
	  */
    @Column(name="is_default")
	private String isDefault;

    /**
	  * 证件类型
	  */
    @Column(name="card_no_type")
	private String cardNoType;

    /**
	  * 国籍
	  */
    private String nationality;


	/**
	 * 获取“收款帐号基本信息ID”
	 */
	public Long getBasicId() {
		return basicId;
	}

	/**
	 * 设置“收款帐号基本信息ID”
	 */
	public void setBasicId(Long basicId) {
		this.basicId = basicId;
	}
	/**
	 * 获取“客户ID”
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * 设置“客户ID”
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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
	 * 获取“姓名”
	 */
	public String getAcctName() {
		return acctName;
	}

	/**
	 * 设置“姓名”
	 */
	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}
	/**
	 * 获取“用户QQ”
	 */
	public String getUserQq() {
		return userQq;
	}

	/**
	 * 设置“用户QQ”
	 */
	public void setUserQq(String userQq) {
		this.userQq = userQq;
	}
	/**
	 * 获取“收款人邮箱”
	 */
	public String getAccountEmail() {
		return accountEmail;
	}

	/**
	 * 设置“收款人邮箱”
	 */
	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}
	/**
	 * 获取表字段eng_name
	 */
	public String getEngName() {
		return engName;
	}

	/**
	 * 设置表字段eng_name
	 */
	public void setEngName(String engName) {
		this.engName = engName;
	}
	
	public String getEngSurname() {
		return engSurname;
	}

	public void setEngSurname(String engSurname) {
		this.engSurname = engSurname;
	}
	
	/**
	 * 获取“银行地址”
	 */
	public String getBankAddress() {
		return bankAddress;
	}

	/**
	 * 设置“银行地址”
	 */
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
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
	 * 获取“身份证号”
	 */
	public String getCardNo() {
		return cardNo;
	}

	/**
	 * 设置“身份证号”
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	/**
	 * 获取“是否是默认”
	 */
	public String getIsDefault() {
		return isDefault;
	}

	/**
	 * 设置“是否是默认”
	 */
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	/**
	 * 获取“证件类型”
	 */
	public String getCardNoType() {
		return cardNoType;
	}

	/**
	 * 设置“证件类型”
	 */
	public void setCardNoType(String cardNoType) {
		this.cardNoType = cardNoType;
	}
	/**
	 * 获取“国籍”
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * 设置“国籍”
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

}
