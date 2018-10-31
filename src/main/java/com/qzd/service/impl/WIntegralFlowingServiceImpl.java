package org.ieforex.service.impl;

import java.util.List;
import java.util.Map;

import org.ieforex.dao.IntegralFlowingMapper;
import org.ieforex.entity.IntegralFlowing;
import org.ieforex.service.WIntegralFlowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("wIntegralFlowingService")
public class WIntegralFlowingServiceImpl implements WIntegralFlowingService {
	
	@Autowired
	private IntegralFlowingMapper mapper;
	
	
	@Override
	public IntegralFlowing selectById(Long flowingId){
		return mapper.selectById(flowingId);
	}

	@Override
	public List<IntegralFlowing> selectAll(Map<String, Object> map) {
		return mapper.selectAll(map);
	}

	@Override
	public int insert(IntegralFlowing integralFlowing) {
		return mapper.insert(integralFlowing);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return mapper.queryTotal(map);
	}
	
}
