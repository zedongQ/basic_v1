package org.ieforex.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ieforex.entity.WPromotionalDiscount;
import org.ieforex.service.WPromotionalDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("promote")
public class PromotionalController {
	@Autowired
	protected HttpServletResponse response;
	@Autowired
	private WPromotionalDiscountService promotionalService;
	
	@ResponseBody
	@RequestMapping(value={"/", "home"}, method=RequestMethod.GET)
	public Map<String,Object> index(HttpServletRequest request) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultmap = new HashMap<String, Object>();
		resultmap.put("dealerList", promotionalService.dealerList());
		resultmap.put("totalCount", promotionalService.selectCount());
		return resultmap;
	}
	
	@ResponseBody
	@RequestMapping(value={"/promoteList/{curPage}"}, method=RequestMethod.GET)
	public List<WPromotionalDiscount> promoteList(@PathVariable("curPage") Integer curPage){
		response.setHeader("Access-Control-Allow-Origin", "*");
		int offset = curPage * 7;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "1");
		map.put("offset", offset);
		map.put("limit", 7);
		return promotionalService.promoteList(map);
	}
	
	@ResponseBody
	@RequestMapping(value={"/detail/{id}"}, method=RequestMethod.GET)
	public Map<String, Object> detail(@PathVariable("id") Long id) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultmap = new HashMap<String, Object>();
		resultmap.put("promotional", promotionalService.selectById(id));
		return resultmap;
	}
	
}
