package org.ieforex.service;

import java.util.List;

import org.ieforex.entity.Dealer;
import org.ieforex.entity.ForeignExchange;
import org.ieforex.entity.HomePage;

public interface IndexService {

	//首页横幅广告
	List<HomePage> bannerAds();
	//首页交易商文字广告
	List<HomePage> dealerTextAds();
	//首页交易商图文广告
	List<HomePage> dealerAds();
	//首页交易商图文轮播广告
	List<HomePage> dealerScrollAds();
	//首页交易商列表
	List<Dealer> dealerList();
	//首页交易商点差
	List<Dealer> dealerSpread();
	//外汇咨询
	List<ForeignExchange> forexInformation();
	//励志学堂
	List<HomePage> litchiClassroom();
	
}
