package org.ieforex.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import java.util.Date;


/**
 * 
 * 
 * @author zero
 * @email 15638559970
 * @date 2017-09-01 17:08:10
 */
 @Table(name="e_customer_flowing")
public class ECustomerFlowing implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @Id
	@Column(name="flowing_id")
	private Long flowingId;

    @Column(name="customer_id")
	private Long customerId;

    @Column(name="trade_name")
	private String tradeName;

    private String pwd;

    @Column(name="with_draw_pwd")
	private String withDrawPwd;

    @Column(name="user_name")
	private String userName;

    @Column(name="login_phone")
	private String loginPhone;

    @Column(name="login_email")
	private String loginEmail;

    @Column(name="login_ip")
	private String loginIp;

    @Column(name="login_date")
	private Date loginDate;

    @Column(name="forum_name")
	private String forumName;

    private String nationality;

    @Column(name="is_ib")
	private String isIb;

    @Column(name="user_qq")
	private String userQq;

    @Column(name="user_card_no")
	private String userCardNo;

    @Column(name="rebate_count")
	private Integer rebateCount;

    @Column(name="security_level")
	private String securityLevel;

    private String status;

    @Column(name="user_type")
	private String userType;

    private BigDecimal amount;

    private BigDecimal available;

    private BigDecimal freeze;

    private BigDecimal uncollected;

    @Column(name="rebate_amount")
	private BigDecimal rebateAmount;

    @Column(name="rebate_amount_ex")
	private BigDecimal rebateAmountEx;

    @Column(name="with_draw_amount")
	private BigDecimal withDrawAmount;

    @Column(name="invite_amount")
	private BigDecimal inviteAmount;

    @Column(name="invite_id")
	private Long inviteId;

    @Column(name="level_id")
	private Long levelId;

    @Column(name="is_aut")
	private String isAut;

    @Column(name="calculate_level_id")
	private Long calculateLevelId;

    @Column(name="start_date")
	private Date startDate;

    @Column(name="end_date")
	private Date endDate;

    @Column(name="with_draw_code")
	private Integer withDrawCode;

    @Column(name="code_date")
	private Date codeDate;

    @Column(name="login_count")
	private Integer loginCount;

    @Column(name="change_info_date")
	private Long changeInfoDate;

    @Column(name="head_img")
	private String headImg;

    @Column(name="oper_type")
	private String operType;

    @Column(name="info_type")
	private String infoType;

    @Column(name="oper_user_id")
	private Long operUserId;

    @Column(name="oper_date")
	private Date operDate;

    @Column(name="oper_task_id")
	private Long operTaskId;

    @Column(name="create_user_id")
	private Long createUserId;

    @Column(name="create_date")
	private Date createDate;

    @Column(name="update_user_id")
	private Long updateUserId;

    @Column(name="update_date")
	private Date updateDate;


	/**
	 * 获取表字段flowing_id
	 */
	public Long getFlowingId() {
		return flowingId;
	}

	/**
	 * 设置表字段flowing_id
	 */
	public void setFlowingId(Long flowingId) {
		this.flowingId = flowingId;
	}
	/**
	 * 获取表字段customer_id
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * 设置表字段customer_id
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	/**
	 * 获取表字段trade_name
	 */
	public String getTradeName() {
		return tradeName;
	}

	/**
	 * 设置表字段trade_name
	 */
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	/**
	 * 获取表字段pwd
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * 设置表字段pwd
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	/**
	 * 获取表字段with_draw_pwd
	 */
	public String getWithDrawPwd() {
		return withDrawPwd;
	}

	/**
	 * 设置表字段with_draw_pwd
	 */
	public void setWithDrawPwd(String withDrawPwd) {
		this.withDrawPwd = withDrawPwd;
	}
	/**
	 * 获取表字段user_name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置表字段user_name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取表字段login_phone
	 */
	public String getLoginPhone() {
		return loginPhone;
	}

	/**
	 * 设置表字段login_phone
	 */
	public void setLoginPhone(String loginPhone) {
		this.loginPhone = loginPhone;
	}
	/**
	 * 获取表字段login_email
	 */
	public String getLoginEmail() {
		return loginEmail;
	}

	/**
	 * 设置表字段login_email
	 */
	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}
	/**
	 * 获取表字段login_ip
	 */
	public String getLoginIp() {
		return loginIp;
	}

	/**
	 * 设置表字段login_ip
	 */
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	/**
	 * 获取表字段login_date
	 */
	public Date getLoginDate() {
		return loginDate;
	}

	/**
	 * 设置表字段login_date
	 */
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	/**
	 * 获取表字段forum_name
	 */
	public String getForumName() {
		return forumName;
	}

	/**
	 * 设置表字段forum_name
	 */
	public void setForumName(String forumName) {
		this.forumName = forumName;
	}
	/**
	 * 获取表字段nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * 设置表字段nationality
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	/**
	 * 获取表字段is_ib
	 */
	public String getIsIb() {
		return isIb;
	}

	/**
	 * 设置表字段is_ib
	 */
	public void setIsIb(String isIb) {
		this.isIb = isIb;
	}
	/**
	 * 获取表字段user_qq
	 */
	public String getUserQq() {
		return userQq;
	}

	/**
	 * 设置表字段user_qq
	 */
	public void setUserQq(String userQq) {
		this.userQq = userQq;
	}
	/**
	 * 获取表字段user_card_no
	 */
	public String getUserCardNo() {
		return userCardNo;
	}

	/**
	 * 设置表字段user_card_no
	 */
	public void setUserCardNo(String userCardNo) {
		this.userCardNo = userCardNo;
	}
	/**
	 * 获取表字段rebate_count
	 */
	public Integer getRebateCount() {
		return rebateCount;
	}

	/**
	 * 设置表字段rebate_count
	 */
	public void setRebateCount(Integer rebateCount) {
		this.rebateCount = rebateCount;
	}
	/**
	 * 获取表字段security_level
	 */
	public String getSecurityLevel() {
		return securityLevel;
	}

	/**
	 * 设置表字段security_level
	 */
	public void setSecurityLevel(String securityLevel) {
		this.securityLevel = securityLevel;
	}
	/**
	 * 获取表字段status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 设置表字段status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取表字段user_type
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * 设置表字段user_type
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	/**
	 * 获取表字段amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * 设置表字段amount
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * 获取表字段available
	 */
	public BigDecimal getAvailable() {
		return available;
	}

	/**
	 * 设置表字段available
	 */
	public void setAvailable(BigDecimal available) {
		this.available = available;
	}
	/**
	 * 获取表字段freeze
	 */
	public BigDecimal getFreeze() {
		return freeze;
	}

	/**
	 * 设置表字段freeze
	 */
	public void setFreeze(BigDecimal freeze) {
		this.freeze = freeze;
	}
	/**
	 * 获取表字段uncollected
	 */
	public BigDecimal getUncollected() {
		return uncollected;
	}

	/**
	 * 设置表字段uncollected
	 */
	public void setUncollected(BigDecimal uncollected) {
		this.uncollected = uncollected;
	}
	/**
	 * 获取表字段rebate_amount
	 */
	public BigDecimal getRebateAmount() {
		return rebateAmount;
	}

	/**
	 * 设置表字段rebate_amount
	 */
	public void setRebateAmount(BigDecimal rebateAmount) {
		this.rebateAmount = rebateAmount;
	}
	/**
	 * 获取表字段rebate_amount_ex
	 */
	public BigDecimal getRebateAmountEx() {
		return rebateAmountEx;
	}

	/**
	 * 设置表字段rebate_amount_ex
	 */
	public void setRebateAmountEx(BigDecimal rebateAmountEx) {
		this.rebateAmountEx = rebateAmountEx;
	}
	/**
	 * 获取表字段with_draw_amount
	 */
	public BigDecimal getWithDrawAmount() {
		return withDrawAmount;
	}

	/**
	 * 设置表字段with_draw_amount
	 */
	public void setWithDrawAmount(BigDecimal withDrawAmount) {
		this.withDrawAmount = withDrawAmount;
	}
	/**
	 * 获取表字段invite_amount
	 */
	public BigDecimal getInviteAmount() {
		return inviteAmount;
	}

	/**
	 * 设置表字段invite_amount
	 */
	public void setInviteAmount(BigDecimal inviteAmount) {
		this.inviteAmount = inviteAmount;
	}
	/**
	 * 获取表字段invite_id
	 */
	public Long getInviteId() {
		return inviteId;
	}

	/**
	 * 设置表字段invite_id
	 */
	public void setInviteId(Long inviteId) {
		this.inviteId = inviteId;
	}
	/**
	 * 获取表字段level_id
	 */
	public Long getLevelId() {
		return levelId;
	}

	/**
	 * 设置表字段level_id
	 */
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}
	/**
	 * 获取表字段is_aut
	 */
	public String getIsAut() {
		return isAut;
	}

	/**
	 * 设置表字段is_aut
	 */
	public void setIsAut(String isAut) {
		this.isAut = isAut;
	}
	/**
	 * 获取表字段calculate_level_id
	 */
	public Long getCalculateLevelId() {
		return calculateLevelId;
	}

	/**
	 * 设置表字段calculate_level_id
	 */
	public void setCalculateLevelId(Long calculateLevelId) {
		this.calculateLevelId = calculateLevelId;
	}
	/**
	 * 获取表字段start_date
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * 设置表字段start_date
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * 获取表字段end_date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置表字段end_date
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * 获取表字段with_draw_code
	 */
	public Integer getWithDrawCode() {
		return withDrawCode;
	}

	/**
	 * 设置表字段with_draw_code
	 */
	public void setWithDrawCode(Integer withDrawCode) {
		this.withDrawCode = withDrawCode;
	}
	/**
	 * 获取表字段code_date
	 */
	public Date getCodeDate() {
		return codeDate;
	}

	/**
	 * 设置表字段code_date
	 */
	public void setCodeDate(Date codeDate) {
		this.codeDate = codeDate;
	}
	/**
	 * 获取表字段login_count
	 */
	public Integer getLoginCount() {
		return loginCount;
	}

	/**
	 * 设置表字段login_count
	 */
	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
	/**
	 * 获取表字段change_info_date
	 */
	public Long getChangeInfoDate() {
		return changeInfoDate;
	}

	/**
	 * 设置表字段change_info_date
	 */
	public void setChangeInfoDate(Long changeInfoDate) {
		this.changeInfoDate = changeInfoDate;
	}
	/**
	 * 获取表字段head_img
	 */
	public String getHeadImg() {
		return headImg;
	}

	/**
	 * 设置表字段head_img
	 */
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	/**
	 * 获取表字段oper_type
	 */
	public String getOperType() {
		return operType;
	}

	/**
	 * 设置表字段oper_type
	 */
	public void setOperType(String operType) {
		this.operType = operType;
	}
	/**
	 * 获取表字段info_type
	 */
	public String getInfoType() {
		return infoType;
	}

	/**
	 * 设置表字段info_type
	 */
	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}
	/**
	 * 获取表字段oper_user_id
	 */
	public Long getOperUserId() {
		return operUserId;
	}

	/**
	 * 设置表字段oper_user_id
	 */
	public void setOperUserId(Long operUserId) {
		this.operUserId = operUserId;
	}
	/**
	 * 获取表字段oper_date
	 */
	public Date getOperDate() {
		return operDate;
	}

	/**
	 * 设置表字段oper_date
	 */
	public void setOperDate(Date operDate) {
		this.operDate = operDate;
	}
	/**
	 * 获取表字段oper_task_id
	 */
	public Long getOperTaskId() {
		return operTaskId;
	}

	/**
	 * 设置表字段oper_task_id
	 */
	public void setOperTaskId(Long operTaskId) {
		this.operTaskId = operTaskId;
	}
	/**
	 * 获取表字段create_user_id
	 */
	public Long getCreateUserId() {
		return createUserId;
	}

	/**
	 * 设置表字段create_user_id
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取表字段create_date
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * 设置表字段create_date
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取表字段update_user_id
	 */
	public Long getUpdateUserId() {
		return updateUserId;
	}

	/**
	 * 设置表字段update_user_id
	 */
	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}
	/**
	 * 获取表字段update_date
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * 设置表字段update_date
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
