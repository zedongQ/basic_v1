package org.ieforex.service.impl;

import java.util.List;
import java.util.Map;

import org.ieforex.dao.WAllCustomerFundFlowingMapper;
import org.ieforex.dao.WThreeMonthCustomerFundFlowingMapper;
import org.ieforex.entity.WThreeMonthCustomerFundFlowing;
import org.ieforex.service.WThreeMonthCustomerFundFlowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.single.SingleTableServiceImpl;
@Service("wThreeMonthCustomerFundFlowingService")
public class WThreeMonthCustomerFundFlowingServiceImpl implements WThreeMonthCustomerFundFlowingService {

	@Autowired
	private WThreeMonthCustomerFundFlowingService mapper;
	
	@Override
	public List<WThreeMonthCustomerFundFlowing> queryList(Map<String, Object> map) {
		return mapper.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return mapper.queryTotal(map);
	}

	@Override
	public WThreeMonthCustomerFundFlowing getRebateById(Long flowingId) {
		return mapper.getRebateById(flowingId);
	}

	@Override
	public WThreeMonthCustomerFundFlowing getWithdrawalById(
			Map<String, Object> map) {
		return mapper.getWithdrawalById(map);
	}

	@Override
	public int insert(WThreeMonthCustomerFundFlowing tmcff) {
		return mapper.insert(tmcff);
	}
}
