package org.ieforex.service;

import java.util.List;
import java.util.Map;

import org.ieforex.entity.HomePage;
import org.ieforex.entity.ForeignExchange;

public interface ForeignExchangeService {
	//横幅推荐
	List<ForeignExchange> bannerExc();
	//交易商优惠广告
	List<HomePage> dealerAds(String type);
	//优惠促销
	List<ForeignExchange> exchangeList(Map<String, Object> map);
	//总个数
	int total(Map<String, Object> map);
	//详情页面
	Map<String, Object> detail(Long fid);
}
