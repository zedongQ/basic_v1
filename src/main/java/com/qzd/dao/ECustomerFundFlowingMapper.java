package org.ieforex.dao;

import java.util.Map;

import org.ieforex.entity.ECustomerFundFlowing;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author zero
 * @email 15638559970
 * @date 2017-08-15 10:36:55
 */
@Repository("eCustomerFundFlowingMapper")
public interface ECustomerFundFlowingMapper {
	int insert(ECustomerFundFlowing eCustomerFundFlowing);
	int deleteById(Long flowingId);
	int updateById(ECustomerFundFlowing eCustomerFundFlowing);
	int updatePartById(Map<?,?> map);
	ECustomerFundFlowing selectById(Long flowingId);
}
