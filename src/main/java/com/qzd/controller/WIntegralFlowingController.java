package org.ieforex.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ieforex.entity.IntegralFlowing;
import org.ieforex.service.WIntegralFlowingService;
import org.ieforex.utils.RESULT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 
 * 
 * @author zero
 * @email 15638559970
 * @date 2017-08-11 14:46:49
 */
@Controller
@RequestMapping("wintegralflowing")
public class WIntegralFlowingController {

	@Autowired
	protected HttpServletResponse response;
	@Autowired
	private WIntegralFlowingService wIntegralFlowingService;
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{flowingId}")
	@RequiresPermissions("wintegralflowing:info")
	public RESULT info(@PathVariable("flowingId") Long flowingId){
		response.setHeader("Access-Control-Allow-Origin", "*");
		IntegralFlowing wIntegralFlowing = wIntegralFlowingService.selectById(flowingId);
		return RESULT.ok().put("wIntegralFlowing", wIntegralFlowing);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("wintegralflowing:save")
	public RESULT save(@RequestBody IntegralFlowing wIntegralFlowing){
		response.setHeader("Access-Control-Allow-Origin", "*");
		wIntegralFlowingService.insert(wIntegralFlowing);
		return RESULT.ok();
	}
	
}
