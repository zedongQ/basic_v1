package org.ieforex.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bcics.sso.SSOHelper;
import org.bcics.sso.SSOToken;
import org.ieforex.service.WLiveChatService;
import org.ieforex.service.impl.IndianaServiceImpl;
import org.ieforex.utils.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("indiana")
public class IndianaController {
	@Autowired
	protected HttpServletResponse response;
	@Autowired
	protected HttpServletRequest request;
	@Autowired
	private IndianaServiceImpl indianaService;
	@Autowired
	private WLiveChatService liveChatService;

	@ResponseBody
	@RequestMapping(value={"home"}, method=RequestMethod.GET)
	public Map<String, Object> index() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dealerAds", indianaService.dealerAds());
		//加载活动信息
		Map<String, Object> activityMap = indianaService.lotteryLog();
		if(activityMap!=null){
			map.put("activity", activityMap);
		} 
		//加载用户基本信息
		SSOToken token = SSOHelper.getToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			map.put("baseInf",indianaService.baseInf(customerId));
		}		
		/**
		map.put("goodsList", indianaService.goodsList()); //商品信息 
		List<WLiveChat> queryList = liveChatService.queryList("1");
		if(queryList.size()>0){
			//加载客服
			map.put("liveChat",queryList.get(0).getLiveChatQq());
		}else{
			map.put("liveChat","");
		}
		*/
		return map;
	}

	@ResponseBody
	@RequestMapping(value={"/goodshome"}, method=RequestMethod.GET)
	public Map<String, Object> goodshome(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goodsList", indianaService.goodsList()); //商品信息 
		//加载用户基本信息
		SSOToken token = SSOHelper.getToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			map.put("baseInf",indianaService.baseInf(customerId));
		}
		return map;
	}
	
	
	@ResponseBody
	@RequestMapping(value={"/lottery"}, method=RequestMethod.GET)
	public Map<String, Object> lottery(HttpServletRequest request){
		SSOToken token = SSOHelper.getToken(request);
		if(token != null){
			Long cid = MathUtil.parseLong(token.getUid());
			return indianaService.lottery(cid);
		}else{//测试使用
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", -2);//未登录
			map.put("discription", "请您在登录后进行该操作");
			return map;
		}
	}
	
	@ResponseBody
	@RequestMapping(value={"/goodsLog"}, method=RequestMethod.GET)
	public Map<String, Object> goodsLog(){
		SSOToken token = SSOHelper.getToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			return indianaService.goodsLog(customerId,0);
		}else{
			return new HashMap<String, Object>();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/exchangeGoods/{gid}", method=RequestMethod.GET)
	public  Map<String, Object> exchangeGoods(@PathVariable("gid") Long gid){
		SSOToken token = SSOHelper.getToken(request);
		if(token != null){
			Long cid = MathUtil.parseLong(token.getUid());
			return indianaService.exchangeGoods(cid, gid);
		}else{//测试使用
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", -2);//未登录
			map.put("discription", "请您在登录后进行该操作");
			return map;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/goodsFlowing/{curPage}", method=RequestMethod.GET)
	public Map<String, Object> goodsFlowing(@PathVariable("curPage") Integer curPage){
		SSOToken token = SSOHelper.getToken(request);
		if(token!=null){
			Long customerId = MathUtil.parseLong(token.getUid());
			return indianaService.goodsLog(customerId, curPage);
		}else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", -2);//未登录
			map.put("discription", "请您在登录后进行该操作");
			return map;
		}	
	}
	
}
