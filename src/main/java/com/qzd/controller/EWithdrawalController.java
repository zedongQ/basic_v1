package org.ieforex.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bcics.sso.SSOHelper;
import org.bcics.sso.Token;
import org.bcics.sso.annotation.Permission;
import org.ieforex.entity.ECollectionSetting;
import org.ieforex.entity.ECustomer;
import org.ieforex.entity.ECustomerAccountBasic;
import org.ieforex.entity.ECustomerAccountInfo;
import org.ieforex.entity.ECustomerLimit;
import org.ieforex.entity.EWithdrawal;
import org.ieforex.entity.EWithdrawalColWay;
import org.ieforex.service.ECollectionSettingService;
import org.ieforex.service.ECustomerAccountBasicService;
import org.ieforex.service.ECustomerAccountInfoService;
import org.ieforex.service.ECustomerLimitService;
import org.ieforex.service.ECustomerService;
import org.ieforex.service.ETradeAcctService;
import org.ieforex.service.EWithdrawalService;
import org.ieforex.utils.AesCryptUtil;
import org.ieforex.utils.DateUtil;
import org.ieforex.utils.GetRate;
import org.ieforex.utils.MathUtil;
import org.ieforex.utils.RESULT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 提现申请表
 * 
 * @author brilliance
 * @email 
 * @date 2017-04-26 14:02:16
 */
@Controller
@RequestMapping("ewithdrawal")
public class EWithdrawalController extends AbstractController {
	
	@Autowired
	protected HttpServletResponse response;
	@Autowired
	private EWithdrawalService eWithdrawalService;
	@Autowired
	private ECustomerService eCustomerService;
	@Autowired
	private ETradeAcctService eTradeAcctService;
	@Autowired
	private ECustomerAccountInfoService customerAccountInfoService;
	@Autowired
	private ECustomerAccountBasicService customerAccountBasicService;
	@Autowired
	private ECollectionSettingService collectionSettingService;
	@Autowired
	private ECustomerLimitService customerLimitService;
	/**
	 * 获得收款账号
	 */
	@ResponseBody
	@RequestMapping(value="getpays/{type}",method=RequestMethod.POST)
	public List<Map<String, Object>> getAccountInfos(@PathVariable("type") String type,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		Map<String, Object> accountInfo = new HashMap<String, Object>();
		List<Map<String, Object>> eCustomerAccountInfos = new ArrayList<Map<String, Object>>();
		Token token = SSOHelper.getToken(request);
		if(token!=null){
			Long customerId = Long.parseLong(token.getUid());
			Map<String, Object> basicMap = customerAccountBasicService.getBacicIdbyCustomerId(customerId);
			if(null!=basicMap&&basicMap.size()>0){
				map.put("basicId", basicMap.get("basicId"));
				map.put("customerId", customerId);
			}
			if(type!=null&&type!=""){
				if("0".equals(type)){
					map.put("payType", "and a.type in (1,3,4)");
				}else if ("1".equals(type)){
					map.put("payType", "and a.type in (0)");
				}
				List<ECustomerAccountInfo> customerAccountInfos = customerAccountInfoService.getBycustomerId(map);
				for (ECustomerAccountInfo eCustomerAccountInfo : customerAccountInfos) {
					accountInfo.put("accountId", eCustomerAccountInfo.getAccountId());
					accountInfo.put("accountAcctView", eCustomerAccountInfo.getAccountAcctView());
					accountInfo.put("typeName", eCustomerAccountInfo.getTypeName());
					eCustomerAccountInfos.add(accountInfo);
				}
			}
		}
		return eCustomerAccountInfos;
	}
	
	
	
	/**
	 * 保存
	 * 新增提现申请
	 */
	@ResponseBody
	@Permission("usr:logined")
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(@RequestBody String params,HttpServletRequest request){
		response.setHeader("Access-Control-Allow-Origin", "*");
		Date d = new Date();
		Map<String, String> paramMap;
		Token attrToken = SSOHelper.attrToken(request);
		Long customerId = Long.parseLong(attrToken.getUid());
		ECustomer customer = eCustomerService.queryUserById(customerId);
		String userCardNo = customer.getUserCardNo();
		String userName = customer.getUserName();
		try {
			//页面传过来的值
			paramMap = AesCryptUtil.decrypt2JSON(params);
			String acctNumId = paramMap.get("acctNum");//收款账号
			String wantAmountString = paramMap.get("withdrawalAmount");
			String moneyType = paramMap.get("currencyType");//币种
			if("1".equals(moneyType)&&(userCardNo==null||"".equals(userCardNo)||userName==null||"".equals(userName))) {
				return RESULT.error(message("user.isname.error")).aes();
			}
			//判断是否被限制提现
			ECustomerLimit customerLimit = customerLimitService.searchById(customerId);
			if(customerLimit!=null){
				if("1".equals(customerLimit.getLimitType())
						&&(DateUtil.compareToDate(customerLimit.getLimitDate(),new Date())!=-1)){
					
					String limitDate = new SimpleDateFormat("yyyy-MM-dd").format(customerLimit.getLimitDate());
					String limitinfo = message("user.withdrawal.limit")+limitDate+"！";
					return RESULT.error(limitinfo).aes();
				}
			}
			//开始计算费用及保存申请
			EWithdrawal eWithdrawal = new EWithdrawal();
			BigDecimal balanceAvailable;
			BigDecimal wantAmount = new BigDecimal(wantAmountString);//需要提现金额
			
			//用户数据
			balanceAvailable = customer.getAvailable();
			BigDecimal exchangeRate = customer.getFeeRate();
			
			//后台计算手续费费用
			EWithdrawalColWay eWithdrawalColWay = new EWithdrawalColWay();
			eWithdrawalColWay.setAcctNum(acctNumId);
			eWithdrawalColWay.setCurrency(moneyType);
			//eWithdrawalColWay.setWantAmount(wantAmountString);
			List<ECollectionSetting> colRate = getColRate(eWithdrawalColWay);
			
			//获取获取汇率调控值
			if("1".equals(moneyType)){
				if(colRate.get(0).getExchangeRateType()!=null){
					BigDecimal rmbExchangeRate = colRate.get(0).getRmbExchangeRate();
					BigDecimal rmbExchange = colRate.get(0).getRmbExchange();
					if(rmbExchangeRate!=null && "0".equals(colRate.get(0).getExchangeRateType())){
						exchangeRate = MathUtil.parseBigDecimal(exchangeRate.multiply(rmbExchangeRate).divide(new BigDecimal("100"),6,6), 2, true);
					}else if(rmbExchange!=null && "1".equals(colRate.get(0).getExchangeRateType())){
						exchangeRate = MathUtil.parseBigDecimal(exchangeRate.subtract(rmbExchange), 2, true);
					}
				}
				wantAmount = MathUtil.parseBigDecimal(wantAmount.divide(exchangeRate,6,6),2,true);
			}
			
			//余额不足直接返回提示
			if((MathUtil.parseBigDecimal(wantAmount,2,true)).compareTo(MathUtil.parseBigDecimal(balanceAvailable,2,true))==1){
				return RESULT.error(message("user.amount.insufficient"))
							 .aes();
			}
			if(wantAmount.compareTo(new BigDecimal(5000))==1) {
				return RESULT.error(message("user.amount.outfive"))
						 .aes();
			}
			
			//获取手续费计算方式
			BigDecimal fee = new BigDecimal(0);//根据手续费计算方式得出的手续费
			BigDecimal shouxufei = new BigDecimal(0);//计算后的真实手续费
			BigDecimal withdrawalAmountCurrency = new BigDecimal(0);
			BigDecimal amountRmb = new BigDecimal(0);
			if("1".equals(eWithdrawalColWay.getCurrency())){//"1"是RMB
				eWithdrawalColWay.setWantAmount(wantAmountString);
				colRate = getColRate(eWithdrawalColWay);
				if("0".equals(colRate.get(0).getFeeWay())){//"0"按比例收取费用
					fee = colRate.get(0).getFeePercent();
					shouxufei = wantAmount.multiply((fee.divide(new BigDecimal(100))).multiply(exchangeRate));
				}else if("1".equals(colRate.get(0).getFeeWay())){//"1"按笔收取
					fee = colRate.get(0).getFee();
					shouxufei = fee;
				}
				withdrawalAmountCurrency = new BigDecimal(wantAmountString);//人名币
				withdrawalAmountCurrency = withdrawalAmountCurrency.subtract(shouxufei);
				amountRmb =MathUtil.parseBigDecimal(withdrawalAmountCurrency,2,true);
			}else if("0".equals(eWithdrawalColWay.getCurrency())){//"0"是美元
				eWithdrawalColWay.setWantAmount(wantAmountString);
				colRate = getColRate(eWithdrawalColWay);
				if("0".equals(colRate.get(0).getFeeWay())){//"0"按比例收取费用
					fee = colRate.get(0).getFeePercent();
					shouxufei = (wantAmount.multiply(fee.divide(new BigDecimal(100),2,2)));
				}else if("1".equals(colRate.get(0).getFeeWay())){//"1"按笔收取
					fee = colRate.get(0).getFee();
					shouxufei = fee;
				}
				withdrawalAmountCurrency = wantAmount.subtract(shouxufei);
				amountRmb = MathUtil.parseBigDecimal(withdrawalAmountCurrency.multiply(exchangeRate), 2, true);
			}
			
			//扣除手续费后如果＜=0就返回提现失败
     		if(new BigDecimal("0").compareTo(withdrawalAmountCurrency)!=-1){
				return RESULT.error(message("user.amount.insufficient")).aes();
			}	
     		
			if(customerId!=null){
				Map<String,Object> getTypemMap = new HashMap<String,Object>();
				Long accountId = MathUtil.parseLong(acctNumId);
				getTypemMap.put("customerId", customerId);
				getTypemMap.put("accountId", accountId);
				//获取提现账号信息
				ECustomerAccountInfo accountInfo = customerAccountInfoService.getcollectionById(getTypemMap);

				String acctNum = accountInfo.getAccountAcct();
				String payType= accountInfo.getType();
				String payeeAddress = accountInfo.getPayeeAddress();
				eWithdrawal.setCustomerId(customerId);
				//提现金额
				eWithdrawal.setWithdrawalAmount(wantAmount);
				//根据提现币种计算出的手续费
				eWithdrawal.setWithdrawalFee(shouxufei);
				eWithdrawal.setWithdrawalCurrency(moneyType);
				//美元转换后的人民币
				eWithdrawal.setAmountRmb(amountRmb);
				//汇率
				eWithdrawal.setExchangeRate(exchangeRate);
				//实际到帐金额
				eWithdrawal.setWithdrawalAmountCurrency(withdrawalAmountCurrency);
				//支付方式
				eWithdrawal.setWithdrawalWay(payType);
				
				//根据交易类型添加收款账号信息
				if("0".equals(payType)){//银行
					eWithdrawal.setPayeeBankAcctNo(acctNum);
					eWithdrawal.setPayeeBankName(accountInfo.getBankName());
				}else if("1".equals(payType)){//电汇
					eWithdrawal.setPayeeTtBankAcctNo(acctNum);
					eWithdrawal.setPayeeTtAddress(payeeAddress);
					eWithdrawal.setPayeeTtBankName(accountInfo.getAccountName());
					eWithdrawal.setPayeeTtBankSwiftCode(accountInfo.getBankSwiftCode());
				}else if("2".equals(payType)){//支付宝
					eWithdrawal.setPayeeAlipayAcctNo(acctNum);
				}else if("3".equals(payType)){//MB账号
					eWithdrawal.setPayeeMbAcctNo(acctNum);
				}else if("4".equals(payType)){//paypal账号
					eWithdrawal.setPayeePaypalNo(acctNum);
				}
				//生成流水号
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");  
				String dateNowStr = sdf.format(d);  
				
				BigDecimal amount = eWithdrawal.getWithdrawalAmount();//美元
				String withdrawalCurrency = eWithdrawal.getWithdrawalCurrency();
				
				//客户收款帐号基本信息表ECustomerAccountBasic
				ECustomerAccountBasic basic = customerAccountBasicService.searchByCustomerId(customerId);
				if(basic!=null){
					eWithdrawal.setPayeeName(basic.getAcctName());
					eWithdrawal.setPayeeEngName(basic.getEngName()+" "+basic.getEngSurname());
					eWithdrawal.setPayeePhone(basic.getAccountPhone());
					eWithdrawal.setPayeeEmail(basic.getAccountEmail());
					if(eWithdrawal.getPayeeAddress()==null || "".equals(eWithdrawal.getPayeeAddress()) ) {
						eWithdrawal.setPayeeAddress(basic.getBankAddress());
					}
					String engName = basic.getEngName();
					String engSurname = basic.getEngSurname();
					if(engName==null){
						engName = "";
					}
					if(engSurname==null){
						engSurname = "";
					}
					eWithdrawal.setPayeeEngName(engName+" "+engSurname);
					eWithdrawal.setPayeeCardNo(basic.getCardNo());
				}
				
				//客户收款帐号表ECustomerAccountInfo
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("customerId", customerId);
				if(null!=customer){
					eWithdrawal.setLoginEmail(customer.getLoginEmail());
					eWithdrawal.setLoginPhone(customer.getLoginPhone());
					eWithdrawal.setHolderName(customer.getUserName());
				}
				
				if(eTradeAcctService.getIsSameNameOrIsAgency(customerId).size()>0) {
					eWithdrawal.setStatus("0");
				}else {
					eWithdrawal.setStatus("7");
				}
				
				eWithdrawal.setCreateDate(d);
				eWithdrawal.setCreateUserId(customerId);
				eWithdrawal.setWithdrawalDate(d);
				eWithdrawal.setWithdrawalNo(dateNowStr);
				
				//当前用户的当日提现次数
				int today = eWithdrawalService.getWithdrawalNumOfTheDay(customerId);
				//是否连续三天进行提现
				int threeDay = eWithdrawalService.getWithdrawalForThreeDays(customerId);
				int dollarCompare = amount.compareTo(new BigDecimal(300));
				int rmbCompare = amountRmb.compareTo(new BigDecimal(2000));
				boolean compare = (dollarCompare==1) || (rmbCompare==1);
				if(today>=2){
					if("0".equals(withdrawalCurrency) && compare){
						eWithdrawal.setWithdrawalType("3");
						eWithdrawal.setWithdrawalTypeDesc("提现金额高，提现频次多");
						eWithdrawal.setWithdrawalAdvise("着重审核是否为本人操作以及近三月的资金流水");
					}else if("1".equals(withdrawalCurrency) && compare){
						eWithdrawal.setWithdrawalType("3");
						eWithdrawal.setWithdrawalTypeDesc("提现金额高，提现频次多");
						eWithdrawal.setWithdrawalAdvise("着重审核是否为本人操作以及近三月的资金流水");
					}else{
						eWithdrawal.setWithdrawalType("1");
						eWithdrawal.setWithdrawalTypeDesc("当日提款次数大于2次");
						eWithdrawal.setWithdrawalAdvise("着重审核是否为本人操作");
					}
				}else if(threeDay>0){
					if("0".equals(withdrawalCurrency) && compare){
						eWithdrawal.setWithdrawalType("3");
						eWithdrawal.setWithdrawalTypeDesc("提现金额高，提现频次多");
						eWithdrawal.setWithdrawalAdvise("着重审核是否为本人操作以及近三月的资金流水");
					}else if("1".equals(withdrawalCurrency) && compare){
						eWithdrawal.setWithdrawalType("3");
						eWithdrawal.setWithdrawalTypeDesc("提现金额高，提现频次多");
						eWithdrawal.setWithdrawalAdvise("着重审核是否为本人操作以及近三月的资金流水");
					}else{
						eWithdrawal.setWithdrawalType("1");
						eWithdrawal.setWithdrawalTypeDesc("连续3天提现");
						eWithdrawal.setWithdrawalAdvise("着重审核是否为本人操作");
					}
				}else{
					if("0".equals(withdrawalCurrency) && compare){
						eWithdrawal.setWithdrawalType("2");
						eWithdrawal.setWithdrawalTypeDesc("单笔提现金额大于300美元");
						eWithdrawal.setWithdrawalAdvise("着重审核资金交易流水");
					}else if("1".equals(withdrawalCurrency) && compare){
						eWithdrawal.setWithdrawalType("2");
						eWithdrawal.setWithdrawalTypeDesc("单笔提现金额大于2000元");
						eWithdrawal.setWithdrawalAdvise("着重审核资金交易流水");
					}else{
						eWithdrawal.setWithdrawalType("0");
						eWithdrawal.setWithdrawalTypeDesc("单笔提现金额小于300美元、2000元；当日提款次数少于等于2次，无连续3天提款记录");
						eWithdrawal.setWithdrawalAdvise("");
					}
				}
					eWithdrawal.setLoginIp(attrToken.getIp());
					eWithdrawal.setLoginTime(new Date(attrToken.getTime()));
					eWithdrawalService.insert(eWithdrawal);				
			}
		} catch (Exception e) {
			e.printStackTrace();
			return RESULT.error(message("user.withdrawal.error")).aes();
		}
		return RESULT.ok(message("user.withdrawal.ok"))
				.aes();
		
	}
	
	
	//抓去实时汇率并存入用户表中
	/**
	 * 获得汇率
	 */
	@ResponseBody
	@RequestMapping(value="getrate",method=RequestMethod.GET)
	public RESULT getNowRate(){
		Token token = SSOHelper.getToken(request);
		if(token!=null) {
			Long customerId = MathUtil.parseLong(token.getUid());

			Map<String,Object> map = new HashMap<String, Object>();
			String exchangeRateString = GetRate.exchangeRate("http://srh.bankofchina.com/search/whpj/search.jsp?erectDate=&nothing=&pjname=1316", 2, 3);
			if(exchangeRateString!=null) {
				BigDecimal exchangeRate = MathUtil.parseBigDecimal(new BigDecimal(exchangeRateString).divide(new BigDecimal("100")), 4, true);
				map.put("customerId", customerId);
				map.put("feeRate", exchangeRate);
				eCustomerService.updateFeeRate(map);
				return RESULT.ok(exchangeRate.toString());
			}
			return RESULT.error("提现异常，请联系客服！");
		}
		return null;
	}

	/**
	 * 获得汇率
	 */
	/*@ResponseBody
	@Permission("usr:logined")
	@RequestMapping(value="getrate",method=RequestMethod.POST)
	public String getRate(@RequestBody String[] parms){
		String url = parms[0];
		int tr = MathUtil.parseInt(parms[1]);
		int td = MathUtil.parseInt(parms[2]);
		String rate = GetRate.exchangeRate(url, tr, td);
		return rate;
	}*/
	
	/**
	 * 获得手续费以及手续费费率
	 */
	@ResponseBody
	@Permission("usr:logined")
	@RequestMapping(value="getcolrate",method=RequestMethod.POST)
	public List<ECollectionSetting> getColRate(@RequestBody EWithdrawalColWay eWithdrawalColWay){

		Token token = SSOHelper.attrToken(request);
		if(token!=null) {

			String acctNumId = eWithdrawalColWay.getAcctNum();
			String Waycurrency = eWithdrawalColWay.getCurrency();
			String wantAmount = eWithdrawalColWay.getWantAmount();
			
			Map<String,Object> getTypemMap = new HashMap<String,Object>();
			Long accountId = MathUtil.parseLong(acctNumId);
			getTypemMap.put("customerId",  MathUtil.parseLong(token.getUid()));
			getTypemMap.put("accountId", accountId);
			
			ECustomerAccountInfo accountInfo = customerAccountInfoService.getcollectionById(getTypemMap);
			
			Map<String,Object> map =new HashMap<String, Object>();
			String collection = accountInfo.getType();
			map.put("currency", Waycurrency);
			map.put("collectionWay", collection);
			if(!"0".equals(wantAmount)&&!"".equals(wantAmount)&&wantAmount!=null){
				map.put("wantAmount", wantAmount);
			}else{
				wantAmount = "0";
			}
			
			List<ECollectionSetting> collectionSettings = collectionSettingService.getFeeByWay(map);
			
			if(collectionSettings.size()==0){//若取不到区间中的手续费计算方式，则选择最大的值
				ECollectionSetting collectionSetting = new ECollectionSetting();
				Map<String,Object> initMap =new HashMap<String, Object>();
				initMap.put("currency", Waycurrency);
				initMap.put("collectionWay", collection);
				BigDecimal maxFee = collectionSettingService.getMaxFee(initMap);
				BigDecimal maxFeePercent = collectionSettingService.getMaxFeePercent(initMap);
				if(maxFee==null)maxFee=new BigDecimal("0");
				if(maxFeePercent==null)maxFeePercent = new BigDecimal("0");
				BigDecimal wamount = new BigDecimal(wantAmount);
				if(maxFee.compareTo(maxFeePercent.multiply(wamount))==-1){
					collectionSetting.setFee(maxFee);
					collectionSetting.setFeePercent(maxFeePercent);
					collectionSetting.setFeeWay("0");
				}else if(maxFee.compareTo(maxFeePercent.multiply(wamount))==1){
					collectionSetting.setFee(maxFee);
					collectionSetting.setFeePercent(maxFeePercent);
					collectionSetting.setFeeWay("1");
				}else if(maxFee.compareTo(maxFeePercent.multiply(wamount))==0){
					collectionSetting.setFee(maxFee);
					collectionSetting.setFeePercent(maxFeePercent);
					collectionSetting.setFeeWay("0");
				}
				collectionSettings.add(collectionSetting);
			}
			return collectionSettings;
		}
		return null;
	}
}
