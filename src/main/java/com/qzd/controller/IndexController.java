package org.ieforex.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.ieforex.entity.WLiveChat;
import org.ieforex.service.IndexService;
import org.ieforex.service.WLiveChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController{
	@Autowired
	protected HttpServletResponse response;
	@Autowired
	private IndexService indexService;
	@Autowired
	private WLiveChatService liveChatService;

	@ResponseBody
	@RequestMapping(value={"home"}, method=RequestMethod.GET)
	public Map<String,Object> index() {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bannerAds", indexService.bannerAds());
		map.put("dealerAds", indexService.dealerAds());
		/**
		map.put("dealerTextAds", indexService.dealerTextAds());
		Map<String, List<HomePage>> mapAds =  indexService.dealerAds();
		map.put("dealerAdsStart", mapAds.get("dealerAdsStart"));
		map.put("dealerScrollAds", indexService.dealerScrollAds());
		map.put("dealerAdsEnd", mapAds.get("dealerAdsEnd"));
		map.put("dealerList", indexService.dealerList());
		map.put("dealerSpread", indexService.dealerSpread());
		map.put("forexInformation", indexService.forexInformation());
		map.put("litchiClassroom", indexService.litchiClassroom());
		//加载客服
		List<WLiveChat> queryList = liveChatService.queryList("0");
		if(queryList.size()>0){
			map.put("liveChat",queryList.get(0).getLiveChatQq());
		}else{
			map.put("liveChat","");
		}
		*/
		return map;
	}
	
	//在线客服
	@ResponseBody
	@RequestMapping(value={"liveChat"}, method=RequestMethod.GET)
	public String liveChat() {
		response.setHeader("Access-Control-Allow-Origin", "*");
		List<WLiveChat> queryList = liveChatService.queryList("1");
		if(queryList.size()>0){
			return queryList.get(0).getLiveChatQq();
		}else{
			return null;
		}
	}
}
