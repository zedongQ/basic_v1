package org.ieforex.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bcics.sso.SSOConfig;
import org.bcics.sso.SSOHelper;
import org.bcics.sso.SSOToken;
import org.bcics.sso.Token;
import org.bcics.sso.annotation.Permission;
import org.bcics.sso.common.encrypt.MD5;
import org.ieforex.entity.CustomerAttendance;
import org.ieforex.entity.ECustomer;
import org.ieforex.service.CustomerAttendanceService;
import org.ieforex.service.DealerService;
import org.ieforex.service.ECustomerService;
import org.ieforex.service.WMessageTempletService;
import org.ieforex.utils.AesCryptUtil;
import org.ieforex.utils.CheckMobile;
import org.ieforex.utils.CookieUtil;
import org.ieforex.utils.CustomerUtil;
import org.ieforex.utils.DateUtil;
import org.ieforex.utils.EmailValidateUtil;
import org.ieforex.utils.Idcard;
import org.ieforex.utils.MathUtil;
import org.ieforex.utils.RESULT;
import org.ieforex.utils.RequestUtil;
import org.ieforex.utils.VerifyCodeUtils;
import org.ieforex.utils.charEcordingFilterUtil;
import org.ieforex.utils.sms.SmsSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tk.mybatis.mapper.util.StringUtil;


/**
 * 个人登录后相关页面及接口
 */
@Controller
@RequestMapping("user")
public class UserHomeController extends AbstractController {
	
	@Autowired
	protected HttpServletResponse response;
	@Autowired
	private ECustomerService customerService;
	@Autowired
	private WMessageTempletService messageTempletService;
	@Autowired
	private DealerService dealerService;
	@Autowired 
	private CustomerAttendanceService customerAttendanceService;
	//数据库模版键值
	private final static String CHANGE_EMAIL_TEMPLET_KEY = "Change_Email";
	private final static String SING_UP_TEMPLET_KEY = "Sing_Up_Active";
	private final static String FORGET_PWD_TEMPLET_KEY = "Forget_Pwd";
	private final static String DECRYPT_KEY="1ie9coo0kie8cod9";
	
	@RequestMapping(value="codecaptcha" ,method=RequestMethod.GET) 
	public void captcha(HttpServletResponse response)throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg"); 
        //生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        CookieUtil.addCookie("verify_code", verifyCode, null, 3600, response);
        //生成图片 
        int w = 120, h = 50;
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
	}
	
	//短信验证码
	@ResponseBody
	@RequestMapping(value="smsCode" ,method=RequestMethod.GET) 
	public RESULT smsCode(HttpServletResponse response) {
		try {
			String loginPhone=request.getParameter("phone");
			String type=request.getParameter("registType");
			if(!("0".equals(type))&&!("1".equals(type))&&(!"2".equals(type))){
				return RESULT.error("验证码获取失败");
			}
			//注册手机号验重
			if(null!=type&&"0".equals(type)){
				//手机号验重
				int nameFalg=customerService.repeatPhone(loginPhone);
				if(nameFalg>0){
					return RESULT.error(message("user.phone.error"));
				}
			}
			//忘记密码等验证号码是否存在
			if(null!=type&&"1".equals(type)){
				//手机号验重
				int nameFalg=customerService.repeatPhone(loginPhone);
				if(nameFalg<1){
					return RESULT.error("该帐号不存在");
				}
			}
			int radomInt=(int) ((Math.random()*9+1)*100000);
			String code = AesCryptUtil.encrypt(radomInt+"",DECRYPT_KEY, DECRYPT_KEY);
			String cookeiCode=loginPhone+"||"+code;
			//短信发送
			String smsSendCode = SmsSend.smsSend(loginPhone, radomInt+"");
			System.out.println(radomInt+"===="+code);
//			String smsSendCode="Success";
			if(!"".equals(smsSendCode)&&"Success".equals(smsSendCode)){
				CookieUtil.addCookie("sms_code", cookeiCode, null, 300, response);
				return RESULT.ok(message("user.sendsms.success"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RESULT.error();
	}
	
	@ResponseBody
	@RequestMapping(value="isLogin", method=RequestMethod.GET)
	public RESULT isLogin(){
		SSOToken token = SSOHelper.getToken(request);
		if(token!=null){
			int messageNum =customerService.msgUnReadNum(MathUtil.parseLong(token.getUid()));
			return RESULT.ok().put("messageNum",messageNum);
		}
		return RESULT.error();
	}
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login(){
		return "login";
	}
	
	/**
	 * 登录动作 （注解跳过权限验证）
	 */
	@ResponseBody
	@RequestMapping(value="loginSub", method=RequestMethod.POST)
	public RESULT loginSub(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			//获取用户密码
			Map<String, String> paramMap = AesCryptUtil.decrypt2JSON(params);
			String type = MathUtil.isNull(paramMap.get("loginType"));
			ECustomer customer=null;
			//短信登录
			if("0".equals(type)){
				String loginPhone=MathUtil.isNull(paramMap.get("loginPhone"));
				String smsCode=MathUtil.isNull(paramMap.get("smsCode"));
				String cookieCode = CookieUtil.getCookie(request, "sms_code");
				if(null==cookieCode){
					return RESULT.error("验证码不正确");
				}
				String cookie=cookieCode.split("\\|\\|")[1];
				String cookiePhone=cookieCode.split("\\|\\|")[0];
				Boolean codeValidate=EmailValidateUtil.smsValidate(cookie, smsCode, DECRYPT_KEY);
				if(!cookiePhone.equals(loginPhone)){
					return RESULT.error(message("user.textcode.error"));
				}
				if(!codeValidate){
					return RESULT.error(message("user.textcode.error"));
				}else{
					customer=customerService.queryUser(loginPhone);
					if(customer==null){
						/*ECustomer newCustomer=new ECustomer();
						newCustomer.setLoginPhone(loginPhone);
						newCustomer.setCreateDate(new Date());
						newCustomer.setStatus("0");
						String tradeName=loginPhone.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
						newCustomer.setTradeName(tradeName);
						customerService.insert(newCustomer, false);
						CookieUtil.addCookie("sms_code", null, null, 0, response);
						customer=customerService.queryUser(loginPhone);*/
						return RESULT.error(message("user.noexists.error"));
					}
				}
			}else if("1".equals(type)){
				String username =  MathUtil.isNull(paramMap.get("userName"));
				//sha256加密
				String password = MD5.toMD5(paramMap.get("passWord"));
				//
				customer=customerService.queryUser(username);
				if (customer!=null) {
					if("3".equals(customer.getStatus())){
						return RESULT.error("帐号不存在");
					}else
					if("2".equals(customer.getStatus())){
						return RESULT.error(message("user.active.need"));
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
							return RESULT.error(message("user.locked"));
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
							return RESULT.error(messages);
						}
					}else{
						return RESULT.error("帐号不存在");
					}
				}else{
					return RESULT.error("帐号不存在");
				}
			}else{
				return RESULT.error("用户名或密码错误");
			}
			// authSSOCookie 设置 cookie 同时改变 jsessionId
			SSOToken token = new SSOToken(request);
			token.setId(customer.getCustomerId());
			token.setUid(customer.getCustomerId()+"");
			token.setType(1);
			request.setAttribute(SSOConfig.SSO_COOKIE_MAXAGE, 2592000);
			//记住密码，设置 cookie 时长 1 周 = 604800 秒 【动态设置 maxAge 实现记住密码功能】
//			String rememberMe =  MathUtil.isNull(paramMap.get("rememberMe"));
//			if ("on".equals(rememberMe) ) {
//				//记住30天
//				request.setAttribute(SSOConfig.SSO_COOKIE_MAXAGE, 2592000); 
//			}
			SSOHelper.setSSOCookie(request, response, token, true);
			//登录成功 清空错误次数 记录登录ip地址和时间
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("customerId", customer.getCustomerId());
			map.put("loginCount", 0);
			//获取ip
            String ip = RequestUtil.getIpAddr(request);
            if ("0:0:0:0:0:0:0:1".equals(ip)) {
            	map.put("loginIp", "本机IP");
			} else {
				map.put("loginIp", ip);
			}
			map.put("loginDate", new Date());
			customerService.updateLoginCount(map);
			RESULT result=RESULT.ok(message("user.login.success"));
			if (StringUtil.isNotEmpty(paramMap.get("ReturnURL"))) {
				String urlString = paramMap.get("ReturnURL");
				try {
					urlString = URLDecoder.decode(paramMap.get("ReturnURL"), "utf-8");
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
//				String searchString = request.getContextPath()+"/";
//				int start = urlString.indexOf(searchString);
//				if (start>0) {
//					urlString = urlString.substring(start+searchString.length());
//				}
				result.put("href", urlString);
			} else {
				result.put("href", "");
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RESULT.error(message("user.login.failed"));
	}
	
	/**
	 * 退出登录
	 */
	@Permission("usr:logined")
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException {
		/**
		 * SSO 退出，清空退出状态即可
		 * 
		 * 子系统退出 SSOHelper.logout(request, response); 注意 sso.properties 包含 退出到
		 * SSO 的地址 ， 属性 sso.logout.url 的配置
		 */
		SSOHelper.clearLogin(request, response);
		
		response.sendRedirect(request.getContextPath()+"/pages/userEntry.html"); 
	}
	
	/**
	 * 注册帐号
	 */
	@ResponseBody
	@RequestMapping(value="regist", method=RequestMethod.POST)
	public RESULT registAccount(HttpServletResponse response,HttpServletRequest request,@RequestBody String params) {
		try {
			Map<String, String> paramMap = AesCryptUtil.decrypt2JSON(params);
			String type = paramMap.get("registType");
			String userType = paramMap.get("userType");
			String inviteId = CookieUtil.getCookie(request,"_gaga");
			String phone="";
			String email ="";
			String password="";
			ECustomer customer=new ECustomer();
			if("0".equals(type)){
				phone =  MathUtil.isNull(paramMap.get("phone"));
				String smsCode= MathUtil.isNull(paramMap.get("zcSmsCode"));
				String cookieCode = CookieUtil.getCookie(request, "sms_code");
				//手机号验重
				int nameFalg=customerService.repeatPhone(phone);
				if(nameFalg>0){
					return RESULT.error(message("user.phone.error"));
				}
				if(null==cookieCode){
					return RESULT.error("验证码不正确");
				}
				String cookie=cookieCode.split("\\|\\|")[1];
				String cookiePhone=cookieCode.split("\\|\\|")[0];
				Boolean codeValidate=EmailValidateUtil.smsValidate(cookie, smsCode, DECRYPT_KEY);
				if(!cookiePhone.equals(phone)){
					return RESULT.error(message("user.textcode.error"));
				}
				if(!codeValidate){
					return RESULT.error(message("user.textcode.error"));
				}else{
					customer.setLoginPhone(phone);
					customer.setStatus("0");
					customer.setLoginDate(new Date());
					if(userType!=null && "mp".equals(userType)){
						customer.setUserType("mobilephone");
					}
					String tradeName=phone.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
					customer.setTradeName(tradeName);
				}
			}else
			if("1".equals(type)){
				email =  MathUtil.isNull(paramMap.get("loginEamil"));
				password =  MathUtil.isNull(paramMap.get("zcPassWord"));
				//邮件验重
				int eamilFalg=customerService.repeatEmail(email);
				if(eamilFalg>0){
					return RESULT.error(message("user.eamil.error"));
				}
				customer.setLoginEmail(email);
				customer.setPwd(MD5.toMD5(password));
				customer.setStatus("2");
				String tradeName=email.replaceAll("(\\w?)(\\w+)(\\w)(@\\w+\\.[a-z]+(\\.[a-z]+)?)", "$1****$3$4");
				customer.setTradeName(tradeName);
			}else{
				return RESULT.error("注册失败");
			}
			customer.setCreatedate(new Date());
			//邀请人验证
			if(inviteId!=null&&!"".equals(inviteId)){
				ECustomer customerflag=customerService.queryUserById(MathUtil.parseLong(inviteId));
				if(customerflag!=null){
					customer.setInviteId(MathUtil.parseLong(inviteId));
				}
			}
			int flag = customerService.insert(customer, false);
			CookieUtil.addCookie("sms_code", null, null, 0, response);
			if(flag>0&&"1".equals(type)){
				String modelName="activeUser";
				String uuid=customer.getCustomerId().toString();
				String emailTempl=SING_UP_TEMPLET_KEY;
				return EmailValidateUtil.sendEmail(request,messageTempletService,customerService,emailTempl,modelName,uuid,email);
			}
			if(flag>0&&"0".equals(type)){
				SSOToken token = new SSOToken(request);
				token.setId(customer.getCustomerId());
				token.setUid(customer.getCustomerId()+"");
				token.setType(1);
				SSOHelper.setSSOCookie(request, response, token, true);
			}
			String userAgent = request.getHeader("USER-AGENT");
			boolean isphone =CheckMobile.check(userAgent);
			return RESULT.ok().put("isphone", isphone);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RESULT.error(message("user.singup.error"));
	}
	
	/**
	 * 重新发送激活邮件
	 */
	@ResponseBody
	@RequestMapping(value="activeUserAgain", method=RequestMethod.GET)
	public RESULT activeUserAgain() {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String email =  MathUtil.isNull(request.getParameter("email"));
		ECustomer customer = customerService.queryUser(email);
		if(customer!=null){
			if(customer.getStatus().equals("2")){
				String modelName="activeUser";
				String uuid=customer.getCustomerId().toString();
				String emailTempl=SING_UP_TEMPLET_KEY;
				return EmailValidateUtil.sendEmail(request,messageTempletService,customerService,emailTempl,modelName,uuid,email);
			}else{
				return RESULT.error(message("user.activated"));
			}
		}else{
			return RESULT.error(message("user.email.nofound"));
		}
	}
	
	/**
	 * 发送链接页面
	 */
	@RequestMapping(value="emailRegist", method=RequestMethod.GET)
	public String emailRegist(Model model){
		response.setHeader("Access-Control-Allow-Origin", "*");
		String email =request.getParameter("email");
		model.addAttribute("email", email);
		return "email_regist";
	}
	
	/**
	 * 注册成功
	 */
	@RequestMapping(value="signUpSuccess", method=RequestMethod.GET)
	public String signUpSuccess(Model model){
		response.setHeader("Access-Control-Allow-Origin", "*");
		String name =request.getParameter("n");
		name=name.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
		name=name.replaceAll("(\\w?)(\\w+)(\\w)(@\\w+\\.[a-z]+(\\.[a-z]+)?)", "$1****$3$4");
		model.addAttribute("name", name);
		model.addAttribute("dealerList",dealerService.queryEdealer());
		return "signup_success";
	}
	/**
	 * 激活帐号
	 */
	@RequestMapping(value="activeUser", method=RequestMethod.GET)
	public void activeUser(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {
			//获取数据
			String uuid= MathUtil.isNull(request.getParameter("uuid"));
			String email= MathUtil.isNull(request.getParameter("email"));
			Long time=Long.parseLong(request.getParameter("t"));
			//时间处理
			Date date = new Date();//获取当前时间    
			Calendar calendar = Calendar.getInstance();    
			calendar.setTime(date);
			calendar.add(Calendar.HOUR_OF_DAY, -2);
			Date nowDate = calendar.getTime();
			ECustomer customer = customerService.queryUser(email);
			if(customer==null){
				return;
			}
			if(customer.getCustomerId().toString().equals(uuid)&&nowDate.getTime()<=time&&customer.getChangeInfoDate().equals(time)&&email.equals(customer.getLoginEmail())){
				if(customer.getStatus().equals("2")){
					Map<String,Object> customerMap=new HashMap<String, Object>();
					customerMap.put("customerId", uuid);
					customerMap.put("status", "0");
					customerMap.put("inviteId",customer.getInviteId());
					customerService.updateStatus(customerMap);
					SSOToken token = new SSOToken(request);
					token.setId(uuid);
					token.setUid(uuid);
					token.setType(1);
					SSOHelper.setSSOCookie(request, response, token, true);
					//获取路径
					response.sendRedirect("signUpSuccess?n="+email);
				}else{
					response.sendRedirect("../index");
				}
			}else{
				response.sendRedirect("../index");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 忘记密码页面
	 */
	@RequestMapping(value={"forgotPwd"}, method=RequestMethod.GET)
	public String forgotPwd() {
		response.setHeader("Access-Control-Allow-Origin", "*");
		return "forgot_pwd";
	}
	/**
	 * 忘记密码-手机
	 */
	@RequestMapping(value={"forgotByPhone"}, method=RequestMethod.POST)
	public String forgotByPhone(Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String forgotPhone = request.getParameter("forgotPhone");
		String forgotPhoneCode = request.getParameter("forgotPhoneCode");
		//手机号验重
		int nameFalg=customerService.repeatPhone(forgotPhone);
		if(nameFalg<1){
			model.addAttribute("codeError", "该帐号不存在");
			return "forgot_pwd";
		}
		String cookieCode = CookieUtil.getCookie(request, "verify_code");
		if((!cookieCode.equalsIgnoreCase(forgotPhoneCode))){
			model.addAttribute("forgotPhone",forgotPhone);
			model.addAttribute("codeError", message("user.textcode.error"));
			return "forgot_pwd";
		}
		model.addAttribute("codeError", "");
		model.addAttribute("forgotPhone",forgotPhone);
		return "forgot_phone";
	}
	
	
	@RequestMapping(value={"phoneValidate"}, method=RequestMethod.POST)
	public String phoneValidate(Model model,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String forgotPhone = request.getParameter("forgotPhone");
		String forgotSmsCode = request.getParameter("forgotSmsCode");
		String cookieCode = CookieUtil.getCookie(request, "sms_code");
		if(null==cookieCode){
			model.addAttribute("forgotPhone",forgotPhone);
			model.addAttribute("codeError","验证码不正确");
			return "forgot_phone";
		}
	    String cookie=cookieCode.split("\\|\\|")[1];
	    String cookiePhone=cookieCode.split("\\|\\|")[0];
	    Boolean codeValidate=EmailValidateUtil.smsValidate(cookie, forgotSmsCode, DECRYPT_KEY);
		if((!codeValidate)||(!cookiePhone.equals(forgotPhone))){
			model.addAttribute("forgotPhone",forgotPhone);
			model.addAttribute("codeError",message("user.textcode.error"));
			return "forgot_phone";
		}else{
			ECustomer customer = customerService.queryUser(forgotPhone);
			if(null==customer){
				model.addAttribute("codeError","该帐号不存在");
			}
			try {
				String uuid = AesCryptUtil.encrypt(customer.getCustomerId().toString(),DECRYPT_KEY, DECRYPT_KEY);
				CookieUtil.addCookie("uuid", uuid, null, 3600, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "new_pwd";
		}
		
	}
	/**
	 * 忘记密码-邮箱
	 */
	@RequestMapping(value={"forgotByEmail"}, method=RequestMethod.GET)
	public String forgotByEmail() {
		response.setHeader("Access-Control-Allow-Origin", "*");
		return "forgot_email";
	}
	
	/**
	 * 忘记密码-验证
	 */
	@ResponseBody
	@RequestMapping(value="forgotEmail", method=RequestMethod.POST)
	public RESULT forgotPassword(@RequestBody String params,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {
			Map<String, String> paramMap = AesCryptUtil.decrypt2JSON(params);
			String email =  MathUtil.isNull(paramMap.get("forgotEmail"));
			ECustomer customer = customerService.queryUser(email);
			if(customer!=null){
				String modelName="changePassword";
				String uuid=customer.getCustomerId().toString();
				String emailTempl=FORGET_PWD_TEMPLET_KEY;
				//发送邮件
				String uuids = AesCryptUtil.encrypt(uuid,DECRYPT_KEY, DECRYPT_KEY);
				CookieUtil.addCookie("uuid", uuids, null, 3600, response);
				return EmailValidateUtil.sendEmail(request,messageTempletService,customerService,emailTempl,modelName,uuid,email);
			}else{
				return RESULT.error(message("user.email.nofound"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return RESULT.error(message("user.reset.error"));
		}
	}
	/**
	 * 忘记密码-邮箱发送
	 */
	@RequestMapping(value={"emailSend"}, method=RequestMethod.GET)
	public String emailSend(Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String forgortEmail=request.getParameter("email");
		model.addAttribute("forgortEmail", forgortEmail);
		return "forgot_email_send";
	}
	
	/**
	 * 忘记密码-新密码
	 */
	@RequestMapping(value="changePassword", method=RequestMethod.GET)
	public void changePassword(HttpServletResponse response,Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		try{
			//获取数据
			String uuid= MathUtil.isNull(request.getParameter("uuid"));
			String email= MathUtil.isNull(request.getParameter("email"));
			Long time=Long.parseLong(request.getParameter("t"));
			ECustomer customer = customerService.queryUser(email);
			if(null==customer){
				redirectTo(request, response, "/index");
			}else{
				//验证链接
				Boolean linkBoolean=EmailValidateUtil.emailValidate(uuid,time,email,customer);
				if(!linkBoolean){
					redirectTo(request, response, "/index");
				}else {
					redirectTo(request, response, "/user/newPwd");
				}
			}
		}catch (Exception e) {
			e.printStackTrace();	
		}
	}
	
	/**
	 * 忘记密码-新密码
	 */
	@RequestMapping(value="newPwd", method=RequestMethod.GET)
	public String newPwd() {
		response.setHeader("Access-Control-Allow-Origin", "*");
		return "new_pwd";
	}
		
		
	
	/**
	 * 忘记密码-新密码-保存
	 */
	@ResponseBody
	@RequestMapping(value="saveNewPword", method=RequestMethod.POST)
	public String saveNewPword(@RequestBody String params,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		try{
			Map<String, String> paramMap = AesCryptUtil.decrypt2JSON(params);
			String newPassWord = MD5.toMD5(paramMap.get("newPassWord"));
			String uuidCookie=CookieUtil.getCookie(request, "uuid");
			String uuid= MathUtil.isNull(AesCryptUtil.decrypt(uuidCookie,DECRYPT_KEY,DECRYPT_KEY));
			ECustomer customer = customerService.queryUserById(MathUtil.parseLong(uuid));
			if(null!=customer){
				ECustomer eCustomer=new ECustomer();
				eCustomer.setCustomerId(MathUtil.parseLong(uuid));
				eCustomer.setPwd(newPassWord);
				eCustomer.setChangeInfoDate(new Date().getTime());
				//数据库操作
				CookieUtil.addCookie("uuid", null, null, 0, response);
				customerService.updatePwdOrEmail(eCustomer);
				return RESULT.ok().aes();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return RESULT.error(message("user.update.error")).aes();
	}
	
	//忘记密码-设置成功
	@RequestMapping(value={"success_save"}, method=RequestMethod.GET)
	public String succesSave() {
		response.setHeader("Access-Control-Allow-Origin", "*");
		return "retrieve_pword";
	}
	
	//手机端-绑定手机获取验证码
	@ResponseBody
	@Permission("usr:logined")
	@RequestMapping(value={"bdPhoneCode/{loginPhone}"}, method=RequestMethod.GET)
	public RESULT bdPhoneCode(@PathVariable("loginPhone") String loginPhone) {
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			int radomInt=(int) ((Math.random()*9+1)*100000);
			try {
				token=SSOHelper.attrToken(request);
				//手机号验重
				int nameFalg=customerService.repeatPhone(loginPhone);
				if(nameFalg>0){
					return RESULT.error("该号码已经存在");
				}
				String code = AesCryptUtil.encrypt(radomInt+"",DECRYPT_KEY, DECRYPT_KEY);
				//短信发送
				String smsSendCode = SmsSend.smsSend(loginPhone, radomInt+"");
				System.out.println(radomInt+"===="+code);
				if(!"".equals(smsSendCode)&&"Success".equals(smsSendCode)){
					CookieUtil.addCookie("sms_code", code, null, 300, response);
					return RESULT.ok(message("user.sendsms.success"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}	
	
	//手机端-绑定手机/修改手机
	@ResponseBody
	@Permission("usr:logined")
	@RequestMapping(value={"boundPhone"}, method=RequestMethod.POST)
	public RESULT boundPhone(@RequestBody String params) {
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			try {
				token=SSOHelper.attrToken(request);
				Map<String, String> paramMap = AesCryptUtil.decrypt2JSON(params);
				String smsCode=paramMap.get("smsCode");
				String loginPhone=paramMap.get("loginPhone");
				String cookieCode = CookieUtil.getCookie(request, "sms_code");
				if(null==cookieCode){
					return RESULT.error("验证码不正确");
				}
				if(null==loginPhone ||"".equals(loginPhone)) {
					return RESULT.error("手机号码不正确");
				}
				Boolean codeValidate=EmailValidateUtil.smsValidate(cookieCode,smsCode, DECRYPT_KEY);
				if((!codeValidate)){
					return RESULT.error(message("user.textcode.error"));
				}else{
					//执行绑定
					ECustomer customer=new ECustomer();
					customer.setCustomerId(MathUtil.parseLong(token.getUid()));
					customer.setLoginPhone(loginPhone);
					customerService.updatePwdOrEmail(customer);
					CookieUtil.addCookie("sms_code", null, null, 0, response);
					return RESULT.ok().put("phoneView", customer.getLoginPhoneView());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	//手机端-修改绑定手机号码发送验证码
	@ResponseBody
	@Permission("usr:logined")
	@RequestMapping(value={"upPhoneCode"}, method=RequestMethod.GET)
	public RESULT updatePhone(Model model) {
		Token token=SSOHelper.getToken(request);
		if(null!=token){
			Long customerId = MathUtil.parseLong(token.getUid());
			ECustomer customer = customerService.queryUserById(customerId);
			String loginPhone = customer.getLoginPhone();
			int radomInt=(int) ((Math.random()*9+1)*100000);
			try {
				String code = AesCryptUtil.encrypt(radomInt+"",DECRYPT_KEY, DECRYPT_KEY);
				//短信发送
				String smsSendCode = SmsSend.smsSend(loginPhone, radomInt+"");
				System.out.println(radomInt+"===="+code);
				//String smsSendCode="Success";
				if(!"".equals(smsSendCode)&&"Success".equals(smsSendCode)){
					CookieUtil.addCookie("sms_code", code, null, 300, response);
					return RESULT.ok(message("user.sendsms.success"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return RESULT.error();	
		}
		return null;
	}
	
	
	
	//修改绑定手机 验证
	@Permission("usr:logined")
	@ResponseBody
	@RequestMapping(value={"updateValidate"}, method=RequestMethod.POST)
	public RESULT updateValidate(@RequestBody String params,HttpServletResponse response){
		try {
			SSOToken token=SSOHelper.attrToken(request);
			ECustomer customer= customerService.queryUserById(MathUtil.parseLong(token.getUid()));
			Map<String, String> paramMap = AesCryptUtil.decrypt2JSON(params);
			String smsCode=paramMap.get("upSms");
			String cookieCode = CookieUtil.getCookie(request, "sms_code");
			if(null==cookieCode){
				return RESULT.error("验证码不正确");
			}
			String cookie=cookieCode.split("\\|\\|")[1];
		    String cookiePhone=cookieCode.split("\\|\\|")[0];
		    Boolean codeValidate=EmailValidateUtil.smsValidate(cookie, smsCode, DECRYPT_KEY);
			if((!codeValidate)||(!cookiePhone.equals(customer.getLoginPhone()))){
				return RESULT.error(message("user.textcode.error"));
			}else{
				CookieUtil.addCookie("sms_code", null, null, 0, response);
				return RESULT.ok();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RESULT.error();
	}
	
	//修改绑定手机
	@Permission("usr:logined")
	@RequestMapping(value={"newPhone"}, method=RequestMethod.GET)
	public String newPhone(Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			//用户基本信息
			ECustomer customer = customerService.queryUserById(customerId);
			//签到数据
			CustomerAttendance info = getAttendanceInfo();
			//未读消息
			int msgUnReadNum = customerService.msgUnReadNum(customerId);
			model.addAttribute("msgUnReadNum",msgUnReadNum);
			model.addAttribute("attendanceInfo",info);
			model.addAttribute("eCustomer", CustomerUtil.getCustomer(customer));
			model.addAttribute("ractive", true);
			return "update_new_phone";
		}
		return null;
	}
	
	//绑定
	@Permission("usr:logined")
	@ResponseBody
	@RequestMapping(value={"updatePhone"}, method=RequestMethod.POST)
	public RESULT updatePhone(@RequestBody String params,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {
			Token token=SSOHelper.getToken(request);
			if(null!=token){
				Map<String, String> paramMap = AesCryptUtil.decrypt2JSON(params);
				String phone= MathUtil.isNull(paramMap.get("boundPhone"));
				String smsCode= MathUtil.isNull(paramMap.get("boundSms"));
				String cookieCode = CookieUtil.getCookie(request, "sms_code");
				if(null==cookieCode){
					return RESULT.error("验证码不正确");
				}
				String cookie=cookieCode.split("\\|\\|")[1];
			    String cookiePhone=cookieCode.split("\\|\\|")[0];
			    Boolean codeValidate=EmailValidateUtil.smsValidate(cookie, smsCode, DECRYPT_KEY);
			    if(!cookiePhone.equals(phone)){
			    	return RESULT.error("验证码不正确");
			    }
				if(!codeValidate){
					return RESULT.error("验证码不正确");
				}
				ECustomer customer=new ECustomer();
				customer.setCustomerId(MathUtil.parseLong(token.getUid()));
				customer.setLoginPhone(phone);
				customerService.updatePwdOrEmail(customer);
				CookieUtil.addCookie("sms_code", null, null, 0, response);
				return RESULT.ok();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RESULT.error();
	}
	
	//绑定成功
	@Permission("usr:logined")
	@RequestMapping(value={"boundPhoneSuccess"}, method=RequestMethod.GET)
	public String boundPhoneSuccess(Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			String phone=request.getParameter("phone");
			Long customerId = MathUtil.parseLong(token.getUid());
			//用户基本信息
			ECustomer customer = customerService.queryUserById(customerId);
			//签到数据
			CustomerAttendance info = getAttendanceInfo();
			//未读消息
			int msgUnReadNum = customerService.msgUnReadNum(customerId);
			model.addAttribute("msgUnReadNum",msgUnReadNum);
			model.addAttribute("attendanceInfo",info);
			model.addAttribute("eCustomer", CustomerUtil.getCustomer(customer));
			model.addAttribute("ractive", true);
			model.addAttribute("phone", phone);
			model.addAttribute("ractive", true);
			return "bound_phone_success";
		}
		return null;
	}
	//修改绑定成功
	@Permission("usr:logined")
	@RequestMapping(value={"updatePhoneSuccess"}, method=RequestMethod.GET)
	public String updatePhoneSuccess(Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			//用户基本信息
			ECustomer customer = customerService.queryUserById(customerId);
			//签到数据
			CustomerAttendance info = getAttendanceInfo();
			//未读消息
			int msgUnReadNum = customerService.msgUnReadNum(customerId);
			model.addAttribute("msgUnReadNum",msgUnReadNum);
			model.addAttribute("attendanceInfo",info);
			model.addAttribute("eCustomer", CustomerUtil.getCustomer(customer));
			model.addAttribute("ractive", true);
			String phone=request.getParameter("phone");
			model.addAttribute("phone", phone);
			model.addAttribute("ractive", true);
			return "update_phone_success";
		}
		return null;
	}
	
	
	
	
	
	//绑定邮箱
	@Permission("usr:logined")
	@RequestMapping(value={"boundEmail"}, method=RequestMethod.GET)
	public String boundEmail(Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			//用户基本信息
			ECustomer customer = customerService.queryUserById(customerId);
			//签到数据
			CustomerAttendance info = getAttendanceInfo();
			//未读消息
			int msgUnReadNum = customerService.msgUnReadNum(customerId);
			model.addAttribute("msgUnReadNum",msgUnReadNum);
			model.addAttribute("attendanceInfo",info);
			model.addAttribute("eCustomer", CustomerUtil.getCustomer(customer));
			model.addAttribute("ractive", true);
			return "bound_email";
		}
		return null;
	}
	//修改绑定邮箱
	@Permission("usr:logined")
	@RequestMapping(value={"updateEmail"}, method=RequestMethod.GET)
	public String updateEmail(Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			//用户基本信息
			ECustomer customer = customerService.queryUserById(customerId);
			//签到数据
			CustomerAttendance info = getAttendanceInfo();
			//未读消息
			int msgUnReadNum = customerService.msgUnReadNum(customerId);
			model.addAttribute("msgUnReadNum",msgUnReadNum);
			model.addAttribute("attendanceInfo",info);
			model.addAttribute("eCustomer", CustomerUtil.getCustomer(customer));
			model.addAttribute("ractive", true);
			return "update_email";
		}
		return null;
	}
	
	//修改绑定邮箱验证
	@Permission("usr:logined")
	@ResponseBody
	@RequestMapping(value={"updateEmailSend"}, method=RequestMethod.POST)
	public RESULT updateEmailSend(Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String email=request.getParameter("email"); 
		Token token=SSOHelper.getToken(request);
		if(null!=token){
			ECustomer customer = customerService.queryUserById(MathUtil.parseLong(token.getUid()));
			if(email.equals(customer.getLoginEmail())){
				String modelName="updateEmailValidate";
				String uuid=token.getUid();
				String emailTempl=CHANGE_EMAIL_TEMPLET_KEY;
				//发送邮件
				return EmailValidateUtil.sendEmail(request,messageTempletService,customerService,emailTempl,modelName,uuid,email);
			}else{
				return RESULT.error("邮箱地址不正确");
			}
		}
		return RESULT.error();
	}
	//修改绑定邮箱验证
	@Permission("usr:logined")
	@RequestMapping(value={"upEmailSend"}, method=RequestMethod.GET)
	public String upEmailSend(Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			//用户基本信息
			ECustomer customer = customerService.queryUserById(customerId);
			//签到数据
			CustomerAttendance info = getAttendanceInfo();
			//未读消息
			int msgUnReadNum = customerService.msgUnReadNum(customerId);
			model.addAttribute("msgUnReadNum",msgUnReadNum);
			model.addAttribute("attendanceInfo",info);
			model.addAttribute("eCustomer", CustomerUtil.getCustomer(customer));
			String email=request.getParameter("email");
			model.addAttribute("email",email);
			model.addAttribute("ractive", true);
			return "update_email_send";
		}
		return null;
	}
	//修改绑定邮箱验证 验证链接
	@Permission("usr:logined")
	@RequestMapping(value={"updateEmailValidate"}, method=RequestMethod.GET)
	public void updateEmailValidate(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		try{
			Token token=SSOHelper.getToken(request);
			if(null!=token){
				//获取数据
				String uuid= MathUtil.isNull(request.getParameter("uuid"));
				String email= MathUtil.isNull(request.getParameter("email"));
				Long time=Long.parseLong(request.getParameter("t"));
				
				ECustomer customer=customerService.queryUserById(MathUtil.parseLong(token.getUid()));
				if(null==customer){
					redirectTo(request, response, "/index");
				}else{
					//验证链接
					Boolean linkBoolean=EmailValidateUtil.emailValidate(uuid,time,email,customer);
					if(!linkBoolean){
						redirectTo(request, response, "/index");
					}else {
						redirectTo(request, response, "/user/updateNewEmail");
					}
				}
			}else{
				redirectTo(request, response, "/index");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//修改绑定邮箱-绑定新邮箱
	@Permission("usr:logined")
	@RequestMapping(value={"updateNewEmail"}, method=RequestMethod.GET)
	public String updateEmailValidate(Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			//用户基本信息
			ECustomer customer = customerService.queryUserById(customerId);
			//签到数据
			CustomerAttendance info = getAttendanceInfo();
			//未读消息
			int msgUnReadNum = customerService.msgUnReadNum(customerId);
			model.addAttribute("msgUnReadNum",msgUnReadNum);
			model.addAttribute("attendanceInfo",info);
			model.addAttribute("eCustomer", CustomerUtil.getCustomer(customer));
			model.addAttribute("ractive", true);
			return "update_new_email";
		}
		return null;
	}
	//修改绑定邮箱-绑定新邮箱
	@Permission("usr:logined")
	@ResponseBody
	@RequestMapping(value={"boundNewEmail"}, method=RequestMethod.POST)
	public RESULT boundNewEmail() {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String email=request.getParameter("email");
		Token token=SSOHelper.getToken(request);
		ECustomer customer = customerService.queryUser(email);
		if(customer!=null){
			return RESULT.error("邮箱已存在");
		}
		if(null!=token){
			ECustomer eCustomer=new ECustomer();
			eCustomer.setCustomerId(MathUtil.parseLong(token.getUid()));
			eCustomer.setLoginEmail(email);
			eCustomer.setChangeInfoDate(new Date().getTime());
			customerService.updatePwdOrEmail(eCustomer);
			return RESULT.ok();
		}
		return RESULT.error();
	}
	//修改绑定邮箱-绑定新邮箱-成功
	@Permission("usr:logined")
	@RequestMapping(value={"updateEmailSuccess"}, method=RequestMethod.GET)
	public String updateEmailSuccess(Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			//用户基本信息
			ECustomer customer = customerService.queryUserById(customerId);
			//签到数据
			CustomerAttendance info = getAttendanceInfo();
			//未读消息
			int msgUnReadNum = customerService.msgUnReadNum(customerId);
			model.addAttribute("msgUnReadNum",msgUnReadNum);
			model.addAttribute("attendanceInfo",info);
			model.addAttribute("eCustomer", CustomerUtil.getCustomer(customer));
			model.addAttribute("ractive", true);
			String email=request.getParameter("email");
			model.addAttribute("email",email);
			return "update_email_success";
		}
		return null;
	}
	
	//绑定邮箱-发送邮件
	@Permission("usr:logined")
	@ResponseBody
	@RequestMapping(value={"boundEmailSend"}, method=RequestMethod.POST)
	public RESULT boundEmailSend(@RequestBody String params, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {
			Token token=SSOHelper.getToken(request);
			if(null!=token){
				Map<String, String> paramMap = AesCryptUtil.decrypt2JSON(params);
				String email= paramMap.get("bound_email");
				ECustomer customer = customerService.queryUser(email);
				if(customer!=null){
					return RESULT.error("已存在的邮箱");
				}else{
					String modelName="emailValidate";
					String uuid=token.getUid();
					String emailTempl=CHANGE_EMAIL_TEMPLET_KEY;
					//发送邮件
					return EmailValidateUtil.sendEmail(request,messageTempletService,customerService,emailTempl,modelName,uuid,email);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RESULT.error();
	}
	//绑定邮箱-发送邮件-成功
	@Permission("usr:logined")
	@RequestMapping(value={"emailSendSuccess"}, method=RequestMethod.GET)
	public String emailSendSuccess(Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			//用户基本信息
			ECustomer customer = customerService.queryUserById(customerId);
			//签到数据
			CustomerAttendance info = getAttendanceInfo();
			//未读消息
			int msgUnReadNum = customerService.msgUnReadNum(customerId);
			model.addAttribute("msgUnReadNum",msgUnReadNum);
			model.addAttribute("attendanceInfo",info);
			model.addAttribute("eCustomer", CustomerUtil.getCustomer(customer));
			model.addAttribute("ractive", true);
			String email= request.getParameter("email");
			model.addAttribute("email",email);
			return "bound_email_send";
		}
		return null;
	}
	
	//绑定邮箱-邮件链接验证
	@RequestMapping(value={"emailValidate"}, method=RequestMethod.GET)
	public void emailValidate(Model model,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		try{
			Token token=SSOHelper.getToken(request);
			//获取数据
			String uuid= MathUtil.isNull(request.getParameter("uuid"));
			String email= MathUtil.isNull(request.getParameter("email"));
			if(null!=token){
				Long time=Long.parseLong(request.getParameter("t"));
				ECustomer customer=customerService.queryUserById(MathUtil.parseLong(token.getUid()));
				if(null==customer){
					redirectTo(request, response, "/index");
				}else{
					//验证链接
					if(null==customer.getLoginEmail()||"".equals(customer.getLoginEmail())){
						customer.setLoginEmail(email);
					}else{
						redirectTo(request, response, "/index");
						return;
					}
					Boolean linkBoolean=EmailValidateUtil.emailValidate(uuid,time,email,customer);
					if(!linkBoolean){
						redirectTo(request, response, "/index");
						return;
					}
					ECustomer eCustomer=new ECustomer();
					eCustomer.setCustomerId(customer.getCustomerId());
					eCustomer.setLoginEmail(email);
					eCustomer.setChangeInfoDate(new Date().getTime());
					customerService.updatePwdOrEmail(eCustomer);
					redirectTo(request, response, "/user/boundEmailSuccess?email="+email);
					return;
				}
			}else{
				redirectTo(request, response, "/user/boundEmailSuccess?email="+email);
				return;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//邮箱绑定成功
	@Permission("usr:logined")
	@RequestMapping(value={"boundEmailSuccess"}, method=RequestMethod.GET)
	public String boundEmailSuccess(Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			//用户基本信息
			ECustomer customer = customerService.queryUserById(customerId);
			//签到数据
			CustomerAttendance info = getAttendanceInfo();
			//未读消息
			int msgUnReadNum = customerService.msgUnReadNum(customerId);
			model.addAttribute("msgUnReadNum",msgUnReadNum);
			model.addAttribute("attendanceInfo",info);
			model.addAttribute("eCustomer", CustomerUtil.getCustomer(customer));
			String email= request.getParameter("email");
			model.addAttribute("email",email);
			model.addAttribute("ractive", true);
			return "bound_email_success";
		}
		return null;
	}
	
	//设置密码
	@Permission("usr:logined")
	@RequestMapping(value={"settingPwd"}, method=RequestMethod.GET)
	public String settingPwd(Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			//用户基本信息
			ECustomer customer = customerService.queryUserById(customerId);
			//签到数据
			CustomerAttendance info = getAttendanceInfo();
			//未读消息
			int msgUnReadNum = customerService.msgUnReadNum(customerId);
			model.addAttribute("msgUnReadNum",msgUnReadNum);
			model.addAttribute("attendanceInfo",info);
			model.addAttribute("eCustomer", CustomerUtil.getCustomer(customer));
			String phoneNumber = customer.getLoginPhone();
			String phoneNumbers=phoneNumber.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
			model.addAttribute("phone",phoneNumber);
			model.addAttribute("phones",phoneNumbers);
			model.addAttribute("ractive", true);
			return "setting_pwd";
		}
		return null;
	}
	//设置密码-手机验证
	@Permission("usr:logined")
	@ResponseBody
	@RequestMapping(value={"setPwdValidate"}, method=RequestMethod.POST)
	public RESULT setPwdValidate(@RequestBody String params,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {
			SSOToken token=SSOHelper.attrToken(request);
			ECustomer customer= customerService.queryUserById(MathUtil.parseLong(token.getUid()));
			Map<String, String> paramMap = AesCryptUtil.decrypt2JSON(params);
			String smsCode=paramMap.get("setPwdSms");
			String cookieCode = CookieUtil.getCookie(request, "sms_code");
			if(null==cookieCode){
				return RESULT.error("验证码不正确");
			}
			String cookie=cookieCode.split("\\|\\|")[1];
		    String cookiePhone=cookieCode.split("\\|\\|")[0];
			Boolean codeValidate=EmailValidateUtil.smsValidate(cookie, smsCode, DECRYPT_KEY);
			if((!codeValidate)||(!cookiePhone.equals(customer.getLoginPhone()))){
				return RESULT.error(message("user.textcode.error"));
			}else{
				CookieUtil.addCookie("sms_code", null, null, 0, response);
				return RESULT.ok();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RESULT.error();
	}
	//设置密码
	@Permission("usr:logined")
	@RequestMapping(value={"settingNewPwd"}, method=RequestMethod.GET)
	public String settingNewPwd(Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			//用户基本信息
			ECustomer customer = customerService.queryUserById(customerId);
			//签到数据
			CustomerAttendance info = getAttendanceInfo();
			//未读消息
			int msgUnReadNum = customerService.msgUnReadNum(customerId);
			model.addAttribute("msgUnReadNum",msgUnReadNum);
			model.addAttribute("attendanceInfo",info);
			model.addAttribute("eCustomer", CustomerUtil.getCustomer(customer));
			model.addAttribute("ractive", true);
			return "setting_new_pwd";
		}
		return null;
	}
	//设置登录密码-保存登录密码
	@Permission("usr:logined")
	@ResponseBody
	@RequestMapping(value={"saveNewPwd"}, method=RequestMethod.POST)
	public RESULT saveNewPwd(@RequestBody String params) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {
			Token token=SSOHelper.getToken(request);
			if(null!=token){
				Map<String, String> paramMap = AesCryptUtil.decrypt2JSON(params);
				String newPwd= MathUtil.isNull(paramMap.get("newPwd"));
				ECustomer customer=new ECustomer();
				customer.setCustomerId(MathUtil.parseLong(token.getUid()));
				customer.setPwd(MD5.toMD5(newPwd));
				customerService.updatePwdOrEmail(customer);
				return RESULT.ok();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RESULT.error();
	}
	
	//设置密码-设置成功
	@Permission("usr:logined")
	@RequestMapping(value={"setPwdSuccess"}, method=RequestMethod.GET)
	public String setPwdSuccess(Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			//用户基本信息
			ECustomer customer = customerService.queryUserById(customerId);
			//签到数据
			CustomerAttendance info = getAttendanceInfo();
			//未读消息
			int msgUnReadNum = customerService.msgUnReadNum(customerId);
			model.addAttribute("msgUnReadNum",msgUnReadNum);
			model.addAttribute("attendanceInfo",info);
			model.addAttribute("eCustomer", CustomerUtil.getCustomer(customer));
			model.addAttribute("ractive", true);
			return "setting_pwd_success";
		}
		return null;
	}
	
	//修改密码
	@Permission("usr:logined")
	@RequestMapping(value={"updatePwd"}, method=RequestMethod.GET)
	public String updatePwd(Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			//用户基本信息
			ECustomer customer = customerService.queryUserById(customerId);
			//签到数据
			CustomerAttendance info = getAttendanceInfo();
			//未读消息
			int msgUnReadNum = customerService.msgUnReadNum(customerId);
			model.addAttribute("msgUnReadNum",msgUnReadNum);
			model.addAttribute("attendanceInfo",info);
			model.addAttribute("eCustomer", CustomerUtil.getCustomer(customer));
			model.addAttribute("ractive", true);
			return "update_pwd";
		}
		return null;
	}
	//修改密码-修改
	@Permission("usr:logined")
	@ResponseBody
	@RequestMapping(value={"updateNewPwd"}, method=RequestMethod.POST)
	public RESULT updateNewPwd(@RequestBody String params) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {
			Token token=SSOHelper.getToken(request);
			if(null!=token){
				Map<String, String> paramMap = AesCryptUtil.decrypt2JSON(params);
				String oldPwd= MathUtil.isNull(paramMap.get("oldPwd"));
				String pwd=MD5.toMD5(oldPwd);
				String newPwd= MathUtil.isNull(paramMap.get("newPwd"));
				ECustomer eCustomer=customerService.queryUserById(MathUtil.parseLong(token.getUid()));
				if(eCustomer.getPwd().equals(pwd)){
					ECustomer customer=new ECustomer();
					customer.setCustomerId(MathUtil.parseLong(token.getUid()));
					customer.setPwd(MD5.toMD5(newPwd));
					customerService.updatePwdOrEmail(customer);
					return RESULT.ok();
				}else{
					return RESULT.error("密码错误");
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RESULT.error();
	}
	
	//修改密码
	@Permission("usr:logined")
	@RequestMapping(value={"updatePwdSuccess"}, method=RequestMethod.GET)
	public String updatePwdSuccess(Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			//用户基本信息
			ECustomer customer = customerService.queryUserById(customerId);
			//签到数据
			CustomerAttendance info = getAttendanceInfo();
			//未读消息
			int msgUnReadNum = customerService.msgUnReadNum(customerId);
			model.addAttribute("msgUnReadNum",msgUnReadNum);
			model.addAttribute("attendanceInfo",info);
			model.addAttribute("eCustomer", CustomerUtil.getCustomer(customer));
			model.addAttribute("ractive", true);
			return "update_pwd_success";
		}
		return null;
	}
	
	//手机端-页面获取手机号码展示
	@ResponseBody
	@Permission("usr:logined")
	@RequestMapping(value={"settingCfc"}, method=RequestMethod.GET)
	public RESULT setCertification(Model model,HttpServletResponse response) throws IOException {
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			ECustomer customer = customerService.queryUserById(customerId);
			Map<String,Object> eCustomer = new HashMap<String,Object>();
			eCustomer.put("loginPhoneView", customer.getLoginPhoneView());
			return RESULT.ok().put("eCustomer", eCustomer);
		}
		return null;
	}
	
	//手机端-实名认证中短信验证码
	@ResponseBody
	@Permission("usr:logined")
	@RequestMapping(value="smrzCode" ,method=RequestMethod.GET) 
	public RESULT smrzCode(HttpServletResponse response) {
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			ECustomer customer = customerService.queryUserById(customerId);
			String loginPhone = customer.getLoginPhone();
			int radomInt=(int) ((Math.random()*9+1)*100000);
			try {
				String code = AesCryptUtil.encrypt(radomInt+"",DECRYPT_KEY, DECRYPT_KEY);
				//短信发送
				String smsSendCode = SmsSend.smsSend(loginPhone, radomInt+"");
				System.out.println(radomInt+"===="+code);
				//String smsSendCode="Success";
				if(!"".equals(smsSendCode)&&"Success".equals(smsSendCode)){
					CookieUtil.addCookie("sms_code", code, null, 300, response);
					return RESULT.ok(message("user.sendsms.success"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return RESULT.error();
		}
		return null;
	}
	
	//手机端-验证短信验证码
	@ResponseBody
	@Permission("usr:logined")
	@RequestMapping(value={"yzCode"}, method=RequestMethod.POST)
	public RESULT yzCode(@RequestBody String params,HttpServletResponse response) {
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Map<String, String> paramMap;
			try {
				paramMap = AesCryptUtil.decrypt2JSON(params);
				String cookie = CookieUtil.getCookie(request, "sms_code");
				if(null==cookie){
					return RESULT.error().put("code_error", message("user.textcode.error"));
				}
				if(null!=paramMap.get("code")){
					Boolean codeValidate=EmailValidateUtil.smsValidate(cookie,paramMap.get("code"), DECRYPT_KEY);
					if(!codeValidate){
						return RESULT.error().put("code_error", message("user.textcode.error"));
					}else {
						return RESULT.ok();
					}
				}
			} catch (Exception e) {
				return RESULT.error();
			}
			return RESULT.error();
		}
			return null;
	}	
	
	//手机端-实名认证操作
	@ResponseBody
	@Permission("usr:logined")
	@RequestMapping(value={"updateCardNumb"}, method=RequestMethod.POST)
	public RESULT updateCardNumb(@RequestBody String params,HttpServletResponse response) {
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Map<String, String> paramMap;
			try {
				Long customerId = MathUtil.parseLong(token.getUid());
				ECustomer customerNow = customerService.queryUserById(customerId);
				if(customerNow.getUserName()!=null && customerNow.getUserCardNo()!=null){
					return RESULT.error().put("code_error", "您已实名认证过，请勿重复认证");
				}
				paramMap = AesCryptUtil.decrypt2JSON(params);
				Boolean certification = false;
				String cookie = CookieUtil.getCookie(request,"sms_code");
				if(null==cookie){
					return RESULT.error().put("code_error", message("user.textcode.error"));
				}
				if(null!=paramMap.get("code")&&null!=paramMap.get("userName")&&null!=paramMap.get("idNumb")){
					Boolean codeValidate=EmailValidateUtil.smsValidate(cookie,paramMap.get("code"), DECRYPT_KEY);
					if(!codeValidate){
						return RESULT.error().put("code_error", message("user.textcode.error"));
					}
					String userName = paramMap.get("userName");
					String idNumb = paramMap.get("idNumb");
					certification = Idcard.certification(userName, idNumb);
					if(certification){
						CookieUtil.addCookie("sms_code", null, null, 0, response);
						ECustomer customer = new ECustomer();
						customer.setCustomerId(customerId);
						customer.setUserName(userName);
						customer.setUserCardNo(idNumb);
						customerService.updateBasicByShiMing(customer);
						return RESULT.ok();
					}
				}
			} catch (Exception e) {
				return RESULT.error();
			}
			return RESULT.error();
		}
		return null;
	}
	
	//实名认证成功
	@Permission("usr:logined")
	@RequestMapping(value={"certificationSuccess"}, method=RequestMethod.GET)
	public String certificationSuccess(Model model) {
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			//用户基本信息
			ECustomer customer = customerService.queryUserById(customerId);
			//签到数据
			CustomerAttendance info = getAttendanceInfo();
			model.addAttribute("attendanceInfo",info);
			model.addAttribute("eCustomer", CustomerUtil.getCustomer(customer));
			model.addAttribute("ractive", true);
			return "certification_success";
		}
		return null;
	}
	
	//实名认证失败
	@Permission("usr:logined")
	@RequestMapping(value={"certificationFaild"}, method=RequestMethod.GET)
	public String certificationFaild(Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			//用户基本信息
			ECustomer customer = customerService.queryUserById(customerId);
			if(customer.getUserCardNo()!=null&&customer.getUserName()!=null){
				return "redirect:/";
			}
			//签到数据
			CustomerAttendance info = getAttendanceInfo();
			model.addAttribute("attendanceInfo",info);
			model.addAttribute("eCustomer", CustomerUtil.getCustomer(customer));
			model.addAttribute("ractive", true);
			return "certification_faild";
		}
		return null;
	}
	
	//手机端-签到数据初始化
	@ResponseBody
	@Permission("usr:logined")
	@RequestMapping(value={"attendanceInfo"}, method=RequestMethod.GET)
	public RESULT attendanceInfo(HttpServletResponse response) {
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			//用户基本信息
			ECustomer customer = customerService.queryUserById(customerId);
			//签到数据
			CustomerAttendance info = getAttendanceInfo();
			Long userIntegral = customer.getIntegral();
			return RESULT.ok().put("attendanceInfo",info)
					.put("userIntegral", userIntegral);
		}else {
			return null;
		}
	}
	
	//手机端-绑定或修改登录邮箱
	@Permission("usr:logined")
	@ResponseBody
	@RequestMapping(value={"bindEmail"}, method=RequestMethod.POST)
	public RESULT bindEmail(@RequestBody String params) {
		try {
			Token token=SSOHelper.getToken(request);
			if(null!=token){
				Map<String, String> paramMap = AesCryptUtil.decrypt2JSON(params);
				String passWord = MathUtil.isNull(paramMap.get("password"));
				String newemail = MathUtil.isNull(paramMap.get("newemail"));
				if(newemail==null || newemail.equals("")) {
					return RESULT.error("邮箱号码有误");
				}
				passWord = MD5.toMD5(passWord);
				Long customerId = MathUtil.parseLong(token.getUid());
				ECustomer customer = customerService.queryUserById(customerId);
				if(!passWord.equals(customer.getPwd())){
					return RESULT.error("密码有误");
				}
				ECustomer cus = new ECustomer();
				cus.setCustomerId(customerId);
				cus.setLoginEmail(newemail);
				customerService.updatePwdOrEmail(cus);
				return RESULT.ok().put("emailView", cus.getLoginEmailView());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RESULT.error();
	}
	
	//签到数据回显
	public CustomerAttendance getAttendanceInfo(){
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
	
	//邀请好友
	@ResponseBody
	@RequestMapping(value="invite", method=RequestMethod.GET)
	public RESULT invite(Model model){
		SSOToken token=SSOHelper.getToken(request);
		if(token!=null){
			ECustomer customer = customerService.queryInviteInfo(MathUtil.parseLong(token.getUid()));
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("inviteAmount", MathUtil.parseBigDecimal(customer.getInviteAmount(),2,false));
			map.put("inviteNum", customer.getInviteNum());
			map.put("inviteIntegral", customer.getInviteIntegral());
			//邀请链接
			String basePath = request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/";
			String inviteUrl=basePath+"user/receive?uuid="+token.getUid();
			/*model.addAttribute("customer",customer);
			model.addAttribute("customerList",customerList);*/
			return RESULT.ok().put("inviteUrl", inviteUrl).put("inviteInfo", map);
		}else{
			return RESULT.error("请您登录后再来邀请小伙伴");
		}
	}
	
	//活动接受页
	@RequestMapping(value="receive", method=RequestMethod.GET)
	public String receive(Model model,HttpServletResponse response){
		try{  
			String uuid=request.getParameter("uuid");
			CookieUtil.addCookie("_gaga", uuid, null, 1296000, response);
			//String userAgent = request.getHeader("USER-AGENT");  
	        //boolean isphone =CheckMobile.check(userAgent);  
	        List<Map<String, Object>> list=customerService.queryInviteAll();
	        List<Map<String, Object>> customerList=new ArrayList<Map<String,Object>>();
	        for (Map<String, Object> map : list) {
	        	Map<String, Object> maps=new HashMap<String, Object>();
	        	String name=charEcordingFilterUtil.specialCharFilter(map.get("trade_name").toString());
	        	Object obj =map.get("invite_amount");
				BigDecimal invite_amount;
				if(obj!=null){
					BigDecimal amount=new BigDecimal(obj+"");
					invite_amount=MathUtil.parseBigDecimal(amount, 2, false);
				}else{
					invite_amount=new BigDecimal(0);
				}
				maps.put("trade_name", name);
				maps.put("invite_amount", invite_amount+"");
	        	customerList.add(maps);
	        }
        	//if(isphone){
        		//model.addAttribute("customerList",customerList);
	        	//model.addAttribute("uuid" ,uuid);
	        	redirectTo(request, response, "/pages/inviterRegister.html");
	      //  }else{  
	            //电脑访问操作  
	        	//model.addAttribute("customerList",customerList);
	        	//model.addAttribute("uuid" ,uuid);
	        	//return "invite_pc";
	       // }
		}catch(Exception e){
		} 
		return null;
	}
	
	//wap注册成功
	@RequestMapping(value="singUpSuccessWap", method=RequestMethod.GET)
	public String singUpSuccessWap(Model model){
		response.setHeader("Access-Control-Allow-Origin", "*");
		String phone= request.getParameter("n");
		phone=phone.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
		model.addAttribute("phone",phone);
		return "signup_success_wap";
	}
		
}
