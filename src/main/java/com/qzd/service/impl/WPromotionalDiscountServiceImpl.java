package org.ieforex.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ieforex.dao.DealerMapper;
import org.ieforex.dao.WPromotionalDiscountMapper;
import org.ieforex.entity.Dealer;
import org.ieforex.entity.WPromotionalDiscount;
import org.ieforex.service.WPromotionalDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("wPromotionalDiscountServiceImpl")
public class WPromotionalDiscountServiceImpl implements WPromotionalDiscountService {

	@Autowired
	private WPromotionalDiscountMapper mapper;
	@Autowired
	private DealerMapper dealerMapper;
	
	
	@Override
	public List<WPromotionalDiscount> queryTop() {
		return mapper.queryTop();
	}

	@Override
	public List<Dealer> dealerList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataStatus", "0");//数据状态
		return dealerMapper.selectHome(map);
	}

	@Override
	public List<WPromotionalDiscount> promoteList(Map<String, Object> map) {
		return mapper.select(map);
	}

	@Override
	public int selectCount() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "1");
		return mapper.selectCount(map);
	}

	@Override
	public WPromotionalDiscount selectById(Long id) {
		return mapper.selectById(id);
	}
}
