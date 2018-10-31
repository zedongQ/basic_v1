package org.ieforex.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.ieforex.service.ECustomerFundFlowingService;
import org.ieforex.entity.ECustomerFundFlowing;
import org.ieforex.dao.ECustomerFundFlowingMapper;


@Service("eCustomerFundFlowingService")
public class ECustomerFundFlowingServiceImpl implements ECustomerFundFlowingService {
	
	@Autowired
	private ECustomerFundFlowingMapper mapper;
	
	@Override
	public int insert(ECustomerFundFlowing eCustomerFundFlowing){
		return mapper.insert(eCustomerFundFlowing);
	}
	
	@Override
	public int deleteById(Long flowingId){
		return mapper.deleteById(flowingId);
	}
	
	@Override
	public int updateById(ECustomerFundFlowing eCustomerFundFlowing){
		return mapper.updateById(eCustomerFundFlowing);
	}
	
	@Override
	public int updatePartById(Map<String,Object> map){
		return mapper.updatePartById(map);
	}
	
	@Override
	public ECustomerFundFlowing selectById(Long flowingId){
		return mapper.selectById(flowingId);
	}
	
}
