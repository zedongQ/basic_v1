package org.ieforex.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.ieforex.enums.RebatePeriodType;

@Table(name="dealer")
public class Dealer implements Serializable {
	private static final long serialVersionUID = 1L;
	
		/**
		 * 优惠信息
		 */
		@Transient
		private List<WPromotionalDiscount> promotional;
	
	 	public List<WPromotionalDiscount> getPromotional() {
			return promotional;
		}
		public void setPromotional(List<WPromotionalDiscount> promotional) {
			this.promotional = promotional;
		}

		/**
		 * 交易品种
		 */
		@Transient
		private String transactionType;

	    public String getTransactionType() {
			return transactionType;
		}
		public void setTransactionType(String transactionType) {
			this.transactionType = transactionType;
		}

		/**
		 * 监管机制
		 */
		@Transient
		private String regulatoryOrg;
		
		
		public String getRegulatoryOrg() {
			return regulatoryOrg;
		}
		public void setRegulatoryOrg(String regulatoryOrg) {
			this.regulatoryOrg = regulatoryOrg;
		}
		
		/**
		 * 开户地址
		 */
		@Transient
		private List<Map<String,String>> openUrl;
		
		public List<Map<String,String>> getOpenUrl() {
			return openUrl;
		}
		public void setOpenUrl(List<Map<String,String>> openUrl) {
			this.openUrl = openUrl;
		}

		/*基本信息及前端处理所需字段*/
		
		

		@Id
		@Column(name="dealer_id")
		private Long dealerId;
	    /**
		  * 平台信息
		  */
		@Column(name="dealer_info")
		private String dealerInfo;
	    /**
		  * 默认返佣标准
		  */
	    @Column(name="rebate_standard")
		private BigDecimal rebateStandard;

	    /**
	     * 公司名称
	     */
	    @Column(name="company_name")
	    private String companyName;
	    /**
		  * 数据状态
		  */
	    @Column(name="data_status")
		private String dataStatus;
	    
	    /**
		  * create_user_id
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
		  * 首页Logo保存地址
		  */
	    @Column(name="logo_url")
		private String logoUrl;
	    
	    /**
	 	  * 明细Logo保存地址
	 	  */
	    @Column(name="logo_url2")
	 	private String logoUrl2;  
	    
	    /**
		  * 是否首页展示
		  */
	    @Column(name="is_home_page")
		private String isHomePage;
	    
	    /**
		  * 是否首页展示点差
		  */
	    @Column(name="is_home_dc")
		private String isHomeDc;

	    /**
		  * 首页排序
		  */
	    @Column(name="home_page_sort")
		private Long homePageSort;

	    /**
		  * 默认排序
		  */
	    @Column(name="default_sort")
		private Long defaultSort;


	    /**
		  * 交易商发票代码
		  */
	    @Column(name="invoice_no")
		private String invoiceNo;

	    /**
		  * 交易商地址
		  */
	    @Column(name="address")
	    private String address;

	    /**
		  * 交易商电话
		  */
	    @Column(name="telephone")
	    private String telephone;
	    /**
		  * 点差映射编号
		  */
	    @Column(name="pro_name")
		private String proName;  
	       

	/*平台返现*/    
	   
	    /**
		  * 返佣周期类型
		  */
	    @Column(name="rebate_period_type")
		private String rebatePeriodType;
		  
		public String getRebatePeriodTypeName() {
			if(rebatePeriodType!=null){
				return RebatePeriodType.getDisplayName(rebatePeriodType).describe();
			}
			return null;
		}      
	    
		/**
		  * 外汇返佣
		  */
	    @Column(name="forex_ratio")
		private String forexRatio;

	    /**
		  * 黄金返佣
		  */
	    @Column(name="gold_ratio")
		private String goldRatio;

	    /**
		  * 白银返佣
		  */
	    @Column(name="silver_ratio")
		private String silverRatio;

	    /**
		  * 原油返佣
		  */
	    @Column(name="crude_oil_ratio")
		private String crudeOilRatio; 
	    
	    /**
		  * 返佣持仓限制
		  */
	    @Column(name="position_limit")
		private String positionLimit;     
	    
	/*交易点差及利息*/
	   
	    /**
		  * 点差类型
		  */
	    @Column(name="pips_type")
		private String pipsType;      
	   
	   	/**
		  * 欧美点差
		  */
	    @Column(name="occident_pips")
		private String occidentPips;    
	  
	   	/**
		  * 黄金点差
		  */
	  	@Column(name="gold_pips")
		private String goldPips;    
	  
	   	/**
		  * 原油点差
		  */
	  	@Column(name="crude_oil_pips")
		private String crudeOilPips;        
	   
	   	/**
		  * 离岸人民币点差
		  */
	  	@Column(name="cnh")
		private String cnh;    

	  	/**
		  * A股指数点差
		  */
	  	@Column(name="a_share_index")
		private String aShareIndex;     
	    
	  	/**
		  * 隔夜利息
		  */
	 	@Column(name="rollovers")
		private String rollovers;     
	    
	    
	    
	/*平台模式*/


	    /**
		  * 平台类型
		  */
	    @Column(name="platform_type")
		private String platformType;  
	    
	    /**
		  * 平台模式
		  */
	    @Column(name="platform_mode")
		private String platformMode;
	        
	    /**
		  * 最低入金
		  */
	    @Column(name="min_money")
		private String minMoney;
	    
	    /**
		  * 最大杠杆
		  */
	    @Column(name="max_leverage")
		private String maxLeverage;  
	    
	    /**
		  * 最小手数
		  */
	    @Column(name="trade_min_amount")
		private String tradeMinAmount;
	    
	    /**
		  * 最大交易量
		  */
	    @Column(name="max_business")
		private String maxBusiness;

	    /**
		  * 爆仓比例
		  */
	    @Column(name="explosionra")
	    private String explosionra;
	    
	    /**
		  * 剥头皮
		  */
	    @Column(name="scalp")
		private String scalp;
	    
	    public String getScalpName() {
			return "0".equals(scalp)?"支持":"不支持";
		}

		/**
		  * 对冲
		  */
	    @Column(name="hedging")
	    private String hedging;
	    
	    public String getHedgingName() {
			return "0".equals(hedging)?"支持":"不支持";
		}
	    
	/*平台出入金*/
	    
	    /**
		  * 是否支持人民币出入金
		  */
	    @Column(name="rmb_in_out")
	    private String rmbInOut;
	    
	    public String getRmbInOutName() {
			return "0".equals(rmbInOut)?"支持":"不支持";
		}
	    
	    /**
		  * 出入金方式
		  */
	    @Column(name="in_out_ways")
	    private String inOutWays;
	    
	    /**
		  * 出金手续费
		  */
		@Column(name="outfee")
		private String outfee;
		
		/**
		  * 入金手续费
		  */
		@Column(name="infee")
		private String infee;
		
	    /**
		  * 出金到账周期
		  */
		@Column(name="out_transfer_cycle")
		private String outTransferCycle;
		
		/**
		  * 入金到账周期
		  */
		@Column(name="in_transfer_cycle")
		private String inTransferCycle;
	    
		/**
		  * 账户结算币种
		  */
		@Column(name="close_currency")
		private String closeCurrency;
		
	/*平台信息*/
	    
	    /**
	     * 平台成立时间
	     */
	    @Column(name="founding_time")
	    private String foundingTime;    

	    /**
	     * 语言
	     */
	    @Column(name="language")
	    private String language;
	    
	    /**
		  * 交易商名称
		  */
	    private String name;

	    /**
		  * 交易商中文名称
		  */
	    @Column(name="chn_name")
		private String chnName;
	    
	    /**
		  * 官网地址
		  */
	    @Column(name="website_url")
		private String websiteUrl;
	    
	    
	/*新增*/
	    /**
	     * 客户指南
	     */
	    @Column(name="customer_guide")
	    private String customerGuide;
	    /**
	     * 返现流程
	     */
	    @Column(name="return_flow")
	    private String returnFlow;
	    /**
	     * 转户指南
	     * @return
	     */
	    @Column(name="transfer_guide")
	    private String transferGuide;
	    /**
	     * 宣传图片
	     */
	    @Column(name="logo_url3")
	    private	String logoUrl3;
	    
	    
	    /**
		  * 推广地址
		  */
	    @Column(name="promote_url")
		private String promoteUrl;
	    
	    
		public String getPromoteUrl() {
			return promoteUrl;
		}
		public void setPromoteUrl(String promoteUrl) {
			this.promoteUrl = promoteUrl;
		}
		public Long getDealerId() {
			return dealerId;
		}
		public void setDealerId(Long dealerId) {
			this.dealerId = dealerId;
		}
		public String getDealerInfo() {
			return dealerInfo;
		}
		public void setDealerInfo(String dealerInfo) {
			this.dealerInfo = dealerInfo;
		}
		public BigDecimal getRebateStandard() {
			return rebateStandard;
		}
		public void setRebateStandard(BigDecimal rebateStandard) {
			this.rebateStandard = rebateStandard;
		}
		public String getDataStatus() {
			return dataStatus;
		}
		public void setDataStatus(String dataStatus) {
			this.dataStatus = dataStatus;
		}
		public Long getCreateUserId() {
			return createUserId;
		}
		public void setCreateUserId(Long createUserId) {
			this.createUserId = createUserId;
		}
		public Date getCreateDate() {
			return createDate;
		}
		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}
		public Long getUpdateUserId() {
			return updateUserId;
		}
		public void setUpdateUserId(Long updateUserId) {
			this.updateUserId = updateUserId;
		}
		public Date getUpdateDate() {
			return updateDate;
		}
		public void setUpdateDate(Date updateDate) {
			this.updateDate = updateDate;
		}
		public String getLogoUrl() {
			return logoUrl;
		}
		public void setLogoUrl(String logoUrl) {
			this.logoUrl = logoUrl;
		}
		public String getLogoUrl2() {
			return logoUrl2;
		}
		public void setLogoUrl2(String logoUrl2) {
			this.logoUrl2 = logoUrl2;
		}
		public String getIsHomePage() {
			return isHomePage;
		}
		public void setIsHomePage(String isHomePage) {
			this.isHomePage = isHomePage;
		}
		public Long getHomePageSort() {
			return homePageSort;
		}
		public void setHomePageSort(Long homePageSort) {
			this.homePageSort = homePageSort;
		}
		public Long getDefaultSort() {
			return defaultSort;
		}
		public void setDefaultSort(Long defaultSort) {
			this.defaultSort = defaultSort;
		}
		public String getInvoiceNo() {
			return invoiceNo;
		}
		public void setInvoiceNo(String invoiceNo) {
			this.invoiceNo = invoiceNo;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getTelephone() {
			return telephone;
		}
		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}
		public String getProName() {
			return proName;
		}
		public void setProName(String proName) {
			this.proName = proName;
		}
		
		public String getRebatePeriodType() {
			return rebatePeriodType;
		}
		public void setRebatePeriodType(String rebatePeriodType) {
			this.rebatePeriodType = rebatePeriodType;
		}
		public String getForexRatio() {
			return forexRatio;
		}
		public void setForexRatio(String forexRatio) {
			this.forexRatio = forexRatio;
		}
		public String getGoldRatio() {
			return goldRatio;
		}
		public void setGoldRatio(String goldRatio) {
			this.goldRatio = goldRatio;
		}
		public String getSilverRatio() {
			return silverRatio;
		}
		public void setSilverRatio(String silverRatio) {
			this.silverRatio = silverRatio;
		}
		public String getCrudeOilRatio() {
			return crudeOilRatio;
		}
		public void setCrudeOilRatio(String crudeOilRatio) {
			this.crudeOilRatio = crudeOilRatio;
		}
		public String getPositionLimit() {
			return positionLimit;
		}
		public void setPositionLimit(String positionLimit) {
			this.positionLimit = positionLimit;
		}
		public String getPipsType() {
			return pipsType;
		}
		public void setPipsType(String pipsType) {
			this.pipsType = pipsType;
		}
		public String getOccidentPips() {
			return occidentPips;
		}
		public void setOccidentPips(String occidentPips) {
			this.occidentPips = occidentPips;
		}
		public String getCrudeOilPips() {
			return crudeOilPips;
		}
		public void setCrudeOilPips(String crudeOilPips) {
			this.crudeOilPips = crudeOilPips;
		}
		public String getCnh() {
			return cnh;
		}
		public void setCnh(String cnh) {
			this.cnh = cnh;
		}
		public String getaShareIndex() {
			return aShareIndex;
		}
		public void setaShareIndex(String aShareIndex) {
			this.aShareIndex = aShareIndex;
		}
		public String getRollovers() {
			return rollovers;
		}
		public void setRollovers(String rollovers) {
			this.rollovers = rollovers;
		}
		public String getPlatformType() {
			return platformType;
		}
		public void setPlatformType(String platformType) {
			this.platformType = platformType;
		}
		public String getPlatformMode() {
			return platformMode;
		}
		public void setPlatformMode(String platformMode) {
			this.platformMode = platformMode;
		}
		public String getMinMoney() {
			return minMoney;
		}
		public void setMinMoney(String minMoney) {
			this.minMoney = minMoney;
		}
		public String getMaxLeverage() {
			return maxLeverage;
		}
		public void setMaxLeverage(String maxLeverage) {
			this.maxLeverage = maxLeverage;
		}
		public String getTradeMinAmount() {
			return tradeMinAmount;
		}
		public void setTradeMinAmount(String tradeMinAmount) {
			this.tradeMinAmount = tradeMinAmount;
		}
		public String getMaxBusiness() {
			return maxBusiness;
		}
		public void setMaxBusiness(String maxBusiness) {
			this.maxBusiness = maxBusiness;
		}
		public String getExplosionra() {
			return explosionra;
		}
		public void setExplosionra(String explosionra) {
			this.explosionra = explosionra;
		}
		public String getScalp() {
			return scalp;
		}
		public void setScalp(String scalp) {
			this.scalp = scalp;
		}
		public String getHedging() {
			return hedging;
		}
		public void setHedging(String hedging) {
			this.hedging = hedging;
		}
		public String getRmbInOut() {
			return rmbInOut;
		}
		public void setRmbInOut(String rmbInOut) {
			this.rmbInOut = rmbInOut;
		}
		public String getInOutWays() {
			return inOutWays;
		}
		public void setInOutWays(String inOutWays) {
			this.inOutWays = inOutWays;
		}
		public String getOutFee() {
			return outfee;
		}
		public void setOutFee(String outfee) {
			this.outfee = outfee;
		}
		public String getInFee() {
			return infee;
		}
		public void setInFee(String infee) {
			this.infee = infee;
		}
		public String getOutTransferCycle() {
			return outTransferCycle;
		}
		public void setOutTransferCycle(String outTransferCycle) {
			this.outTransferCycle = outTransferCycle;
		}
		public String getInTransferCycle() {
			return inTransferCycle;
		}
		public void setInTransferCycle(String inTransferCycle) {
			this.inTransferCycle = inTransferCycle;
		}
		public String getCloseCurrency() {
			return closeCurrency;
		}
		public void setCloseCurrency(String closeCurrency) {
			this.closeCurrency = closeCurrency;
		}
		public String getFoundingTime() {
			return foundingTime;
		}
		public void setFoundingTime(String foundingTime) {
			this.foundingTime = foundingTime;
		}
		public String getLanguage() {
			return language;
		}
		public void setLanguage(String language) {
			this.language = language;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getChnName() {
			return chnName;
		}
		public void setChnName(String chnName) {
			this.chnName = chnName;
		}
		public String getWebsiteUrl() {
			return websiteUrl;
		}
		public void setWebsiteUrl(String websiteUrl) {
			this.websiteUrl = websiteUrl;
		}

		public String getGoldPips() {
			return goldPips;
		}
		public void setGoldPips(String goldPips) {
			this.goldPips = goldPips;
		}
		public String getOutfee() {
			return outfee;
		}
		public void setOutfee(String outfee) {
			this.outfee = outfee;
		}
		public String getInfee() {
			return infee;
		}
		public void setInfee(String infee) {
			this.infee = infee;
		}
		
		public String getCustomerGuide() {
			return customerGuide;
		}
		public void setCustomerGuide(String customerGuide) {
			this.customerGuide = customerGuide;
		}
		public String getReturnFlow() {
			return returnFlow;
		}
		public void setReturnFlow(String returnFlow) {
			this.returnFlow = returnFlow;
		}
		public String getTransferGuide() {
			return transferGuide;
		}
		public void setTransferGuide(String transferGuide) {
			this.transferGuide = transferGuide;
		}
		public String getLogoUrl3() {
			return logoUrl3;
		}
		public void setLogoUrl3(String logoUrl3) {
			this.logoUrl3 = logoUrl3;
		}
		public String getIsHomeDc() {
			return isHomeDc;
		}
		public void setIsHomeDc(String isHomeDc) {
			this.isHomeDc = isHomeDc;
		}
		public String getCompanyName() {
			return companyName;
		}
		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}
		
		
}
