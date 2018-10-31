package org.ieforex.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.ieforex.dao.ActivityFlowingMapper;
import org.ieforex.dao.ActivityMapper;
import org.ieforex.dao.ActivityPrizeMapper;
import org.ieforex.dao.ECustomerAddressMapper;
import org.ieforex.dao.ECustomerMapper;
import org.ieforex.dao.ETradeAcctMapper;
import org.ieforex.dao.GoodsFlowingMapper;
import org.ieforex.dao.GoodsMapper;
import org.ieforex.dao.HomePageMapper;
import org.ieforex.dao.IntegralFlowingMapper;
import org.ieforex.dao.RebateResultMapper;
import org.ieforex.entity.Activity;
import org.ieforex.entity.ActivityFlowing;
import org.ieforex.entity.ActivityPrize;
import org.ieforex.entity.ECustomer;
import org.ieforex.entity.ECustomerAddress;
import org.ieforex.entity.Goods;
import org.ieforex.entity.GoodsFlowing;
import org.ieforex.entity.HomePage;
import org.ieforex.entity.IntegralFlowing;
import org.ieforex.service.IndianaService;
import org.ieforex.utils.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("indianaService")
public class IndianaServiceImpl implements IndianaService{
	
	@Autowired
	private HomePageMapper homePageMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private GoodsFlowingMapper goodsFlowingMapper;
	@Autowired
	private ActivityMapper activityMapper;
	@Autowired
	private ActivityPrizeMapper activityPrizeMapper;
	@Autowired
	private ActivityFlowingMapper activityFlowingMapper;
	@Autowired
	private ECustomerMapper customerMapper;
	@Autowired
	private ETradeAcctMapper tradeAcctMapper;
	@Autowired
	private RebateResultMapper rebateResultMapper;
	@Autowired
	private IntegralFlowingMapper integralFlowingMapper;
	@Autowired
	private ECustomerAddressMapper addressMapper;
	
	@Override
	public Map<String, Object> lottery(Long cid) {
		Activity activity = activityMapper.selectNew();
		/**map -1配置问题  0 获得奖品 1活动过期  2积分不足*/
		Map<String, Object> map = new HashMap<String, Object>();
		if(null!=activity){
			/**判断抽奖次数和积分*/
			ECustomer cs = customerMapper.queryUserById(cid);
			Long lotNumber = (cs.getLotteryNumber() == null) ? new Long(0):cs.getLotteryNumber();
			Long integral = (cs.getIntegral() == null) ? new Long(0):cs.getIntegral();
			int lotteryType = 0;
			if(lotNumber.longValue() >= new Long(1).longValue()){
				lotteryType = 1;//使用免费抽奖券
			}else if(integral.longValue() >= new Long(activity.getLotteryCost()).longValue()){
				lotteryType = 2;//使用积分
			}else{
				map.put("status", 2);//2积分不足
				map.put("discription", "积分不足");
				return map;
			}
			Long prizeId= creatPrize(activity.getActivityId());
			map = givePrize(cid,prizeId,lotteryType,activity.getLotteryCost(),activity.getActivityId());
		}else{
			map.put("status", 1);// 1活动暂未开启
			map.put("prize", null);
		}
		return map;
	}

	@Override
	public Map<String, Object> lotteryLog() {
		Activity ac = activityMapper.selectNew();
		if(ac==null){
			ac = activityMapper.selectLast();
		}
		if(ac!=null){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("lotteryCost", ac.getLotteryCost());
			map.put("prizeUrl", ac.getPrizeUrl());
			/**
			List<ActivityFlowing> bigLottery = activityFlowingMapper.selectBigPrize();
			bigLottery.addAll(activityFlowingMapper.select(ac.getActivityId()));
			map.put("lotteryLog", bigLottery);
			map.put("activity", ac);
			*/
			return map;
			
		}
		return null;
	}
	
	@Override
	public Map<String, Object> baseInf(Long customerId) {
		ECustomer customer = customerMapper.queryUserById(customerId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tradeName", customer.getTradeName());
		map.put("headImg", customer.getHeadImg());
		map.put("integral", customer.getIntegral());
		map.put("lotteryNumber", customer.getLotteryNumber());
		return map;
	}
	
	@Override
	public List<Map<String, Object>> goodsList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataStatus", "0");
		List<Goods> list = goodsMapper.select(map);
		List<Map<String, Object>> relist = new ArrayList<Map<String, Object>>();
		if(list!=null && list.size()>0) {
			for(int i=0;i<list.size();i++) {
				Map<String, Object> mp = new HashMap<String, Object>();
				mp.put("goodsId",list.get(i).getGoodsId());
				mp.put("goodsName",list.get(i).getGoodsName());
				mp.put("picUrl",list.get(i).getPicUrl());
				mp.put("totalNumber",list.get(i).getTotalNumber());
				mp.put("costIntegral",list.get(i).getCostIntegral());
				mp.put("describes",list.get(i).getDescribes());
				relist.add(mp);
			}
		}
		return relist;
	}

	@Override
	public Map<String, Object> exchangeGoods(Long cid, Long gid) {
		Map<String, Object> map = new HashMap<String, Object>();
		Goods goods = goodsMapper.selectById(gid);
		if(goods == null){
			map.put("status", -1);//商品不存在
			return map;
		}else{
			ECustomer cs = customerMapper.queryUserById(cid);
			if(cs.getIntegral().longValue() < new Long(goods.getCostIntegral()).longValue()){
				map.put("status", 1);//积分不足
				return map;
			}
			//更新用户积分
			ECustomer customer = new ECustomer();
			customer.setIntegral(new Long(-goods.getCostIntegral()));//减去积分
			customer.setCustomerId(cid);
			customerMapper.updateAmount(customer);
			//更新商品数量
			Goods gd = new Goods();
			gd.setTotalNumber(-1);
			gd.setGoodsId(gid);
			goodsMapper.updateNumber(gd);
			//添加积分流水
			IntegralFlowing integralFlowing = new IntegralFlowing();
			integralFlowing.setCustomerId(cid);
			integralFlowing.setAmount(-goods.getCostIntegral());
			integralFlowing.setDescribes("积分兑换-"+goods.getGoodsName());
			integralFlowing.setType("1");
			integralFlowing.setInsertDate(new Date());
			integralFlowingMapper.insert(integralFlowing);
			//添加商品流水
			GoodsFlowing goodsFlowing = new GoodsFlowing();
			goodsFlowing.setCustomerId(cid);
			goodsFlowing.setGoodsId(gid);
			goodsFlowing.setInsertDate(new Date());
			goodsFlowing.setStatus("0"); /**0未处理 1已处理*/
			/**添加收货地址*/
			goodsAdress(goodsFlowing,cid);
			goodsFlowingMapper.insert(goodsFlowing);
			map.put("status", 0);
			map.put("prizeName", goods.getGoodsName());
		}
		return map;
	}

	@Override
	public List<Map<String, Object>> dealerAds() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("imageType", "7");//抽奖赞助商广告
		map.put("status", "1");//1 已发布
		List<HomePage> list = homePageMapper.selectByType(map);
		List<Map<String, Object>> retList = new ArrayList<Map<String, Object>>();
		if(list!=null && list.size() > 0){
			for(int i=0;i<list.size();i++) {
				HomePage hp = list.get(i);
				Map<String, Object> mp = new HashMap<String, Object>();
				mp.put("linkUrl", hp.getLinkUrl());
				mp.put("picUrl", hp.getPicUrl());
				mp.put("mainTitle", hp.getMainTitle());
				retList.add(mp);
			}
		}else {
			return null;
		}
		return retList;
	}
	
	/**产生奖品*/
	public Long creatPrize(Long activityId){
		List<ActivityPrize> list = activityPrizeMapper.activityPrize(activityId);
		Long prizeId = new Long(0);
		if(list.size() > 0){
			int sum = 0;
			int start = 0;
			int end = 0;
			for(int i=0;i<list.size();i++){
				ActivityPrize ap = list.get(i);
				sum = sum + ap.getWeight();
			}
			Random random = new Random();
			int pos = random.nextInt(sum)+1;
			for(int i=0;i<list.size();i++){
				ActivityPrize ap = list.get(i);
				start = end;
				end = start + ap.getWeight();
				if(pos > start && pos <= end){
					prizeId = ap.getPrizeId();
					break;
				}
			}
		}
		return prizeId;
	}
	
	/**发放奖励*/
	public Map<String,Object> givePrize(Long customerId, Long prizeId,int lotteryType,int lotteryCost,Long activityId){
		Map<String,Object> map = new HashMap<String, Object>();
		ActivityPrize ap = activityPrizeMapper.selectById(prizeId);
		if(ap != null){
			List<String> list = judgeLimit(customerId);
			Boolean flag = false;
			//判断奖品是否合法
			for(int i=0;i < list.size(); i++){
				if(ap.getLimitType().equals(list.get(i))){
					flag = true;
					break;
				}
			}
			//发放奖励
			if(flag==true){
				/**扣除积分或者抽奖券并记录此次消耗积分和奖品*/
				integralLog(ap,customerId,lotteryType,lotteryCost);
				/**判断奖品*/
				if("2".equals(ap.getPrizeType())){//积分奖励(发放奖励)
					ECustomer customer = new ECustomer();
					Long intergral = Long.parseLong(ap.getPrizePrice().toString());
					customer.setIntegral(intergral);//中奖积分
					customer.setCustomerId(customerId);
					customerMapper.updateAmount(customer);
					//奖品数量减1
					updatePrizeNumber(ap.getPrizeId());
					//活动流水
					activityLog(ap,customerId,prizeId);
					//记录积分流水
					IntegralFlowing integralFlowing = new IntegralFlowing();
					integralFlowing.setCustomerId(customerId);
					integralFlowing.setAmount(ap.getPrizePrice());
					integralFlowing.setDescribes("积分抽奖-"+ap.getPrizeName());
					integralFlowing.setType("6");
					integralFlowing.setInsertDate(new Date());
					integralFlowingMapper.insert(integralFlowing);
				}else if("3".equals(ap.getPrizeType())){//再来一次(发放奖励)
					//免费抽奖次数加一
					ECustomer customer = new ECustomer();
					customer.setLotteryNumber(new Long(1));
					customer.setCustomerId(customerId);
					customerMapper.updateAmount(customer);
					//活动流水
					activityLog(ap,customerId,prizeId);
				}else if("4".equals(ap.getPrizeType())){//谢谢参与(无奖励)
					//活动流水
					activityLog(ap,customerId,prizeId);
				}else if("0".equals(ap.getPrizeType()) || "1".equals(ap.getPrizeType())){
					//实物奖励或虚拟奖励需要人工审核(审核奖励)-添加到商品流水里面去处理
					GoodsFlowing goodsFlowing = new GoodsFlowing();
					goodsFlowing.setCustomerId(customerId);
					goodsFlowing.setActivityId(activityId);
					goodsFlowing.setGoodsId(prizeId);
					goodsFlowing.setInsertDate(new Date());
					goodsFlowing.setStatus("0"); /**0未处理 1已处理*/
					/**添加收货地址*/
					goodsAdress(goodsFlowing,customerId);
					goodsFlowingMapper.insert(goodsFlowing);
					//奖品数量减1
					updatePrizeNumber(prizeId);
					//活动流水
					activityLog(ap,customerId,prizeId);
				}
				map.put("status", 0);
				map.put("prize", apList(ap,customerId));
			}else{//奖励不合要求更新奖品为再来一次(发放奖励)
				Map<String , Object> param = new HashMap<String, Object>();
				param.put("activityId", activityId);
				param.put("prizeType", "3");
				ap = activityPrizeMapper.selectByType(param);
				if(ap!=null){
					/**扣除积分或者抽奖券并记录此次消耗积分和奖品*/
					integralLog(ap,customerId,lotteryType,lotteryCost);
					ECustomer customer = new ECustomer();
					customer.setLotteryNumber(new Long(1));//免费抽奖次数加一
					customer.setCustomerId(customerId);
					customerMapper.updateAmount(customer);
					activityLog(ap,customerId,prizeId);
					/**更新奖品数量updatePrizeNumber(ap.getPrizeId());*/
					/**反馈信息*/
					map.put("status", 0);
					map.put("prize", apList(ap,customerId));
				}else{
					map.put("status", -1);
				}
			}
		}else{
			//最后一件奖品冲突,后到者更新奖品为谢谢参与(无奖励)
			Map<String , Object> param = new HashMap<String, Object>();
			param.put("activityId", activityId);
			param.put("prizeType", "4");
			ap = activityPrizeMapper.selectByType(param);
			if(ap!=null){
				/**扣除积分或者抽奖券并记录此次消耗积分和奖品*/
				integralLog(ap,customerId,lotteryType,lotteryCost);
				activityLog(ap,customerId,prizeId);
				/**更新奖品数量updatePrizeNumber(ap.getPrizeId());*/
				/**反馈信息*/
				map.put("status", 0);
				map.put("prize", apList(ap,customerId));
			}else{
				map.put("status", -1);
			}
		}
		return map;
	}
	
	/**判断用户得奖范围*/
	public List<String> judgeLimit(Long customerId){
		List<String> list = new ArrayList<String>();
		list.add("0");
		int count = customerMapper.threeMonthLogin(customerId);
		if(count == 1){
			list.add("1");//3个月内活跃用户
		}
		int tradeCount = tradeAcctMapper.selectCount(customerId);
		if(tradeCount >= 1){
			int rebateCount = rebateResultMapper.selectCount(customerId);
			if(rebateCount == 0){
				list.add("2");//绑定但是无交易
			}
		}else{
			list.add("3");//未绑定交易账号
		}
		return list;
	}
	
	/**扣除积分或者抽奖券并记录此次消耗积分和获得的奖品*/
	public void integralLog(ActivityPrize ap,Long customerId,int lotteryType,int lotteryCost){
		if(lotteryType==1){
			//消耗抽奖券
			ECustomer customer = new ECustomer();
			customer.setLotteryNumber(new Long(-1));//减去一个免费抽奖券
			customer.setCustomerId(customerId);
			customerMapper.updateAmount(customer);
			//记录0积分流水
			IntegralFlowing integralFlowing = new IntegralFlowing();
			integralFlowing.setCustomerId(customerId);
			integralFlowing.setAmount(0);
			integralFlowing.setDescribes("积分抽奖-"+ap.getPrizeName());
			integralFlowing.setType("5");
			integralFlowing.setInsertDate(new Date());
			integralFlowingMapper.insert(integralFlowing);
		}else if(lotteryType==2){
			//消耗积分
			ECustomer customer = new ECustomer();
			customer.setIntegral(new Long(-lotteryCost));//减去积分
			customer.setCustomerId(customerId);
			customerMapper.updateAmount(customer);
			//记录积分流水
			IntegralFlowing integralFlowing = new IntegralFlowing();
			integralFlowing.setCustomerId(customerId);
			integralFlowing.setAmount(-lotteryCost);
			integralFlowing.setDescribes("积分抽奖-"+ap.getPrizeName());
			integralFlowing.setType("0");
			integralFlowing.setInsertDate(new Date());
			integralFlowingMapper.insert(integralFlowing);
		}
	}
	
	/**奖品数量减-1*/
	public void updatePrizeNumber(Long prizeId){
		ActivityPrize activityPrize = new ActivityPrize();
		activityPrize.setPrizeId(prizeId);
		activityPrize.setPrizeAmount(-1);
		activityPrizeMapper.updateAmount(activityPrize);
	}
	
	/**收货地址*/
	public GoodsFlowing goodsAdress(GoodsFlowing flowing,Long customerId){
		List<ECustomerAddress> list = addressMapper.selectByCustomerId(customerId);
		if(list.size()>0){
			ECustomerAddress address = list.get(0);
			/**地址更新*/
			flowing.setReceiver(address.getName());
			StringBuffer sb = new StringBuffer();
			sb.append(MathUtil.parseStr(address.getProvince()));
			sb.append(" ");
			sb.append(MathUtil.parseStr(address.getCity()));
			sb.append(" ");
			sb.append(MathUtil.parseStr(address.getCounty()));
			sb.append(" ");
			sb.append(MathUtil.parseStr(address.getAddress()));
			flowing.setReceiveAddress(sb.toString());
			flowing.setContact(address.getTelephone());
		}
		return flowing;
	}

	/**添加中奖activity流水*/
	public void activityLog(ActivityPrize ap,Long customerId, Long prizeId){
		ECustomer customer = customerMapper.queryUserById(customerId);
		// 记录奖品流水
		ActivityFlowing activityFlowing = new ActivityFlowing();
		activityFlowing.setCustomerId(customerId);
		activityFlowing.setCustomerInf(changeInf(customer));
		activityFlowing.setActivityId(ap.getActivityId());
		activityFlowing.setPrizeId(prizeId);
		activityFlowing.setPrizeName(ap.getPrizeName());
		activityFlowing.setPrizePrice(ap.getPrizePrice());
		activityFlowing.setType(ap.getPrizeType());
		activityFlowing.setInsertDate(new Date());
		activityFlowingMapper.insert(activityFlowing);
	}
	
	public String changeInf(ECustomer customer){
		String customerInf = "";
		if(null != customer.getTradeName() && !"".equals(customer.getTradeName())){
			customerInf = customer.getTradeName();
		}else if(null != customer.getLoginPhoneView() && !"".equals(customer.getLoginPhoneView())){
			customerInf = customer.getLoginPhoneView();
		}else if(null != customer.getLoginEmailView() && !"".equals(customer.getLoginEmailView())){
			customerInf = customer.getLoginEmailView();
		}
		return customerInf;
	}
	
	/**商品信息*/
	public List<String> apList(ActivityPrize ap,Long customerId){
		List<String> aplist = new ArrayList<String>();
		aplist.add(ap.getSortNo().toString());
		aplist.add(ap.getPrizeType());
		aplist.add(ap.getPrizeName());
		aplist.add(ap.getPrizePrice()+"");
		ECustomer customer = customerMapper.queryUserById(customerId);
		aplist.add(customer.getIntegral().toString());
		aplist.add(customer.getLotteryNumber()+"");
		Activity activity = activityMapper.selectNew();
		aplist.add(activity.getLotteryCost().toString());
		return aplist;
	}

	@Override
	public Map<String, Object> goodsLog(Long customerId,Integer curPage) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("customerId", customerId);
		/**
		param.put("offset", curPage * 7);
		param.put("limit", 7);
		*/
		Map<String, Object> map =  new HashMap<String, Object>();
		List<IntegralFlowing> customerLog = integralFlowingMapper.customerLog(param);
		ECustomer customer = customerMapper.queryUserById(customerId);
		String userName = changeInf(customer);
		List<Map<String, Object>> retList = new ArrayList<Map<String, Object>>();
		if(customerLog!=null && customerLog.size()>0) {
			for(int i=0;i<customerLog.size();i++) {
				Map<String, Object> mp = new HashMap<String, Object>();
				mp.put("userName", userName);
				mp.put("describes", customerLog.get(i).getDescribes());
				retList.add(mp);
			}
		}
		map.put("customerGoods", retList);
		return map;
	}

	@Override
	public int totalPage(Long customerId) {
		return integralFlowingMapper.logTotal(customerId);
	}

}
