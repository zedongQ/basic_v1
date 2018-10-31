package org.ieforex.service;

import java.util.List;
import java.util.Map;

import org.ieforex.entity.IntegralFlowing;


/**
 * 
 * 
 * @author zero
 * @email 15638559970
 * @date 2017-08-11 14:46:49
 */
public interface WIntegralFlowingService{
	//查询所有积分流水
	List<IntegralFlowing> selectAll(Map<String,Object> map);
	//插入积分流水
	int insert(IntegralFlowing integralFlowing);
	//根据ID差积分流水
	IntegralFlowing selectById(Long flowingId);
	//查询总数
	int queryTotal(Map<String,Object> map);
}
