package org.ieforex.dao;

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
public interface ETradeAcctTaskMapper{
	//根据搜索条件、分页信息、排序条件来获取列表数据
	List<ETradeAcctTask> queryList(Map<String, Object> map);
	List<ETradeAcctTask> queryByTaskId(Long tradeAcctTaskId);
	int queryTotal(Map<String, Object> map);
	List<ETradeAcctTask> getAcctByCustomerId(Map<String, Object> map);
	ETradeAcctTask getByTaskId(Long tradeAcctTaskId);
	void updateByTaskId(ETradeAcctTask tradeAcct);
	void save(ETradeAcctTask tradeAcct);
}
