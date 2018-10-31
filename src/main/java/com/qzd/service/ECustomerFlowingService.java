package org.ieforex.service;

import java.util.Map;

import org.ieforex.entity.ECustomerFlowing;


/**
 * 
 * 
 * @author zero
 * @email 15638559970
 * @date 2017-08-15 10:36:55
 */
public interface ECustomerFlowingService{
	int save(ECustomerFlowing eCustomerFlowing);
	int deleteById(Long flowingId);
	int updateById(ECustomerFlowing eCustomerFlowing);
	int updatePartById(Map<String,Object> map);
	ECustomerFlowing selectById(Long flowingId);
}
