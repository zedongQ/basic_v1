package org.ieforex.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.ieforex.entity.Dealer;
import org.ieforex.entity.HomePage;
import org.ieforex.enums.RebatePeriodType;
import org.ieforex.service.DealerService;
import org.ieforex.service.HomePageService;
import org.ieforex.service.WLiveChatService;
import org.ieforex.service.WPromotionalDiscountService;
import org.ieforex.utils.CookieUtil;
import org.ieforex.utils.MathUtil;
import org.ieforex.utils.RESULT;
import org.ieforex.utils.SearchSelectOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("broker")
public class DealerController extends AbstractController{
	
	@Autowired
	protected HttpServletResponse response;
	@Autowired
	private DealerService dealerService;
	@Autowired
	private WPromotionalDiscountService promotionalService;
	@Autowired
	private HomePageService homePageService;
	@Autowired
	private WLiveChatService liveChatService;
	
	//交易商首页
	@ResponseBody
	@RequestMapping(value={"home"}, method=RequestMethod.GET)
	public Map<String, Object> home(){
		Map<String, Object> resultmap = new HashMap<String, Object>();
		List<Object> rebatePeriodType = new ArrayList<Object>();
		//筛选条件
		//机构
		List<Map<String, String>> regulator =dealerService.queryRegulator();
		//周期
		for (RebatePeriodType item : RebatePeriodType.values()) {
			SearchSelectOption sso = new SearchSelectOption(item.value(),item.describe());
			rebatePeriodType.add(sso);
		}
		//顶部广告
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("imageType", "5");//交易商列表顶部横幅
		map.put("status", "1");//1 已发布
		List<HomePage> dealerTopAdv = homePageService.selectByType(map);
		if(dealerTopAdv.size()>0){
			resultmap.put("dealerTopAdv",dealerTopAdv);
		}else{
			resultmap.put("dealerTopAdv","");
		}
		//交易商列表右侧优惠轮播
		map.put("imageType", "12");
		map.put("status", "1");    //1 已发布
		List<HomePage> dealerAds = homePageService.selectByType(map);
		resultmap.put("dealerAds",dealerAds);
		resultmap.put("regulator",regulator);
		resultmap.put("rebatePeriodType",rebatePeriodType);
		//加载客服
		/**
		List<WLiveChat> popList = liveChatService.queryList("0");
		if(popList.size()>0){
			resultmap.put("liveChat",popList.get(0).getLiveChatQq());
		}else{
			resultmap.put("liveChat","");
		}
		*/
		return resultmap;
	}
	//交易商首页-异步加载交易商
	@ResponseBody
	@RequestMapping(value={"list"}, method=RequestMethod.POST)
	public Map<String, Object> list(){
		Map<String, Object> resultmap = new HashMap<String, Object>();
		String dicId=request.getParameter("dicId");
		String rmbInOut=request.getParameter("rmbInOut");
		String rebatePeriodType=request.getParameter("rebatePeriodType");
		String isKhzj=request.getParameter("isKhzj");//1 开户赠金筛选
		String orderby=request.getParameter("orderby");
		String orderType=request.getParameter("orderType");
		String dealerName=request.getParameter("dealerName");
		/**
		int pageNum=MathUtil.parseInt(request.getParameter("offset"));
		int limit=3;
		int offset=limit*(pageNum-1);
		*/
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("dicId", dicId);
		map.put("rmbInOut", rmbInOut);
		map.put("rebatePeriodType", rebatePeriodType);
		if("1".equals(isKhzj)){
			map.put("accountGold", "1");
			map.put("nowDate", new Date());
		}
		map.put("dealerName",dealerName);
		map.put("orderby", orderby);
		/**
		map.put("offset", offset);
		map.put("limit", limit);
		*/
		map.put("orderType",orderType);
		List<Dealer> list = dealerService.queryList(map);
		int total =dealerService.queryTotal(map);
		List<Dealer> dealerList = dealerService.queryEdealer();
		//顶部广告
		Map<String, Object> mapAds = new HashMap<String, Object>();
		mapAds.put("imageType", "5");//交易商列表顶部横幅
		mapAds.put("status", "1");//1 已发布
		List<HomePage> dealerTopAdv = homePageService.selectByType(mapAds);
		if(dealerTopAdv.size()>0){
			resultmap.put("dealerTopAdv",dealerTopAdv);
		}else{
			resultmap.put("dealerTopAdv","");
		}
		resultmap.put("list", list);
		resultmap.put("dealerList", dealerList);
		resultmap.put("total", total);
		return resultmap;
	}
	
	//交易商详情
	@ResponseBody
	@RequestMapping(value={"detail/{dealerId}"}, method=RequestMethod.GET)
	public Map<String, Object> dealerDetail(@PathVariable("dealerId") Long dealerId){
		Dealer dealer= dealerService.queryDealerById(dealerId);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		resultmap.put("dealer",dealer);
//		SSOToken token=SSOHelper.getToken(request);
//		if(null!=token){
//			model.addAttribute("openAccount","1");
//		}else{
//			model.addAttribute("openAccount","0");
//		}
		return resultmap;
	}
	
	//交易商对比详情页
	@RequestMapping(value={"compare"}, method=RequestMethod.GET)
	public String compare(Model model){
		
		return "dealer_compare";
	}
	
	@ResponseBody
	@RequestMapping(value={"dealerCompare"}, method=RequestMethod.GET)
	public RESULT dealerCompare(Model model){
		String cookieStr = CookieUtil.getCookie(request, "dealerItem");
		if(null==cookieStr){
			return RESULT.ok().put("dealerList", null);
		}
		try {
			String aString= URLDecoder.decode(cookieStr,"UTF-8");
			String[] cookieArr = aString.split(",");
			Long[] dealerId=new Long[cookieArr.length];
			for(int i=0;i<cookieArr.length;i++){
				dealerId[i]=MathUtil.parseLong(cookieArr[i].split("\\|\\|")[0]);
			}
			List<Dealer> dealerList = dealerService.dealerCompare(dealerId);
			return RESULT.ok().put("dealerList", dealerList);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return RESULT.error();
	}
	//点差对比
	@RequestMapping(value={"dianChaCompare"}, method=RequestMethod.GET)
	public String dianChaCompare(Model model){
		
		return "cp_dc_tablel";
	}
	
	//利息对比
	@RequestMapping(value={"liXiCompare"}, method=RequestMethod.GET)
	public String liXiCompare(Model model){
		
		return "cp_lx_tablel";
	}
	
	//交易商首页
	@ResponseBody
	@RequestMapping(value={"alldealer"}, method=RequestMethod.GET)
	public List<Dealer> getAllDealer(){
		List<Dealer> dealerList = dealerService.queryEdealer();
		return dealerList;
	}
	
		
}
