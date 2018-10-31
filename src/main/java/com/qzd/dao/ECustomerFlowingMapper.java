package org.ieforex.dao;

import java.util.Map;

import org.ieforex.entity.ECustomerFlowing;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author zero
 * @email 15638559970
 * @date 2017-08-15 10:36:55
 */
@Repository("eCustomerFlowingMapper")
public interface ECustomerFlowingMapper {
	int save(ECustomerFlowing eCustomerFlowing);
	int deleteById(Long flowingId);
	int updateById(ECustomerFlowing eCustomerFlowing);
	int updatePartById(Map<?,?> map);
	ECustomerFlowing selectById(Long flowingId);
}
