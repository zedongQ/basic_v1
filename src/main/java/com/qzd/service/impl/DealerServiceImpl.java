package org.ieforex.service.impl;

import java.util.List;
import java.util.Map;

import org.ieforex.dao.DealerMapper;
import org.ieforex.entity.Dealer;
import org.ieforex.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DealerServiceImpl implements DealerService{
	
	
	@Autowired
	private DealerMapper dealerMapper;

	@Override
	public List<Dealer> selectHome(Map<String, Object> map) {
		return dealerMapper.selectHome(map);
	}

	@Override
	public List<Dealer> queryEdealer() {
		return dealerMapper.queryEdealer();
	}

	@Override
	public List<Dealer> queryList(Map<String, Object> map) {
		return dealerMapper.queryList(map);
	}

	@Override
	public List<Map<String, String>> queryRegulator() {
		return dealerMapper.queryRegulator();
	}

	@Override
	public Dealer queryDealerById(Long dealerId) {
		return dealerMapper.queryDealerById(dealerId);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return dealerMapper.queryTotal(map);
	}

	@Override
	public List<Dealer> dealerCompare(Long[] dealerId) {
		return dealerMapper.dealerCompare(dealerId);
	}

}
