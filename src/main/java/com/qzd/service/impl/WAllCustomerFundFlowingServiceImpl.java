package org.ieforex.service.impl;

import java.util.List;
import java.util.Map;

import org.ieforex.dao.WAllCustomerFundFlowingMapper;
import org.ieforex.entity.WAllCustomerFundFlowing;
import org.ieforex.service.WAllCustomerFundFlowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("wAllCustomerFundFlowingService")
public class WAllCustomerFundFlowingServiceImpl implements WAllCustomerFundFlowingService {

	@Autowired
	private WAllCustomerFundFlowingMapper mapper;
	
	@Override
	public List<WAllCustomerFundFlowing> queryList(Map<String, Object> map) {
		return mapper.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return mapper.queryTotal(map);
	}

	@Override
	public List<WAllCustomerFundFlowing> getRebateById(Map<String,Object> map) {
		return mapper.getRebateById(map);
	}

	@Override
	public List<WAllCustomerFundFlowing> getWithdrawalById(Map<String,Object> map) {
		return mapper.getWithdrawalById(map);
	}

	@Override
	public int insert(WAllCustomerFundFlowing acff) {
		return mapper.insert(acff);
	}
}
