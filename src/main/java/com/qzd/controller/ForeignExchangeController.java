package org.ieforex.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ieforex.entity.ForeignExchange;
import org.ieforex.service.ForeignExchangeService;
import org.ieforex.utils.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("exchange")
public class ForeignExchangeController {

	@Autowired
	protected HttpServletResponse response;
	@Autowired
	private ForeignExchangeService foreignExchangeService;
	
	@ResponseBody
	@RequestMapping(value={"/",""}, method=RequestMethod.GET)
	public Map<String, Object> index(HttpServletRequest request) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String t = request.getParameter("t");
		int type = MathUtil.parseInt(t);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bannerExc", foreignExchangeService.bannerExc());
		map.put("dealerAds", foreignExchangeService.dealerAds("8"));
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("categoryType", "0");//行业新闻
		param.put("recommend", "0");
		param.put("status", "1");//1 已发布
		param.put("offset", 0);
		param.put("limit", 4);
		map.put("exchangeTotal", foreignExchangeService.total(param));
		map.put("exchangeList", foreignExchangeService.exchangeList(param));
		map.put("exchangeType", type);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value={"/typeForeign/{type}"}, method=RequestMethod.POST)
	public Map<String, Object> typeForeign(@PathVariable("type") String type){
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("categoryType", type);//类型
		param.put("status", "1");//1 已发布
		param.put("recommend", "0");
		param.put("offset", 0);
		param.put("limit", 4);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("exchangeTotal", foreignExchangeService.total(param));
		map.put("exchangeList", foreignExchangeService.exchangeList(param));
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value={"/moreForeign/{type}"}, method=RequestMethod.POST)
	public Map<String, Object> moreForeign(@PathVariable("type") String type){
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("categoryType", type);//类型
		param.put("status", "1");//1 已发布
		param.put("recommend","0");
		Map<String, Object> map = new HashMap<String, Object>();
		List<ForeignExchange> list = foreignExchangeService.exchangeList(param);
		if(list.size() > 4){
			list = list.subList(4,list.size());
			map.put("exchangeList",list);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value={"/detail/{fid}"}, method=RequestMethod.GET)
	public Map<String, Object> detail(@PathVariable("fid") Long fid){
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("foreign_detail",foreignExchangeService.detail(fid));
		return map;
	}
	
}
