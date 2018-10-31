package org.ieforex.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ieforex.service.HelpCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("helpCenter")
public class HelpCenterController {
	@Autowired
	protected HttpServletResponse response;
	@Autowired
	private HelpCenterService helpCenterService;
	
	@RequestMapping(value={"/", "home"}, method=RequestMethod.GET)
	public Map<String, Object> index(HttpServletRequest request) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("helpMenu", helpCenterService.helpMenu());
		map.put("helpRoom", helpCenterService.firstHelp());
		map.put("dealerList", helpCenterService.dealerList());
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value={"helpRoom/{classid}"}, method=RequestMethod.GET)
	public Map<String, Object> classRoom(@PathVariable("classid") Long classid){
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("helpMenu", helpCenterService.helpMenu());
		map.put("helpRoom", helpCenterService.helpRoom(classid));
		map.put("dealerList", helpCenterService.dealerList());
		return map;
	}
}
