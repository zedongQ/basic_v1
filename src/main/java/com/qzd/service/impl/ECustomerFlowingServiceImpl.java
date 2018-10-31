package org.ieforex.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.ieforex.service.ECustomerFlowingService;
import org.ieforex.entity.ECustomerFlowing;
import org.ieforex.dao.ECustomerFlowingMapper;


@Service("eCustomerFlowingService")
public class ECustomerFlowingServiceImpl implements ECustomerFlowingService {
	
	@Autowired
	private ECustomerFlowingMapper mapper;
	
	@Override
	public int save(ECustomerFlowing eCustomerFlowing){
		return mapper.save(eCustomerFlowing);
	}
	
	@Override
	public int deleteById(Long flowingId){
		return mapper.deleteById(flowingId);
	}
	
	@Override
	public int updateById(ECustomerFlowing eCustomerFlowing){
		return mapper.updateById(eCustomerFlowing);
	}
	
	@Override
	public int updatePartById(Map<String,Object> map){
		return mapper.updatePartById(map);
	}
	
	@Override
	public ECustomerFlowing selectById(Long flowingId){
		return mapper.selectById(flowingId);
	}
	
}
