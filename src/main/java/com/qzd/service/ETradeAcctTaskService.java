package org.ieforex.service;

import java.util.List;
import java.util.Map;

import org.ieforex.entity.ETradeAcctTask;

/**
 * 交易账号审核表
 * 
 * @author brilliance
 * @email 
 * @date 2017-04-26 11:31:02
 */
public interface ETradeAcctTaskService{
	List<ETradeAcctTask> getAcctByCustomerId(Map<String, Object> map);
	ETradeAcctTask getByTaskId(Long tradeAcctTaskId);
	void updateByTaskId(ETradeAcctTask tradeAcctTask);
	void save(ETradeAcctTask tradeAcct);
}
