package org.ieforex.controller;

import javax.servlet.http.HttpServletResponse;

import org.ieforex.entity.ECustomerAccountBasic;
import org.ieforex.service.ECustomerAccountBasicService;
import org.ieforex.utils.RESULT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * 客户收款帐号基本信息表
 * 
 * @author brilliance
 * @email 
 * @date 2017-05-10 13:33:07
 */
@Controller
@RequestMapping("ecustomeraccountbasic")
public class ECustomerAccountBasicController extends AbstractController {
	
	@Autowired
	protected HttpServletResponse response;
	@Autowired
	private ECustomerAccountBasicService eCustomerAccountBasicService;
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/searchByCustomerId/{customerId}")
	public RESULT searchByCustomerId(@PathVariable("customerId") Long customerId){
		response.setHeader("Access-Control-Allow-Origin", "*");
		ECustomerAccountBasic customerAccountBasic = eCustomerAccountBasicService.searchByCustomerId(customerId);
		return RESULT.ok().put("eCustomerAccountBasic", customerAccountBasic);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	public RESULT save(@RequestBody ECustomerAccountBasic eCustomerAccountBasic){
		response.setHeader("Access-Control-Allow-Origin", "*");
		if(eCustomerAccountBasic!=null){
			eCustomerAccountBasicService.insert(eCustomerAccountBasic);
			return RESULT.ok();
		}
		return RESULT.error(message("account.save.error"));
	}
	
}
