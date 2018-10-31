package org.ieforex.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ieforex.service.impl.LitchiClassroomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("litchiClassroom")
public class LitchiClassroomController {

	@Autowired
	protected HttpServletResponse response;
	@Autowired
	private LitchiClassroomServiceImpl roomService;
	
	@ResponseBody
	@RequestMapping(value={"/", ""}, method=RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("litchiMenu", roomService.litchiMenu());
		map.put("classRoom", roomService.firstClassroom());
		map.put("dealerList", roomService.dealerList());
		return new ModelAndView("litchiClassroom",map);
	}
	
	@ResponseBody
	@RequestMapping(value={"classRoom/{classid}"}, method=RequestMethod.GET)
	public ModelAndView classRoom(@PathVariable("classid") Long classid){
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("litchiMenu", roomService.litchiMenu());
		map.put("dealerList", roomService.dealerList());
		map.put("classRoom", roomService.classRoom(classid));
		return new ModelAndView("litchiClassroom",map);
	}
	
}
