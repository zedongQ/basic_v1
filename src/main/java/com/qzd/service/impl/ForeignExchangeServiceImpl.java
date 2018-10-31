package org.ieforex.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.ieforex.dao.ForeignExchangeMapper;
import org.ieforex.dao.HomePageMapper;
import org.ieforex.entity.ForeignExchange;
import org.ieforex.entity.HomePage;
import org.ieforex.service.ForeignExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("foreignExchangeService")
public class ForeignExchangeServiceImpl implements ForeignExchangeService {

	@Autowired
	private HomePageMapper homePageMapper;
	@Autowired
	private ForeignExchangeMapper foreignExchangeMapper;
	
	@Override
	public List<ForeignExchange> bannerExc() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "1");//1 已发布
		map.put("recommend", "1");//1 推荐
		return foreignExchangeMapper.select(map);
	}

	@Override
	public List<HomePage> dealerAds(String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("imageType", type);//8 外汇优惠
		map.put("status", "1");//1 已发布
		return homePageMapper.selectByType(map);
	}

	@Override
	public List<ForeignExchange> exchangeList(Map<String, Object> map) {
		return foreignExchangeMapper.select(map);
	}

	@Override
	public int total(Map<String, Object> map) {
		return foreignExchangeMapper.total(map);
	}

	@Override
	public Map<String, Object> detail(Long fid) {
		//更新阅读次数
		foreignExchangeMapper.updateTimes(fid);
		ForeignExchange fe = foreignExchangeMapper.selectById(fid);
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("status", "1");//1 已发布
		param.put("offset", 0);
		param.put("limit", 5);
		//热门文章
		map.put("hotExchange", hotExchange(param));
		param.put("categoryType", fe.getCategoryType());
		//猜你喜欢的文章
		map.put("likeExchange", mybeYouLike(param,fid));
		//文章详情
		map.put("exchange",fe);
		//交易商优惠
		map.put("dealerAds",dealerAds("9"));//9 外汇资讯详情广告
		return map;
	}
	
	/**全部资讯中热度前20个里面随机取5个*/
	public List<ForeignExchange> hotExchange(Map<String, Object> param){
		param.put("limit", 20);//前10个里面取3个
		List<ForeignExchange> list = foreignExchangeMapper.selectByTimes(param);
		if(list.size() < 6){
			return list;
		}else{
			List<ForeignExchange> li = new ArrayList<ForeignExchange>();
			while(li.size() < 5){
				int x = new Random().nextInt(list.size());
				li.add(list.get(x));
				list.remove(x);
			}
			return li;
		}
	}
	
	/**该类型下热度前6名里面取3个*/
	public List<ForeignExchange> mybeYouLike(Map<String, Object> param,Long fid){
		param.put("limit", 6);//前10个里面取3个
		param.put("serviceId", fid);//除去他自己
		List<ForeignExchange> list = foreignExchangeMapper.selectByTimes(param);
		if(list.size() < 4){
			return list;
		}else{
			while(list.size() > 3){
				list.remove(new Random().nextInt(list.size()));
			}
		}
		return list;
	}

}
