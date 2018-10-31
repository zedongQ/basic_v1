package org.ieforex.controller;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.ieforex.entity.ECustomer;
import org.ieforex.entity.ExternalRegister;
import org.ieforex.service.ECustomerService;
import org.ieforex.service.ExternalRegisterService;
import org.ieforex.utils.sms.SmsSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("externalRegister")
public class ExternalRegisterController extends AbstractController {
	@Autowired
	private ECustomerService customerService;
	@Autowired
	private ExternalRegisterService externalRegisterService;

	//短信验证码
	@ResponseBody
	@RequestMapping(value="smsCode", method=RequestMethod.GET) 
	public String smsCode(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {
			String registerPhone = request.getParameter("phoneNo");
			// 手機號碼爲空
			if(null == registerPhone || "".equals(registerPhone.trim())) {
				return "0001";
			}
			
			registerPhone = registerPhone.trim();
			//手机号號碼已注冊
			int nameFalg = customerService.repeatPhone(registerPhone);
			if(nameFalg > 0){
				return "0010";
			}
			
			int radomInt = (int) ((Math.random()*9+1)*100000);
			ExternalRegister externalRegister = externalRegisterService.getUserByPhone(registerPhone);
			if(null == externalRegister) {
				ExternalRegister insertER = new ExternalRegister();
				insertER.setRegisterPhone(registerPhone);
				insertER.setIdentifyingCode(radomInt + "");
				insertER.setCreateDate(new Date());
				externalRegisterService.insert(insertER);
			} else {
				ExternalRegister updateER = new ExternalRegister();
				updateER.setRegisterId(externalRegister.getRegisterId());
				updateER.setRegisterPhone(registerPhone);
				updateER.setIdentifyingCode(radomInt + "");
				updateER.setUpdateDate(new Date());
				externalRegisterService.update(updateER);
			}
			
			//短信发送
			String smsSendCode = SmsSend.smsSend(registerPhone, radomInt + "");
			if(!"".equals(smsSendCode) && "Success".equals(smsSendCode)){
				return "9999";
			} else {
				return "0011";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "0011";
		}
	}
	
	/**
	 * 注册帐号
	 */
	@ResponseBody
	@RequestMapping(value="regist", method=RequestMethod.GET)
	public String registAccount(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String registerPhone = request.getParameter("phoneNo");
		String code = request.getParameter("smsCode");
		String channelSource = request.getParameter("channelSource");
		// 手機號碼或驗證碼爲空
		if(null == registerPhone || "".equals(registerPhone.trim())) {
			return "0001";
		}
		// 驗證碼爲空
		if(null == code || "".equals(code.trim())) {
			return "0002";
		}
		
		registerPhone = registerPhone.trim();
		ExternalRegister externalRegister = externalRegisterService.getUserByPhone(registerPhone);
		// 無效驗證碼
		if(null == externalRegister || !code.equals(externalRegister.getIdentifyingCode())) {
			return "0010";
		}

		// 新增用戶信息
		ECustomer customer = new ECustomer();
		customer.setLoginPhone(registerPhone);
		customer.setStatus("0");
		if(null == channelSource || channelSource.length() == 0 ||channelSource.length() > 32) {
			customer.setUserType("0000");
		} else {
			customer.setUserType(channelSource);
		}
		customer.setLoginDate(new Date());
		String tradeName = registerPhone.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
		customer.setTradeName(tradeName);
		customer.setCreatedate(new Date());
		customerService.insert(customer, true);
		return "9999";
	}
}

