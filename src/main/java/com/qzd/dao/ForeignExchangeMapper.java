package org.ieforex.dao;

import java.util.List;
import java.util.Map;

import org.ieforex.entity.ForeignExchange;
import org.springframework.stereotype.Repository;

@Repository("foreignExchangeServiceMapper")
public interface ForeignExchangeMapper{
	List<ForeignExchange> select(Map<String, Object> map);
	int total(Map<String, Object> map);
	int updateTimes(Long serviceId);
	List<ForeignExchange> selectByTimes(Map<String, Object> map);
	ForeignExchange selectById(Long serviceId);
}
