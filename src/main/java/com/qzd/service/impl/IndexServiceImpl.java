package org.ieforex.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ieforex.dao.DealerMapper;
import org.ieforex.dao.ForeignExchangeMapper;
import org.ieforex.dao.HomePageMapper;
import org.ieforex.entity.Dealer;
import org.ieforex.entity.ForeignExchange;
import org.ieforex.entity.HomePage;
import org.ieforex.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("indexService")
public class IndexServiceImpl implements IndexService{
	
	@Autowired
	private HomePageMapper homePageMapper;
	@Autowired
	private DealerMapper dealerMapper;
	@Autowired
	private ForeignExchangeMapper foreignExchangeServiceMapper;

	@Override
	public List<HomePage> bannerAds() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("imageType", "0");//首页顶部
		map.put("status", "1");//1 已发布
		return homePageMapper.selectByType(map);
	}
	
	@Override
	public List<HomePage> dealerTextAds() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("imageType", "1");//首页交易商文字广告
		map.put("status", "1");//1 已发布
		return homePageMapper.selectByType(map);
	}

	@Override
	public List<HomePage> dealerAds() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("imageType", "2");//首页交易商图文广告
		map.put("status", "1");//1 已发布
		map.put("offset", 0);
		map.put("limit", 5);
		List<HomePage> list = homePageMapper.selectDealer(map);
		return list;
	}
	
	@Override
	public List<HomePage> dealerScrollAds() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("imageType", "3");//首页交易商图文轮播广告
		map.put("status", "1");//1 已发布
		return homePageMapper.selectByType(map);
	}
	
	@Override
	public List<Dealer> dealerSpread() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataStatus", "0");//数据状态
		map.put("isHomeDc", "1");//是否在首页显示
		map.put("offset", 0);
		map.put("limit", 5);
		return dealerMapper.selectHome(map);
	}
	
	@Override
	public List<Dealer> dealerList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataStatus", "0");//数据状态
		map.put("isHomePage", "1");//是否在首页显示
		map.put("offset", 0);//首页交易商图文广告
		map.put("limit", 18);//1 已发布
		return dealerMapper.selectHome(map);
	}
	
	@Override
	public List<ForeignExchange> forexInformation() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "1");//已发布
		map.put("offset", 0);//首页交易商图文广告
		map.put("limit", 6);//1 已发布
		return foreignExchangeServiceMapper.select(map);
	}

	@Override
	public List<HomePage> litchiClassroom() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("imageType", "4");//首页励志广告
		map.put("status", "1");//1 已发布
		map.put("offset", 0);
		map.put("limit", 3);
		return homePageMapper.selectByType(map);
	}



}
