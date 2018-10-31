package org.ieforex.service.impl;

import java.util.List;
import java.util.Map;

import org.ieforex.dao.ECustomerLimitMapper;
import org.ieforex.entity.ECustomerLimit;
import org.ieforex.service.ECustomerLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("eCustomerLimitService")
public class ECustomerLimitServiceImpl implements ECustomerLimitService {

	@Autowired
	private ECustomerLimitMapper mapper;
	
	@Override
	public List<ECustomerLimit> queryList(Map<String, Object> map) {
		return mapper.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return mapper.queryTotal(map);
	}

	@Override
	public ECustomerLimit searchById(Long customerId) {
		return mapper.searchById(customerId);
	}
	
}
