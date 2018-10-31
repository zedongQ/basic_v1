package org.ieforex.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.bcics.sso.common.encrypt.MD5;
import org.ieforex.entity.ECustomer;
import org.ieforex.service.ECustomerService;
import org.ieforex.utils.CookieUtil;
import org.ieforex.utils.EmailValidateUtil;
import org.ieforex.utils.MathUtil;
import org.ieforex.utils.RESULT;
import org.ieforex.utils.sms.SmsSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("externalLogin")
public class ExternalLoginController extends AbstractController {
	@Autowired
	private ECustomerService customerService;

	private final static String DECRYPT_KEY="1ie9coo0kie8cod9";
	//短信验证码
	@ResponseBody
	@RequestMapping(value="smsCode996996996", method=RequestMethod.POST) 
	public String smsCode(HttpServletResponse response,String loginPhone) {
		//response.setHeader("Access-Control-Allow-Origin", "*");
		try {
			// 手機號碼爲空
			if(null == loginPhone || "".equals(loginPhone.trim())) {
				return RESULT.error("手机号码格式不正确").aes();
			}
			
			loginPhone = loginPhone.trim();
			//手机号號碼已注冊
			int nameFalg = customerService.repeatPhone(loginPhone);
			if(nameFalg == 0){
				return RESULT.error("手机号码未注册").aes();
			}
			
			int radomInt = (int) ((Math.random()*9+1)*100000);
/*			String code = AesCryptUtil.encrypt(radomInt+"",DECRYPT_KEY, DECRYPT_KEY);
			String cookeiCode=loginPhone+"||"+code;*/
			//短信发送
			String smsSendCode = SmsSend.smsSend(loginPhone, radomInt + "");
			if(!"".equals(smsSendCode) && "Success".equals(smsSendCode)){
				//CookieUtil.addCookie("external_login_code", cookeiCode, null, 300, response);
				request.getSession().setAttribute("external_login_code", radomInt);
				return RESULT.ok("验证码发送成功").aes();
			} else {
				return RESULT.error("验证码发送失败").aes();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return RESULT.error("验证码发送失败").aes();
		}
	}
	
	/**
	 * 登录验证
	 */
	@ResponseBody
	@RequestMapping(value="validation", method=RequestMethod.POST)
	public String login(HttpServletResponse response,String username, String password,String loginType,String loginPhone,String smsCode) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {
			//获取用户密码
			String type = MathUtil.isNull(loginType);
			ECustomer customer=null;
			//短信登录
			if("0".equals(type)){
				loginPhone=MathUtil.isNull(loginPhone);
				smsCode=MathUtil.isNull(smsCode);
				String cookieCode = CookieUtil.getCookie(request, "external_login_code");
				if(null==cookieCode){
					return RESULT.error("验证码不正确").aes();
				}
				String cookie=cookieCode.split("\\|\\|")[1];
				String cookiePhone=cookieCode.split("\\|\\|")[0];
				Boolean codeValidate=EmailValidateUtil.smsValidate(cookie, smsCode, DECRYPT_KEY);
				if(!cookiePhone.equals(loginPhone)){
					return RESULT.error(message("user.textcode.error")).aes();
				}
				//String codeValidate = request.getSession().getAttribute("external_login_code").toString();
				if(!codeValidate){
					return RESULT.error(message("user.textcode.error")).aes();
				}else{
					customer=customerService.queryUser(loginPhone);
					if(customer==null){
						ECustomer newCustomer=new ECustomer();
						newCustomer.setLoginPhone(loginPhone);
						newCustomer.setCreateDate(new Date());
						newCustomer.setStatus("0");
						String tradeName=loginPhone.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
						newCustomer.setTradeName(tradeName);
						customerService.insert(newCustomer, false);
						CookieUtil.addCookie("sms_code", null, null, 0, response);
						customer=customerService.queryUser(loginPhone);
						return RESULT.error(message("user.noexists.error")).aes();
					}
				}
			}else if("1".equals(type)){//帐号密码登录
				username =  MathUtil.isNull(username);
				//sha256加密
				password = MD5.toMD5(password);
				//
				customer=customerService.queryUser(username);
				if (customer!=null) {
					if("3".equals(customer.getStatus())){
						return RESULT.error("帐号不存在").aes();
					}else
					if("2".equals(customer.getStatus())){
						return RESULT.error(message("user.active.need")).aes();
					}else
					if("1".equals(customer.getStatus())){
						//半小时解除锁定
						Date date = new Date();//获取当前时间    
						Calendar calendar = Calendar.getInstance();    
						calendar.setTime(date);
						calendar.add(Calendar.MINUTE,-30);
						Date nowDate = calendar.getTime();
						if(customer.getLatestLockTime().getTime()<nowDate.getTime()){
							Map<String, Object> map=new HashMap<String, Object>();
							map.put("customerId", customer.getCustomerId());
							map.put("loginCount", 0);
							map.put("status", "0");
							customerService.updateLoginCount(map);
						}else{
							return RESULT.error(message("user.locked")).aes();
						}
					}else
					if("0".equals(customer.getStatus())){
						if(!password.equals(customer.getPwd())){
							String messages=message("user.login.failed");
							Map<String, Object> map=new HashMap<String, Object>();
							map.put("customerId", customer.getCustomerId());
							map.put("loginCount", customer.getLoginCount()+1);
							//错误五次密码帐号锁定
							if(customer.getLoginCount()+1>5){
								map.put("status", "1");
								map.put("latestLockTime", new Date());
								messages=message("user.unlock");
							}
							customerService.updateLoginCount(map);
							return RESULT.error(messages).aes();
						}
					}else{
						return RESULT.error("帐号不存在").aes();
					}
				}else{
					return RESULT.error("帐号不存在").aes();
				}
			}else{
				return RESULT.error("用户名或密码错误").aes();
			}
			return RESULT.ok()
					.put("customerId", customer.getCustomerId())
					.put("tradeName", customer.getTradeName())
					.aes(); 
		}finally {
			
		}
		
	}
}

