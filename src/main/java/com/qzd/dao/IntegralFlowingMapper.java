package org.ieforex.dao;

import java.util.List;
import java.util.Map;

import org.ieforex.entity.IntegralFlowing;
import org.springframework.stereotype.Repository;

@Repository("integralFlowingMapper")
public interface IntegralFlowingMapper {
	//查询所有积分流水
	List<IntegralFlowing> selectAll(Map<String,Object> map);
	//插入积分流水
	int insert(IntegralFlowing integralFlowing);
	//根据ID差积分流水
	IntegralFlowing selectById(Long flowingId);
	//查询总数
	int queryTotal(Map<String,Object> map);
	//中奖及兑换记录
	List<IntegralFlowing> customerLog(Map<String,Object> map);
	//
	int logTotal(Long customerId);
}
