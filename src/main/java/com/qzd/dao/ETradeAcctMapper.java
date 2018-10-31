package org.ieforex.dao;

import java.util.List;
import java.util.Map;

import org.ieforex.entity.ETradeAcct;


/**
 * 交易账号表
 * 
 * @author brilliance
 * @email 
 * @date 2017-06-01 16:55:09
 */
public interface ETradeAcctMapper{
	//根据搜索条件、分页信息、排序条件来获取列表数据
	List<ETradeAcct> queryList(Map<String, Object> map);
	int queryTotal(Map<String, Object> map);
	List<ETradeAcct> getAcctByCustomerId(Map<String, Object> map);
	int selectCount(Long customerId);
	// 解绑
	int cancelBindAcctNo(Map<String, Object> map);
	// 账号是否已经被绑定
	List<ETradeAcct> isExistAcctNo(Map<String, Object> map);
	
	List<ETradeAcct> getIsSameNameOrIsAgency(Long customerId);
}
