package org.ieforex.service;

import java.util.Map;

import org.ieforex.entity.ECustomerFundFlowing;


/**
 * 
 * 
 * @author zero
 * @email 15638559970
 * @date 2017-08-15 10:36:55
 */
public interface ECustomerFundFlowingService{
	int insert(ECustomerFundFlowing eCustomerFundFlowing);
	int deleteById(Long flowingId);
	int updateById(ECustomerFundFlowing eCustomerFundFlowing);
	int updatePartById(Map<String,Object> map);
	ECustomerFundFlowing selectById(Long flowingId);
}
