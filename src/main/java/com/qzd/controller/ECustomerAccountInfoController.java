package org.ieforex.controller;

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
import org.ieforex.entity.ECustomer;
import org.ieforex.entity.ECustomerAccountInfo;
import org.ieforex.enums.BanksHelp;
import org.ieforex.service.ECustomerAccountBasicService;
import org.ieforex.service.ECustomerAccountInfoService;
import org.ieforex.service.ECustomerService;
import org.ieforex.utils.AesCryptUtil;
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
 * 客户收款帐号表
 * 
 * @author brilliance
 * @email
 * @date 2017-05-10 13:33:08
 */
@Controller
@RequestMapping("ecustomeraccountinfo")
public class ECustomerAccountInfoController extends AbstractController {
	
	@Autowired
	protected HttpServletResponse response;

	@Autowired
	private ECustomerAccountInfoService customerAccountInfoService;

	@Autowired
	private ECustomerAccountBasicService customerAccountBasicService;

	@Autowired
	private ECustomerService customerService;

	/**
	 * 保存
	 */
	@ResponseBody
	@Permission("usr:logined")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@RequestBody String params, HttpServletRequest request) {
		if (params != null && !"".equals(params)) {
			Token token = SSOHelper.getToken(request);
			ECustomer customer = new ECustomer();
			Map<String, String> paramMap;
			if (token != null) {
				customer = customerService.queryUserById(MathUtil
						.parseLong(token.getUid()));
				try {
					paramMap = AesCryptUtil.decrypt2JSON(params);
					String accountAcct = paramMap.get("accountAcct");
					String accountAcctView = "";
					if (accountAcct.contains("@")) {
						accountAcctView = accountAcct
								.replaceAll(
										"(\\w?)(\\w+)(\\w)(@\\w+\\.[a-z]+(\\.[a-z]+)?)",
										"$1***$3$4");
					} else if (accountAcct.length() == 19) {
						accountAcctView = accountAcct.replaceAll(
								"(\\d{3})\\d+(\\d{4})", "$1***********$2");
					} else if (accountAcct.length() == 16) {
						accountAcctView = accountAcct.replaceAll(
								"(\\d{3})\\d+(\\d{4})", "$1*********$2");
					} else if (accountAcct.length() == 11) {
						accountAcctView = accountAcct.replaceAll(
								"(\\d{3})\\d+(\\d{4})", "$1****$2");
					} else {
						accountAcctView = accountAcct.replaceAll(
								"(\\d{3})\\d+(\\d{4})", "$1******$2");
					}
					// String reAccountAcct = paramMap.get("reAccountAcct");
					/*
					 * if(!accountAcct.equals(reAccountAcct)){ return
					 * RESULT.error(message("account.compare")).aes(); }
					 */
					String accountName = paramMap.get("accountName");
					String engName = paramMap.get("firstName");
					String engsurName = paramMap.get("lastName");
					String openingBank = paramMap.get("openingBank");
					String idDefault = paramMap.get("isDefault");
					String bankAddress = paramMap.get("bankAddress");
					String bankSwiftCode = paramMap.get("swifiCode");
					String payAddress = paramMap.get("payeeAddress");
					String nationality = paramMap.get("nationality");
					String accountPhone = paramMap.get("accountPhone");
					String type = paramMap.get("type");
					String isDefault = paramMap.get("isDefault");
					ECustomerAccountInfo customerAccountInfo = new ECustomerAccountInfo();
					customerAccountInfo.setAcctName(customer.getUserName());
					customerAccountInfo.setCardNo(customer.getUserCardNo());
					customerAccountInfo.setEngName(engName);
					customerAccountInfo.setEngSurname(engsurName);
					customerAccountInfo.setAccountAcct(accountAcct);
					customerAccountInfo.setAccountAcctView(accountAcctView);
					customerAccountInfo.setAccountName(accountName);
					customerAccountInfo.setOpeningBank(openingBank);
					customerAccountInfo.setBankAddress(bankAddress);
					customerAccountInfo.setBankSwiftCode(bankSwiftCode);
					customerAccountInfo.setCustomerId(customer.getCustomerId());
					customerAccountInfo.setIsDefault(isDefault);
					customerAccountInfo.setAccountPhone(accountPhone);
					customerAccountInfo.setNationality(nationality);
					customerAccountInfo.setPayeeAddress(payAddress);
					customerAccountInfo.setType(type);
					customerAccountInfo.setIsDefault(idDefault);
					customerAccountInfo.setDataSource("0");
					customerAccountInfo.setDataStatus("0");
					customerAccountInfo.setCreateUserId(MathUtil
							.parseLong(token.getUid()));
					int x = customerAccountInfoService.saveAccountInfor(customerAccountInfo);
					if (x == 0) {
						return RESULT.error(message("user.account.exist"))
								.aes();
					}
				} catch (Exception e) {
					e.printStackTrace();
					return RESULT.error(message("account.save.error")).aes();
				}
				return RESULT.ok(message("account.save.ok")).aes();
			}
		}
		return RESULT.error(message("account.save.error")).aes();
	}

	/**
	 * 查询收款账号
	 */
	@ResponseBody
	@Permission("usr:logined")
	@RequestMapping(value="getamounts/{type}",method=RequestMethod.GET)
	public List<Map<String,Object>> getAmounts(@PathVariable("type") String type,HttpServletRequest request) {
		Token token = SSOHelper.getToken(request);
		if (token != null) {
			String uid = token.getUid();
			Long customerId = Long.parseLong(uid);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("customerId", customerId);
			if(type.equals("1")){//人民币账号
				map.put("payType", "AND type in(0,2)");
			}else if(type.equals("2")){//美元账号
				map.put("payType", "AND type in(1,3)");
			}else {
				return null;
			}
			Map<String, Object> basicMap = customerAccountBasicService.getBacicIdbyCustomerId(customerId);
			if (basicMap != null && basicMap.size() > 0) {
				map.put("basicId", basicMap.get("basicId"));
			}
			List<ECustomerAccountInfo> accountInfos = customerAccountInfoService.getBycustomerId(map);
			List<Map<String,Object>> resMap = new ArrayList<Map<String,Object>>();
			for(int i=0;i<accountInfos.size();i++){
				Map<String,Object> rmap = new HashMap<String,Object>();
				rmap.put("bankPng", accountInfos.get(i).getBankPng());
				rmap.put("bankName", accountInfos.get(i).getBankName());
				rmap.put("typeName", accountInfos.get(i).getTypeName());
				rmap.put("acctName", accountInfos.get(i).getAcctNameView());
				rmap.put("accountAcct", accountInfos.get(i).getAccountAcctView());
				rmap.put("accountId", accountInfos.get(i).getAccountId());
				rmap.put("type", accountInfos.get(i).getType());
				resMap.add(rmap);
				
			}
			return resMap;
		}
		return null;
	}

	/**
	 * 设置默认收款账号
	 */
	@ResponseBody
	@Permission("usr:logined")
	@RequestMapping(value = "updatedefault/{accountId}", method = RequestMethod.GET)
	public String updateDefault(@PathVariable("accountId") Long accountId,
			HttpServletRequest request) {
		Token token = SSOHelper.getToken(request);
		if (token != null) {
			Long customerId = MathUtil.parseLong(token.getUid());
			if (accountId != null && accountId != 0) {
				customerAccountInfoService.cancelDefaultPay(customerId);
			}
			ECustomerAccountInfo searchAccount = customerAccountInfoService
					.searchById(accountId);
			ECustomerAccountInfo customerAccount = new ECustomerAccountInfo();
			customerAccount.setAccountId(accountId);
			customerAccount.setIsDefault("1");
			customerAccount.setCustomerId(customerId);
			customerAccount.setType(searchAccount.getType());
			int i = customerAccountInfoService.updateById(customerAccount);
			if (i == 0) {
				return RESULT.error(message("user.default.account.error"))
						.aes();
			}
			return RESULT.ok(message("user.default.account.ok")).aes();
		}
		return RESULT.error(message("user.default.account.error")).aes();
	}

	/**
	 * 解绑收款账号
	 */
	@ResponseBody
	@Permission("usr:logined")
	@RequestMapping(value = "cancelamount/{accountId}", method = RequestMethod.GET)
	public RESULT cancelBind(@PathVariable("accountId") Long accountId) {
		Token token = SSOHelper.attrToken(request);
		if (token != null) {
			ECustomerAccountInfo cai = new ECustomerAccountInfo();
			cai.setAccountId(accountId);
			cai.setUpdateDate(new Date());
			cai.setCustomerId(MathUtil.parseLong(token.getUid()));
			int i = customerAccountInfoService.cancelBind(cai);
			if (i == 0) {
				return RESULT.ok(message("account.unbind.error"));
			}
			return RESULT.ok(message("account.unbind.ok"));
		}
		return null;
	}

	/**
	 * 加载银行信息
	 */
	@ResponseBody
	@Permission("usr:logined")
	@RequestMapping(value = "getBanks", method = RequestMethod.GET)
	public Map<String, Object> loadBanks() {
		Map<String, Object> map = new HashMap<String, Object>();
		BanksHelp[] banks = BanksHelp.values();
		for (BanksHelp bank : banks) {
			System.out.println();
			map.put(bank.value(), bank.toString());
		}
		return map;
	}
	
	/**
	 * 初始化银行添加页面
	 */
	@ResponseBody
	@Permission("usr:logined")
	@RequestMapping(value = "initData", method = RequestMethod.GET)
	public RESULT initData() {
		Token token = SSOHelper.attrToken(request);
		if (token != null) {
			//用户基本信息
			Long customerId = MathUtil.parseLong(token.getUid());
			ECustomer customer = customerService.queryUserById(customerId);
			if(null!=customer.getUserName() && null!=customer.getUserCardNo()){//已经进行过实名认证
				//银行信息
				Map<String, Object> backmap = new HashMap<String, Object>();
				BanksHelp[] banks = BanksHelp.values();
				for (BanksHelp bank : banks) {
					backmap.put(bank.value(), bank.toString());
				}
				return RESULT.ok().put("backs", backmap)
				.put("userName", customer.getUserNameView());//用户真实姓名
			}else{
				return null;
			}
		}
		return null;
	}	
}
