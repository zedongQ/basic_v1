package org.ieforex.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bcics.sso.SSOHelper;
import org.bcics.sso.SSOToken;
import org.bcics.sso.Token;
import org.bcics.sso.annotation.Permission;
import org.ieforex.entity.CustomerAttendance;
import org.ieforex.entity.Dealer;
import org.ieforex.entity.ECustomer;
import org.ieforex.entity.ECustomerAccountInfo;
import org.ieforex.entity.ECustomerAddress;
import org.ieforex.entity.ECustomerLimit;
import org.ieforex.entity.ETradeAcct;
import org.ieforex.entity.ETradeAcctTask;
import org.ieforex.entity.HomePage;
import org.ieforex.entity.IntegralFlowing;
import org.ieforex.entity.WAllCustomerFundFlowing;
import org.ieforex.entity.WCustomerMessage;
import org.ieforex.entity.WSearchParms;
import org.ieforex.enums.Currency;
import org.ieforex.enums.GetIntegralType;
import org.ieforex.enums.IncomeType;
import org.ieforex.enums.OutIntegralType;
import org.ieforex.enums.WithDrawlStatusType;
import org.ieforex.service.CustomerAttendanceService;
import org.ieforex.service.DealerService;
import org.ieforex.service.ECustomerAccountBasicService;
import org.ieforex.service.ECustomerAccountInfoService;
import org.ieforex.service.ECustomerAddressService;
import org.ieforex.service.ECustomerLimitService;
import org.ieforex.service.ECustomerService;
import org.ieforex.service.ETradeAcctService;
import org.ieforex.service.ETradeAcctTaskService;
import org.ieforex.service.WAllCustomerFundFlowingService;
import org.ieforex.service.WCustomerMessageService;
import org.ieforex.service.WIntegralFlowingService;
import org.ieforex.utils.AesCryptUtil;
import org.ieforex.utils.CustomerUtil;
import org.ieforex.utils.DateUtil;
import org.ieforex.utils.MathUtil;
import org.ieforex.utils.RESULT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("customer")
public class CustomerController extends AbstractController{
	
	@Autowired
	protected HttpServletResponse response;
	@Autowired
	private CustomerAttendanceService customerAttendanceService;
	@Autowired
	private ECustomerAccountBasicService customerAccountBasicService;
	@Autowired
	private ECustomerService customerService;
	@Autowired
	private ETradeAcctService tradeAcctService;
	@Autowired
	private ETradeAcctTaskService tradeAcctTask;
	@Autowired
	private DealerService dealerService;
	@Autowired
	private ECustomerLimitService customerLimitService;
	@Autowired
	private ECustomerAccountInfoService customerAccountInfoService;
	@Autowired
	private WAllCustomerFundFlowingService allCustomerFundFlowingService;
	@Autowired
	private WIntegralFlowingService integralFlowingServiceService;
	@Autowired
	private ECustomerAddressService customerAddressService;
	@Autowired
	private WCustomerMessageService customerMessageService;
	
	//进入首页
	@Permission("usr:logined")
	@ResponseBody
	@RequestMapping(value={"home"}, method=RequestMethod.GET)
	public RESULT home(Model model) {
		Token token = SSOHelper.getToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			//用户基本信息
			ECustomer customer = customerService.queryUserById(customerId);
			//签到数据
			CustomerAttendance info = getAttendanceInfo();
			//邀请数据
			Map<String, Object> inviteMap = customerService.inviteAllCounts(customerId);
			//未读消息
			int msgUnReadNum = customerService.msgUnReadNum(customerId);
			//基本信息
			//Map<String, Object> basicMap = customerAccountBasicService.getBacicIdbyCustomerId(customerId);
			//广告
			List<HomePage> adviteListThree = customerService.adviteList("10");
			List<HomePage> adviteListTwo = customerService.adviteList("11");
			//邀请奖励排行榜
			List<ECustomer> inviteAmountList =  customerService.queryInviteWithAmount();
			for (ECustomer eCustomer : inviteAmountList) {
				CustomerUtil.getCustomer(eCustomer);
			}
			ECustomer eCustomer = CustomerUtil.getCustomerForPage(CustomerUtil.getCustomer(customer));
			//model.addAttribute("eCustomer", CustomerUtil.getCustomer(customer));//客户所有信息
			//model.addAttribute("hactive", true);
			return RESULT.ok()
					.put("eCustomer", eCustomer)//用户信息
					.put("invitePaiHang", inviteAmountList)//邀请排行榜
					.put("msgUnReadNum",msgUnReadNum)//未读系统消息数
					.put("inviteCounts", inviteMap)//邀请人数信息
					//.put("acctInfoBasic",basicMap)//基本信息
					.put("attendanceInfo",info)//签到信息;
					.put("adviteListThree", adviteListThree)//底部广告
					.put("adviteListTwo", adviteListTwo);//顶部公告
		}
		return RESULT.error("此账号不存在");
	}
	
	
	/*
	 * 个人中心
	 */
	@ResponseBody
	@Permission("usr:logined")
	@RequestMapping(value="userInfo", method=RequestMethod.GET)
	public RESULT userInfo(Model model) {
		Token token = SSOHelper.getToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			//用户基本信息
			ECustomer customer = customerService.queryUserById(customerId);
			ECustomer eCustomer = CustomerUtil.getCustomerForPage(CustomerUtil.getCustomer(customer));
			return RESULT.ok()
					.put("eCustomer", eCustomer);//用户信息
		}
		return null;
	}
	
	
	/*
	 * 个人中心-资产明细
	 */
	@ResponseBody
	@Permission("usr:logined")
	@RequestMapping(value="money", method=RequestMethod.GET)
	public RESULT userMoneys(Model model) {
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			//用户基本信息
			ECustomer customer = customerService.queryUserById(customerId);
			//签到数据
			CustomerAttendance info = getAttendanceInfo();
			//Map<String, Object> basicMap = customerAccountBasicService.getBacicIdbyCustomerId(customerId);
			List<Dealer> allDealer = dealerService.queryEdealer();
			IncomeType[] IncomeTypes = IncomeType.values();
			
			//未读消息
			int msgUnReadNum = customerService.msgUnReadNum(customerId);
			
			ECustomer eCustomer = CustomerUtil.getCustomerForPage(CustomerUtil.getCustomer(customer));
			/*
			model.addAttribute("acctInfoBasic",basicMap);*/
			//model.addAttribute("mactive", true);
			return RESULT.ok()
					.put("msgUnReadNum",msgUnReadNum)
					.put("dealers", allDealer)
					.put("incomeTypes", IncomeTypes)
					.put("attendanceInfo",info)
					.put("eCustomer", eCustomer);//用户月信息
		}
		return RESULT.error();
	}
	/*
	 * 个人中心-资产明细-提现
	 */
	@ResponseBody
	@Permission("usr:logined")
	@RequestMapping(value="money/withdrawl", method=RequestMethod.GET)
	public RESULT userMoneyWithdrawl(Model model) {
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			//用户基本信息
			ECustomer customer = customerService.queryUserById(customerId);
			//签到数据
			CustomerAttendance info = getAttendanceInfo();
			//Map<String, Object> basicMap = customerAccountBasicService.getBacicIdbyCustomerId(customerId);
			Currency[] currency = Currency.values();
			WithDrawlStatusType[] statusType = WithDrawlStatusType.values();
			//未读消息
			int msgUnReadNum = customerService.msgUnReadNum(customerId);
			
			ECustomer eCustomer = CustomerUtil.getCustomerForPage(CustomerUtil.getCustomer(customer));
			//model.addAttribute("acctInfoBasic",basicMap);
			//model.addAttribute("mactive", true);
			return RESULT.ok()
					.put("msgUnReadNum",msgUnReadNum)
					.put("attendanceInfo",info)
					.put("eCustomer", eCustomer)
					.put("currencys", currency)
					.put("statusTypes", statusType);
		}
		return RESULT.error();
	}
	/*
	 * 个人中心-资产明细-返现
	 */
	@ResponseBody
	@Permission("usr:logined")
	@RequestMapping(value="money/rebate", method=RequestMethod.GET)
	public RESULT userMoneyRebate(Model model) {
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			//用户基本信息
			ECustomer customer = customerService.queryUserById(customerId);
			//签到数据
			CustomerAttendance info = getAttendanceInfo();
			//Map<String, Object> basicMap = customerAccountBasicService.getBacicIdbyCustomerId(customerId);
			List<Dealer> allDealer = dealerService.queryEdealer();
			//RebateStatusType[] statusType = RebateStatusType.values();
			//未读消息
			int msgUnReadNum = customerService.msgUnReadNum(customerId);
			ECustomer eCustomer = CustomerUtil.getCustomerForPage(CustomerUtil.getCustomer(customer));
			//model.addAttribute("acctInfoBasic",basicMap);
			//model.addAttribute("statusTypes", statusType);
			//model.addAttribute("mactive", true);
			return RESULT.ok()
					.put("msgUnReadNum",msgUnReadNum)
					.put("dealers", allDealer)
					.put("attendanceInfo",info)
					.put("eCustomer",eCustomer)
					;
		}
		return RESULT.error();
	}
	
	/*
	 * 个人中心-加载资产明细
	 */
	@ResponseBody
	@Permission("usr:logined")
	@RequestMapping(value="money/flowing", method=RequestMethod.POST)
	public RESULT loadMoney(@RequestBody WSearchParms searchParms) {
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, Object> map2 = new HashMap<String, Object>();
			List<WAllCustomerFundFlowing> allCustomerFundFlowings = new ArrayList<WAllCustomerFundFlowing>();
			int total;
			map.put("customerId", customerId);
			map2.put("starttime", searchParms.getStarttime());
	        map2.put("endtime", searchParms.getEndtime());
	        map2.put("offset", searchParms.getOffset());
	        map2.put("limit", searchParms.getLimit());
	        String 	status = "";
	        String 	statusSimple = "";
	        String  tradeType = "";
	        String  tradeTypeSimple = "";
	        if("money".equals(searchParms.getMoneyType())){//收入明细流水
	        	if("".equals(searchParms.getTradeType())||searchParms.getTradeType()==null){
		        	IncomeType[] statusType = IncomeType.values();
		        	for(int i = 0; i<statusType.length;i++){
			        	if((i+1)<statusType.length){
			        		tradeType+=statusType[i].value()+",";
			        	}else if((i+1)==statusType.length){
			        		tradeType+=statusType[i].value();
			        	}
			        }
		    		map.put("tradeType", tradeType);
	        	}else{
	        		tradeTypeSimple = searchParms.getTradeType();
	        		map.put("tradeTypeSimple", tradeTypeSimple);
	        	}
	        	map.put("dealerId", searchParms.getDealerId());
	        	map2.put("searchCondition", map);
				allCustomerFundFlowings = allCustomerFundFlowingService.queryList(map2);
			}else if("rebate".equals(searchParms.getMoneyType())){//返佣明细流水
				if("".equals(searchParms.getTradeStatus())||searchParms.getTradeStatus()==null){
					/*RebateStatusType[] statusType = RebateStatusType.values();
					for(int i = 0; i<statusType.length;i++){
			        	if((i+1)<statusType.length){
			        		status+=statusType[i].value()+",";
			        	}else if((i+1)==statusType.length){
			        		status+=statusType[i].value();
			        	}
			        }*/
					map.put("status", "0");
				}else{
					statusSimple = searchParms.getTradeStatus();
					map.put("statusSimple", statusSimple);
				}
				map.put("dealerId", searchParms.getDealerId());
				map2.put("searchCondition", map);
				allCustomerFundFlowings = allCustomerFundFlowingService.getRebateById(map2);
			}else if("withdrawl".equals(searchParms.getMoneyType())){//提现明细流水
				if("".equals(searchParms.getTradeStatus())||searchParms.getTradeStatus()==null){
					WithDrawlStatusType[] statusType = WithDrawlStatusType.values();
					for(int i = 0; i<statusType.length;i++){
			        	if((i+1)<statusType.length){
			        		status+=statusType[i].value()+",";
			        	}else if((i+1)==statusType.length){
			        		status+=statusType[i].value();
			        	}
			        }
					map.put("status", status);
				}else{
					statusSimple = searchParms.getTradeStatus();
					map.put("statusSimple", statusSimple);
				}
				map.put("currency", searchParms.getCurrency());
				map2.put("searchCondition", map);
				allCustomerFundFlowings = allCustomerFundFlowingService.getWithdrawalById(map2);
			}
	        map2.put("moneyType", searchParms.getMoneyType());
	        total = allCustomerFundFlowingService.queryTotal(map2);
			return RESULT.ok()
					     .put("cFlowings", allCustomerFundFlowings)
					     .put("total", total);
		}
		return RESULT.error();
	}
	
	/*
	 * 个人中心-积分明细
	 */
	@Permission("usr:logined")
	@RequestMapping(value="integral", method=RequestMethod.GET)
	public RESULT userIntegral(Model model) {
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			//用户基本信息
			ECustomer customer = customerService.queryUserById(customerId);
			//签到数据
			CustomerAttendance info = getAttendanceInfo();
			List<Dealer> allDealer = dealerService.queryEdealer();
			//未读消息
			int msgUnReadNum = customerService.msgUnReadNum(customerId);
			
			ECustomer eCustomer = CustomerUtil.getCustomerForPage(CustomerUtil.getCustomer(customer));
			
			//model.addAttribute("iactive", true);
			
			return RESULT.ok()
					.put("msgUnReadNum",msgUnReadNum)
					.put("dealers", allDealer)
					.put("attendanceInfo",info)
					.put("eCustomer", eCustomer)
					;
		}
		return null;
	}

	/*
	 * 个人中心-加载积分明细
	 */
	@ResponseBody
	@Permission("usr:logined")
	@RequestMapping(value="integral/flowing", method=RequestMethod.POST)
	public RESULT loadIntegral(@RequestBody WSearchParms searchParms) {
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());	
			Map<String, Object> map = new HashMap<String, Object>();
			List<Map<String, Object>> itgralTypes = new ArrayList<Map<String, Object>>();
			String type = "";
			map.put("customerId", customerId);
			map.put("starttime", searchParms.getStarttime());
			map.put("endtime", searchParms.getEndtime());
			map.put("dealerId", searchParms.getDealerId());
			map.put("offset", searchParms.getOffset());
			map.put("limit", searchParms.getLimit());
			
			if("get".equals(searchParms.getIgalType())){
				GetIntegralType[] igalType = GetIntegralType.values();
				for(int i = 0; i<igalType.length;i++){
					Map<String, Object> itgralType = new HashMap<String, Object>();
					itgralType.put("key", igalType[i].value());
					itgralType.put("value", igalType[i].describe());
					itgralTypes.add(itgralType);
		        	if((i+1)<igalType.length){
		        		type+=igalType[i].value()+",";
		        	}else if((i+1)==igalType.length){
		        		type+=igalType[i].value();
		        	}
		        }
				map.put("itglType", type);
			}else if("out".equals(searchParms.getIgalType())){
				OutIntegralType[] igalType = OutIntegralType.values();
				for(int i = 0; i<igalType.length;i++){
					Map<String, Object> itgralType = new HashMap<String, Object>();
					itgralType.put("key", igalType[i].value());
					itgralType.put("value", igalType[i].describe());
					itgralTypes.add(itgralType);
		        	if((i+1)<igalType.length){
		        		type+=igalType[i].value()+",";
		        	}else if((i+1)==igalType.length){
		        		type+=igalType[i].value();
		        	}
		        }
				map.put("itglType", type);
			}else{
				map.put("type", searchParms.getIgalType());
			}
			
			List<IntegralFlowing> integralFlowings = integralFlowingServiceService.selectAll(map);
			int total=integralFlowingServiceService.queryTotal(map);
			return RESULT.ok()
				     .put("inFlowings", integralFlowings)
				     .put("total", total)
				     .put("itgralTypes", itgralTypes);
		}
		return null;
					
	}
	
	/*
	 * 个人中心-加载收款账号
	 */
	@ResponseBody
	@Permission("usr:logined")
	@RequestMapping(value="receipt/accounts", method=RequestMethod.GET)
	public List<ECustomerAccountInfo> loadReceiptAccounts(Model model) {
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, Object> basicMap = customerAccountBasicService.getBacicIdbyCustomerId(customerId);
			map.put("customerId", customerId);
			if(null!= basicMap.get("basicId")){
				map.put("basicId",basicMap.get("basicId"));
			}
			List<ECustomerAccountInfo> customerAccountInfos = customerAccountInfoService.getBycustomerId(map);
			
			return customerAccountInfos;
		}
		return null;
	}
	
	/*
	 * 个人中心-我的交易账号
	 */
	@Permission("usr:logined")
	@ResponseBody
	@RequestMapping(value="trading", method=RequestMethod.GET)
	public RESULT userTradingAccountPage(Model model) {
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			//用户基本信息
			ECustomer customer = customerService.queryUserById(customerId);
			//签到数据
			CustomerAttendance info = getAttendanceInfo();
			List<Dealer> allDealer = dealerService.queryEdealer();
			//未读消息
			int msgUnReadNum = customerService.msgUnReadNum(customerId);
			ECustomer eCustomer = CustomerUtil.getCustomerForPage(CustomerUtil.getCustomer(customer));
			return RESULT.ok()
					.put("msgUnReadNum",msgUnReadNum)
					.put("attendanceInfo",info)
					.put("dealers",allDealer)
					.put("eCustomer", eCustomer)
					;
		}
		return RESULT.error();
	}
	
	/*
	 * 个人中心-加载交易账号
	 */
	@ResponseBody
	@Permission("usr:logined")
	@RequestMapping(value="trading/acctno", method=RequestMethod.GET)
	public List<ETradeAcctTask> loadAcctNos(Model model) {
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("customerId", customerId);
			map.put("status", "0");
			List<ETradeAcctTask> tradeAcctTasks = tradeAcctTask.getAcctByCustomerId(map);
			return tradeAcctTasks;
		}
		return null;
	}
	
	/*
	 * 个人中心-查询单个审核交易账号
	 */
	@ResponseBody
	@Permission("usr:logined")
	@RequestMapping(value="trading/faild/{taskId}", method=RequestMethod.GET)
	public ETradeAcctTask faildAcctNo(@PathVariable("taskId") Long taskId) {
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			ETradeAcctTask tradeAcct = tradeAcctTask.getByTaskId(taskId);
			return tradeAcct;
		}
		return null;
	}
	
	/*
	 * 个人中心-解绑交易账号
	 */
	@ResponseBody
	@Permission("usr:logined")
	@RequestMapping(value="trading/unbind/{tradeAcctId}", method=RequestMethod.GET)
	public RESULT getByTaskId(@PathVariable("tradeAcctId") Long tradeAcctId) {
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("customerId", customerId);
			map.put("tradeAcctId", tradeAcctId);
			tradeAcctService.cancelBindAcctNo(map);
			return RESULT.ok();
		}
		return RESULT.error();
	}
	
	/*
	 * 新增审核交易账号
	 */
	@ResponseBody
	@Permission("usr:logined")
	@RequestMapping(value="addTradeAcc/task", method=RequestMethod.POST)
	public RESULT updateTradeAcc(@RequestBody String params) {
		try {
			SSOToken token = SSOHelper.attrToken(request);
			if(token!=null){
				//获取数据
				Map<String, String> paramMap = AesCryptUtil.decrypt2JSON(params);
				String dealerId = MathUtil.parseStr(paramMap.get("dealerId")).replaceAll(" ","");
				String acctNo = MathUtil.parseStr(paramMap.get("acctNo")).replaceAll(" ","");
				String holdName = MathUtil.parseStr(paramMap.get("holdName")).replaceAll(" ","");
				String holderEmail = MathUtil.parseStr(paramMap.get("holderEmail")).replaceAll(" ","");
				String tradeAcctTaskId = MathUtil.parseStr(paramMap.get("tradeAcctTaskId")).replaceAll(" ","");
				//账号是否被绑定
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("customerId", MathUtil.parseLong(token.getUid()));
				map.put("acctNo", acctNo);
				map.put("dealerId", dealerId);
				List<ETradeAcct> tradeAccts = tradeAcctService.isExistAcctNo(map);
				if(tradeAccts !=null && tradeAccts.size()>0) {
					return RESULT.error("该账号已存在，请勿重复提交，如果您没有绑定过该账号，请联系客服");
				}
				//账号未绑定的情况下提交审核
				ETradeAcctTask tradeAcc=new ETradeAcctTask();
				tradeAcc.setTradeAcctTaskId(MathUtil.parseLong(tradeAcctTaskId));
				tradeAcc.setDealerId(MathUtil.parseLong(dealerId));
				tradeAcc.setAcctNo(acctNo);
				tradeAcc.setHolderEmail(holderEmail);
				tradeAcc.setHolderName(holdName);
				tradeAcc.setIsAgency("0");
				tradeAcc.setIsSameName("0");
				tradeAcc.setDataSource("0");
				tradeAcc.setSubmitDate(new Date());
				tradeAcc.setAcctCreateDate(new Date());
				tradeAcc.setCustomerId(MathUtil.parseLong(token.getUid()));
				tradeAcc.setCheckStatus("0");
				//新增
				tradeAcctTask.save(tradeAcc);
				return RESULT.ok(message("user.acctno.checking"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return RESULT.error(message("user.acctno.save.error"));
		}
		return RESULT.error(message("user.acctno.save.error"));
	}
	
	
	//账户设置-账户安全
	@Permission("usr:logined")
	@RequestMapping(value={"setting"}, method=RequestMethod.GET)
	public RESULT customerSetting(Model model) {
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			//用户基本信息
			ECustomer customer = customerService.queryUserById(customerId);
			ECustomer eCustomer = CustomerUtil.getCustomerForPage(CustomerUtil.getCustomer(customer));
			//签到数据
			CustomerAttendance info = getAttendanceInfo();
			//未读消息
			int msgUnReadNum = customerService.msgUnReadNum(customerId);
			return RESULT.ok()
					.put("msgUnReadNum",msgUnReadNum)
					.put("attendanceInfo",info)
					.put("eCustomer", eCustomer)
					;
		}
		return null;
	}
	//账户设置-提现账号
	@Permission("usr:logined")
	@RequestMapping(value={"setting/receipt"}, method=RequestMethod.GET)
	public RESULT customerReceipt(Model model) {
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			//用户基本信息
			ECustomer customer = customerService.queryUserById(customerId);
			ECustomer eCustomer = CustomerUtil.getCustomerForPage(CustomerUtil.getCustomer(customer));
			//签到数据
			CustomerAttendance info = getAttendanceInfo();
			
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, Object> basicMap = customerAccountBasicService.getBacicIdbyCustomerId(customerId);
			map.put("customerId", customerId);
			if(null!=basicMap && null!= basicMap.get("basicId")){
				map.put("basicId",basicMap.get("basicId"));
			}
			List<ECustomerAccountInfo> customerAccountInfos = customerAccountInfoService.getBycustomerId(map);
			List<ECustomerAccountInfo> uinionPayAccountInfos = new ArrayList<ECustomerAccountInfo>();
			List<ECustomerAccountInfo> aliPayAccountInfos = new ArrayList<ECustomerAccountInfo>();
			List<ECustomerAccountInfo> dianHuiPayAccountInfos = new ArrayList<ECustomerAccountInfo>();
			List<ECustomerAccountInfo> skrillPayAccountInfos = new ArrayList<ECustomerAccountInfo>();
			for (ECustomerAccountInfo customerAccountInfo : customerAccountInfos) {
				if("0".equals(customerAccountInfo.getType())){
					uinionPayAccountInfos.add(customerAccountInfo);
				}else if("1".equals(customerAccountInfo.getType())){
					dianHuiPayAccountInfos.add(customerAccountInfo);
				}else if("2".equals(customerAccountInfo.getType())){
					aliPayAccountInfos.add(customerAccountInfo);
				}else if("3".equals(customerAccountInfo.getType())){
					skrillPayAccountInfos.add(customerAccountInfo);
				}
			}
			//未读消息
			int msgUnReadNum = customerService.msgUnReadNum(customerId);
			return RESULT.ok()
					.put("msgUnReadNum",msgUnReadNum)
					.put("attendanceInfo",info)
					.put("uinionPayAccountInfos",uinionPayAccountInfos)
					.put("skrillPayAccountInfos",skrillPayAccountInfos)
					.put("dianHuiPayAccountInfos",dianHuiPayAccountInfos)
					.put("eCustomer", eCustomer)
					;
		}
		return null;
	}
	
	//账户设置-收货地址
	@Permission("usr:logined")
	@RequestMapping(value={"setting/address"}, method=RequestMethod.GET)
	public RESULT customerAddress(Model model) {
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			//用户基本信息
			ECustomer customer = customerService.queryUserById(customerId);
			ECustomer eCustomer = CustomerUtil.getCustomerForPage(CustomerUtil.getCustomer(customer));
			//签到数据
			CustomerAttendance info = getAttendanceInfo();
			//地址信息
			List<ECustomerAddress> customerAddresses = customerAddressService.selectByCustomerId(customerId);
			//未读消息
			int msgUnReadNum = customerService.msgUnReadNum(customerId);
			return RESULT.ok()
					.put("msgUnReadNum",msgUnReadNum)
					.put("attendanceInfo",info)
					.put("eCustomer", eCustomer)
					.put("addresses", customerAddresses)
					;
		}
		return null;
	}
	
	//账户设置
	@Permission("usr:logined")
	@RequestMapping(value={"setting/basic"}, method=RequestMethod.GET)
	public RESULT customerBasic(Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			//用户基本信息
			ECustomer customer = customerService.queryUserById(customerId);
			ECustomer eCustomer = CustomerUtil.getCustomerForPage(CustomerUtil.getCustomer(customer));
			//签到数据
			CustomerAttendance info = getAttendanceInfo();
			//未读消息
			int msgUnReadNum = customerService.msgUnReadNum(customerId);
			return RESULT.ok()
					.put("msgUnReadNum",msgUnReadNum)
					.put("attendanceInfo",info)
					.put("eCustomer", eCustomer)
					;
		}
		return null;
	}

	
	/**
	 * 判断用户是否被限制
	 */
	@ResponseBody
	@Permission("usr:logined")
	@RequestMapping(value="islimit", method=RequestMethod.POST)
	public RESULT userLimit(HttpServletRequest request) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		SSOToken token=SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			ECustomerLimit customerLimit = customerLimitService.searchById(customerId);
			if(customerLimit!=null){
				if("1".equals(customerLimit.getLimitType())
						&&(DateUtil.compareToDate(customerLimit.getLimitDate(),new Date())!=-1)){
					
					String limitDate = new SimpleDateFormat("yyyy-MM-dd").format(customerLimit.getLimitDate());
					String limitinfo = message("user.withdrawal.limit")+limitDate+"！";
					return RESULT.error(limitinfo);
				}
			}
		}
		return RESULT.ok();
	}
	
	
	//签到
	@ResponseBody
	@Permission("usr:logined")
	@RequestMapping(value={"attendance"}, method=RequestMethod.GET)
	public RESULT customerAttendance(){
		SSOToken token=SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			int toDay = MathUtil.getDay(new Date());
			CustomerAttendance info = customerAttendanceService.getInfo(customerId);
			if(null!=info.getAttendancdDate()&&DateUtil.isToday(info.getAttendancdDate().getTime())){
				return RESULT.error("今日已签到");
			}
			ECustomer customer =new ECustomer();//通过Id查询对象
			customer.setCustomerId(customerId);
			String bonuses;
			int i = customerAttendanceService.updateAttendance(CustomerUtil.getAttendanceDay(toDay,customerId),customer);
			if(i==7){
				//抽奖次数加 +1
				bonuses = "draw";
			}else if(i==0){
				return RESULT.error("签到失败，请稍后再试！");
			}else{
				//积分增加+
				bonuses = i+"";
			}
			return RESULT.ok("签到成功！")
				     	 .put("bonuses", bonuses);
		}
		return RESULT.error("签到失败，请稍后再试！");
	}
	
	//签到数据回显
	public CustomerAttendance getAttendanceInfo(){
		response.setHeader("Access-Control-Allow-Origin", "*");
		SSOToken token=SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			CustomerAttendance info = customerAttendanceService.getInfo(customerId);
			if(info.getAttendancdDate()!=null){
				if((!DateUtil.isThisWeek(info.getAttendancdDate().getTime())&&!DateUtil.isSunday(new Date().getTime()))){
					customerAttendanceService.emptyAllDay(customerId);
					info.setMonday("");
					info.setTuesday("");
					info.setWednesday("");
					info.setThursday("");
					info.setFriday("");
					info.setSaturday("");
					info.setSunday("");
				}else if(DateUtil.isSunday(info.getAttendancdDate().getTime())&&DateUtil.isMonday(new Date().getTime())){
					customerAttendanceService.emptyAllDay(customerId);
					info.setMonday("");
					info.setTuesday("");
					info.setWednesday("");
					info.setThursday("");
					info.setFriday("");
					info.setSaturday("");
					info.setSunday("");
				}
			}
			return info;
		}
		return null;
	}
	//修改头像/或昵称
	@ResponseBody
	@Permission("usr:logined")
	@RequestMapping(value={"basic/info"}, method=RequestMethod.GET)
	public RESULT updateBasic(){
		response.setHeader("Access-Control-Allow-Origin", "*");
		SSOToken token=SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			String name = "";
			String headImg = "";
			ECustomer customer = new ECustomer();
			customer.setCustomerId(customerId);
			if(null!=request.getParameter("headImg") && null==request.getParameter("name")){
				headImg = request.getParameter("headImg");
				String reg ="^[resources]{9}/[images]{6}/[touxiang]{8}/[1-9][0-2]{0,1}.[jpg]{3}$";
				boolean matches = Pattern.matches(reg, headImg);
				if(!matches){
					return RESULT.error("上传头像不合法");
				}
				customer.setHeadImg(headImg);
			
			}else if(null!=request.getParameter("name") && null==request.getParameter("headImg")){
				//try {
					name = request.getParameter("name");
					//name = new String(name.getBytes("ISO-8859-1"),"UTF-8");
					customer.setTradeName(name);
				/*} catch (UnsupportedEncodingException e) {
					return RESULT.error();
				}*/
			}
			if(null!=request.getParameter("headImg") || "".equals(request.getParameter("headImg")) 
					|| null!=name || "".equals(name)){
				customerService.updateBasic(customer);
				return RESULT.ok();
			}
		}
		return null;
	}
	
	//系统消息页面
	@Permission("usr:logined")
	@RequestMapping(value={"sysmsg"}, method=RequestMethod.GET)
	public RESULT customerSysMsg(Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			//用户基本信息
			ECustomer customer = customerService.queryUserById(customerId);
			ECustomer eCustomer = CustomerUtil.getCustomerForPage(CustomerUtil.getCustomer(customer));
			//签到数据
			CustomerAttendance info = getAttendanceInfo();
			//未读消息
			int msgUnReadNum = customerService.msgUnReadNum(customerId);
			return RESULT.ok()
					.put("msgUnReadNum",msgUnReadNum)
					.put("attendanceInfo",info)
					.put("eCustomer", eCustomer)
					;
		}
		return null;
	}
	//加载系统消息
	@ResponseBody
	@Permission("usr:logined")
	@RequestMapping(value={"loadsysmsg"}, method=RequestMethod.POST)
	public RESULT customerSysMsg(@RequestBody WSearchParms searchParms) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("customerId", customerId);
			map.put("offset", searchParms.getOffset());
			map.put("limit", searchParms.getLimit());
			List<WCustomerMessage> all = customerMessageService.queryList(map);
			int msgCounts = customerMessageService.queryTotal(customerId);
			return RESULT.ok()
						 .put("messages", all)
						 .put("msgCounts", msgCounts);
		}
		return null;
	}
	
		//删除系统消息
		@ResponseBody
		@Permission("usr:logined")
		@RequestMapping(value={"deleteMsgs"}, method=RequestMethod.POST)
		public RESULT deleteSomeMsg(@RequestBody Long[] msgIds) {
			response.setHeader("Access-Control-Allow-Origin", "*");
			Token token = SSOHelper.attrToken(request);
			if(token!=null){
				customerMessageService.deleteById(msgIds,MathUtil.parseLong(token.getUid()));
				return RESULT.ok();
			}
			return null;
		}
		
		//系统消息标记已读
		@ResponseBody
		@Permission("usr:logined")
		@RequestMapping(value={"changeRead/{messageId}"}, method=RequestMethod.GET)
		public RESULT changeRead(@PathVariable("messageId") Long messageId) {
			response.setHeader("Access-Control-Allow-Origin", "*");
			Token token = SSOHelper.attrToken(request);
			if(token!=null){
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("messageId", messageId);
				map.put("customerId", MathUtil.parseLong(token.getUid()));
				customerMessageService.changRead(map);
				return RESULT.ok();
			}
			return null;
		}
		
}

