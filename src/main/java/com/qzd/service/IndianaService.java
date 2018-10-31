package org.ieforex.service;

import java.util.List;
import java.util.Map;

import org.ieforex.entity.Goods;

public interface IndianaService {
	
	//转盘抽奖
	Map<String, Object> lottery(Long cid);
	//中奖纪录 -编辑中奖记录
	Map<String, Object> lotteryLog();
	//用户基本信息
	Map<String, Object> baseInf(Long customerId);
	//商品信息
	List<Map<String, Object>> goodsList();
	//商品兑换
	Map<String, Object> exchangeGoods(Long customerId,Long goodsId);
	//活动赞助商(广告位)
	List<Map<String, Object>> dealerAds();
	//中奖及兑换记录
	Map<String, Object> goodsLog(Long customerId,Integer curPage);
	
	int totalPage(Long customerId);
	
}
