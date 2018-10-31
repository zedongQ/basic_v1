package org.ieforex.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @title 客户表
 * @author brilliance
 * @date 2017-05-25 18:07:31
 */
@Table(name="e_customer")
public class ECustomer implements Serializable {
	private static final long serialVersionUID = 1L;
	 
	public ECustomer(){
		//设置默认值
		this.status = "2";
		this.inviteNum=0;
		this.inviteIntegral=0;
	}
	
	//邀请人数
	@Transient
	private int inviteNum;
	
    public int getInviteNum() {
		return inviteNum;
	}
	public void setInviteNum(int inviteNum) {
		this.inviteNum = inviteNum;
	}
	
	//邀请积分
	@Transient
	private int inviteIntegral;
	
	@Transient
	private BigDecimal rebateAmountSum;	
	@Transient
	private BigDecimal withdrawlAmountSum;	
	@Transient
	private BigDecimal incomeAmountSum;	
	
	public BigDecimal getWithdrawlAmountSum() {
		return withdrawlAmountSum;
	}
	public void setWithdrawlAmountSum(BigDecimal withdrawlAmountSum) {
		this.withdrawlAmountSum = withdrawlAmountSum;
	}
	public BigDecimal getIncomeAmountSum() {
		return incomeAmountSum;
	}
	public void setIncomeAmountSum(BigDecimal incomeAmountSum) {
		this.incomeAmountSum = incomeAmountSum;
	}
	public BigDecimal getRebateAmountSum() {
		return rebateAmountSum;
	}
	public void setRebateAmountSum(BigDecimal rebateAmountSum) {
		this.rebateAmountSum = rebateAmountSum;
	}
	public int getInviteIntegral() {
		return inviteIntegral;
	}
	public void setInviteIntegral(int inviteIntegral) {
		this.inviteIntegral = inviteIntegral;
	}

	/**
	  * 用户ID
	  */
    @Id
	@Column(name="customer_id")
	private Long customerId;
    
    
    /**
	  * 交易账户用户名
	  */
    @Column(name="trade_name")
	private String tradeName;
    /**
	  * 实时汇率
	  */
    @Column(name="fee_rate")
    private BigDecimal feeRate;
    
    /**
	  * 用户密码
	  */
    private String pwd;
    /**
     * 邀请奖励
     */
    private BigDecimal inviteAmount = new BigDecimal("0.00");

    /**
	  * 提现密码
	  */
    @Column(name="with_draw_pwd")
	private String withDrawPwd;

    /**
	  * 真实姓名
	  */
    @Column(name="user_name")
	private String userName;
    
    @Transient
    private String isName;
    /**
	  * 登陆手机号
	  */
    @Column(name="login_phone")
	private String loginPhone;

    /**
	  * 登陆邮箱
	  */
    @Column(name="login_email")
	private String loginEmail;

    /**
	  * 登陆IP
	  */
    @Column(name="login_ip")
	private String loginIp;

    /**
	  * 登陆时间
	  */
    @Column(name="login_date")
	private Date loginDate;

    /**
	  * 论坛用户名
	  */
    @Column(name="forum_name")
	private String forumName;

    /**
	  * 用户QQ
	  */
    @Column(name="user_qq")
	private String userQq;

    /**
	  * 用户身份证
	  */
    @Column(name="user_card_no")
	private String userCardNo;

    /**
	  * 返佣账号数量
	  */
    @Column(name="rebate_count")
	private Integer rebateCount;

    /**
	  * 帐号安全级别
	  */
    @Column(name="security_level")
	private String securityLevel;

    /**
	  * 用户状态 0正常1锁定2未激活
	  */
    private String status;

    /**
	  * 用户创建类型
	  */
    @Column(name="user_type")
	private String userType;

    /**
	  * 金额
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
    @Column(name="rebate_amount")
	private BigDecimal rebateAmount;

    /**
	  * 累计返佣金额（未解绑）
	  */
    @Column(name="rebate_amount_ex")
	private BigDecimal rebateAmountEx;

    /**
	  * 累计提现金额
	  */
    @Column(name="with_draw_amount")
	private BigDecimal withDrawAmount;

    /**
	  * 邀请人ID
	  */
    @Column(name="invite_id")
	private Long inviteId;

    /**
	  * 创建人员ID
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
	  * 当前客户等级编号
	  */
    @Column(name="level_id")
	private Long levelId;

    /**
	  * 是否自动调整等
	  */
    @Column(name="is_aut")
	private String isAut;

    /**
	  * 计算后实际等级
	  */
    @Column(name="calculate_level_id")
	private Long calculateLevelId;

    /**
	  * 手动调整起始日期
	  */
    @Column(name="start_date")
	private Date startDate;

    /**
	  * 手动调整结束日期
	  */
    @Column(name="end_date")
	private Date endDate;

    @Column(name="is_ib")
	private String isIb;
    
    private String nationality;
    
    /**
 	  * 交易账号数量
 	  */
    @Transient
    private Long tradeAcctCounts;
    
    /**
     * 提现账号数量
     */
    @Transient
    private Long withdrawlCounts;
    
    /**
     * 交易次数
     */
    @Transient
    private Long flowingCounts;
    
    @Column(name="with_draw_code")
    private int withDrawCode;
    
    @Column(name="code_date")
    private Date codeDate;
    
	/**
	 * 登录次数
	 */
    @Column(name="login_count")
    private int loginCount;
    /**
	 * 最近锁定时间
	 */
    @Column(name="latest_lock_time")
    private Date latestLockTime;
    /**
     * 数据改变时间
     */
    @Column(name="change_info_date")
    private Long changeInfoDate;

    /**
	  * 头像
	  */
    @Column(name="head_img")
    private String headImg;	
    
    
    @Column(name="verify_code")
    private String verifyCode;
    
    //积分
    private Long integral;

    @Column(name="lottery_number")
    private Long lotteryNumber;
    
    @Transient
    private String loginPhoneView;
    

	public BigDecimal getFeeRate() {
		return feeRate;
	}
	public void setFeeRate(BigDecimal feeRate) {
		this.feeRate = feeRate;
	}

	public Long getFlowingCounts() {
		return flowingCounts;
	}

	public void setFlowingCounts(Long flowingCounts) {
		this.flowingCounts = flowingCounts;
	}

	public String getLoginPhoneView() {
		if(loginPhone!=null){
			return loginPhone.replaceAll("(\\d{3})\\d+(\\d{4})","$1****$2");
		}
		return "";
	}

	public void setLoginPhoneView(String loginPhoneView) {
		this.loginPhoneView = loginPhoneView;
	}

	public String getIsName() {
		return isName;
	}

	public void setIsName(String isName) {
		this.isName = isName;
	}

	public Long getWithdrawlCounts() {
		return withdrawlCounts;
	}

	public void setWithdrawlCounts(Long withdrawlCounts) {
		this.withdrawlCounts = withdrawlCounts;
	}

	public Long getLotteryNumber() {
		return lotteryNumber;
	}

	public void setLotteryNumber(Long lotteryNumber) {
		this.lotteryNumber = lotteryNumber;
	}

	public Long getIntegral() {
		return integral;
	}

	public void setIntegral(Long integral) {
		this.integral = integral;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public int getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	public Long getChangeInfoDate() {
		return changeInfoDate;
	}

	public void setChangeInfoDate(Long changeInfoDate) {
		this.changeInfoDate = changeInfoDate;
	}
	
	public Long getTradeAcctCounts() {
		return tradeAcctCounts;
	}

	public void setTradeAcctCounts(Long tradeAcctCounts) {
		this.tradeAcctCounts = tradeAcctCounts;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
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
	 * 获取“交易账户用户名”
	 */
	public String getTradeName() {
		return tradeName;
	}

	/**
	 * 设置“交易账户用户名”
	 */
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	/**
	 * 获取“用户密码”
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * 设置“用户密码”
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	/**
	 * 获取“提现密码”
	 */
	public String getWithDrawPwd() {
		return withDrawPwd;
	}

	/**
	 * 设置“提现密码”
	 */
	public void setWithDrawPwd(String withDrawPwd) {
		this.withDrawPwd = withDrawPwd;
	}
	
	/**
	 * 获取“待星号的真实姓名”
	 */
	public String getUserNameView() {
		if(userName==null){
			return null;
		}
		if(userName.length() <= 1) {  
			return "*";  
	    }else if(userName.length()==2){  
	    	return userName.replaceAll("([\\u4e00-\\u9fa5]{1})(.*)", "$1*");  
	    }else{
	    	int xingLength = userName.length()-2;
	    	String xing = "";
	    	for( int i=0;i<xingLength;i++ ){
	    		xing+="*";
	    	}
	    	return userName.replaceAll("([\\u4e00-\\u9fa5]{1})(.+)([\\u4e00-\\u9fa5]{1})", "$1"+xing+"$3");  
	    }  
	}
	/**
	 * 获取“真实姓名”
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置“真实姓名”
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取“登陆手机号”
	 */
	public String getLoginPhone() {
		return loginPhone;
	}

	/**
	 * 设置“登陆手机号”
	 */
	public void setLoginPhone(String loginPhone) {
		this.loginPhone = loginPhone;
	}
	/**
	 * 获取“登陆邮箱”
	 */
	public String getLoginEmail() {
		return loginEmail;
	}
	/**
	 * 获取“隐藏登陆邮箱”
	 */
	public String getLoginEmailView() {
		if(loginEmail!=null&&loginEmail.length()>0 && loginEmail.contains("@")){
			String emailPrefix = loginEmail.substring(0,loginEmail.lastIndexOf("@"));
			String emailLastfix = loginEmail.substring(loginEmail.lastIndexOf("@"));
			String xing = "";
			if(emailPrefix.length()==1){
				return "*"+emailLastfix;
			}else if(emailPrefix.length()<5){
				for(int i =0;i<emailPrefix.length()-2;i++){
					xing +="*";
				}
				return loginEmail.replaceAll("(\\w)(\\w+)(\\w)(@\\w+\\.[a-z]+(\\.[a-z]+)?)", "$1"+xing+"$3$4");
			}else if(emailPrefix.length()>4){
				for(int i =0;i<emailPrefix.length()-4;i++){
					xing +="*";
				}
				return loginEmail.replaceAll("(\\w{3})(\\w+)(\\w)(@\\w+\\.[a-z]+(\\.[a-z]+)?)", "$1"+xing+"$3$4");
			}
		}
		return "";
	}

	/**
	 * 设置“登陆邮箱”
	 */
	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}
	/**
	 * 获取“登陆IP”
	 */
	public String getLoginIp() {
		return loginIp;
	}

	/**
	 * 设置“登陆IP”
	 */
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	/**
	 * 获取“登陆时间”
	 */
	public Date getLoginDate() {
		return loginDate;
	}

	/**
	 * 设置“登陆时间”
	 */
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	/**
	 * 获取“论坛用户名”
	 */
	public String getForumName() {
		return forumName;
	}

	/**
	 * 设置“论坛用户名”
	 */
	public void setForumName(String forumName) {
		this.forumName = forumName;
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
	 * 获取“用户身份证”
	 */
	public String getUserCardNo() {
		return userCardNo;
	}
	/**
	 * 展示“用户身份证”
	 */
	public String getUserCardNoView() {
		if(userCardNo!=null){
			return userCardNo.replaceAll("(\\d{3})\\d+(\\w{4})", "$1**********$2");
		}
		return "";
	}

	/**
	 * 设置“用户身份证”
	 */
	public void setUserCardNo(String userCardNo) {
		this.userCardNo = userCardNo;
	}
	/**
	 * 获取“返佣账号数量”
	 */
	public Integer getRebateCount() {
		return rebateCount;
	}

	/**
	 * 设置“返佣账号数量”
	 */
	public void setRebateCount(Integer rebateCount) {
		this.rebateCount = rebateCount;
	}
	/**
	 * 获取“帐号安全级别”
	 */
	public String getSecurityLevel() {
		return securityLevel;
	}

	/**
	 * 设置“帐号安全级别”
	 */
	public void setSecurityLevel(String securityLevel) {
		this.securityLevel = securityLevel;
	}
	/**
	 * 获取“用户状态”
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 设置“用户状态”
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取“用户创建类型”
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * 设置“用户创建类型”
	 */
	public void setUserType(String userType) {
		this.userType = userType;
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
	public BigDecimal getRebateAmount() {
		return rebateAmount;
	}

	/**
	 * 设置“累计返佣金额”
	 */
	public void setRebateAmount(BigDecimal rebateAmount) {
		this.rebateAmount = rebateAmount;
	}
	/**
	 * 获取“累计返佣金额（未解绑）”
	 */
	public BigDecimal getRebateAmountEx() {
		return rebateAmountEx;
	}

	/**
	 * 设置“累计返佣金额（未解绑）”
	 */
	public void setRebateAmountEx(BigDecimal rebateAmountEx) {
		this.rebateAmountEx = rebateAmountEx;
	}
	/**
	 * 获取“累计提现金额”
	 */
	public BigDecimal getWithDrawAmount() {
		return withDrawAmount;
	}

	/**
	 * 设置“累计提现金额”
	 */
	public void setWithDrawAmount(BigDecimal withDrawAmount) {
		this.withDrawAmount = withDrawAmount;
	}
	/**
	 * 获取“邀请人ID”
	 */
	public Long getInviteId() {
		return inviteId;
	}

	/**
	 * 设置“邀请人ID”
	 */
	public void setInviteId(Long inviteId) {
		this.inviteId = inviteId;
	}
	/**
	 * 获取“创建人员ID”
	 */
	public Long getCreateUserId() {
		return createUserId;
	}

	/**
	 * 设置“创建人员ID”
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取“创建时间”
	 */
	public Date getCreatedate() {
		return createDate;
	}

	/**
	 * 设置“创建时间”
	 */
	public void setCreatedate(Date createDate) {
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
	 * 获取“当前客户等级编号”
	 */
	public Long getLevelId() {
		return levelId;
	}

	/**
	 * 设置“当前客户等级编号”
	 */
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}
	/**
	 * 获取“是否自动调整等”
	 */
	public String getIsAut() {
		return isAut;
	}

	/**
	 * 设置“是否自动调整等”
	 */
	public void setIsAut(String isAut) {
		this.isAut = isAut;
	}
	/**
	 * 获取“计算后实际等级”
	 */
	public Long getCalculateLevelId() {
		return calculateLevelId;
	}

	/**
	 * 设置“计算后实际等级”
	 */
	public void setCalculateLevelId(Long calculateLevelId) {
		this.calculateLevelId = calculateLevelId;
	}
	/**
	 * 获取“手动调整起始日期”
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * 设置“手动调整起始日期”
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * 获取“手动调整结束日期”
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置“手动调整结束日期”
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	 * 用户等级名称
	 */
	@Transient
	private String levelName;
	/**
	 * 下一个用户等级名称
	 */
	@Transient
	private String nextLevelName;
	/**
	 * 资金流水笔数
	 */
	@Transient
	private Long fundflowing;
	/**
	 * 账号名称
	 */
	@Transient
	private String acctName;

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public BigDecimal getInviteAmount() {
		return inviteAmount;
	}

	public void setInviteAmount(BigDecimal inviteAmount) {
		this.inviteAmount = inviteAmount;
	}

	public String getNextLevelName() {
		return nextLevelName;
	}

	public void setNextLevelName(String nextLevelName) {
		this.nextLevelName = nextLevelName;
	}

	public Long getFundflowing() {
		return fundflowing;
	}

	public void setFundflowing(Long fundflowing) {
		this.fundflowing = fundflowing;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getWithDrawCode() {
		return withDrawCode;
	}

	public void setWithDrawCode(int withDrawCode) {
		this.withDrawCode = withDrawCode;
	}

	public Date getCodeDate() {
		return codeDate;
	}

	public void setCodeDate(Date codeDate) {
		this.codeDate = codeDate;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public Date getLatestLockTime() {
		return latestLockTime;
	}

	public void setLatestLockTime(Date latestLockTime) {
		this.latestLockTime = latestLockTime;
	}

}
