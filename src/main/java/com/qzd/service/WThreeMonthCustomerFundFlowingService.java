package org.ieforex.service;


import java.util.List;
import java.util.Map;

import org.ieforex.entity.WThreeMonthCustomerFundFlowing;

/**
 * 
 * 
 * @author brilliance
 * @email 
 * @date 2017-05-19 09:57:31
 */
public interface WThreeMonthCustomerFundFlowingService{
	int insert(WThreeMonthCustomerFundFlowing tmcff);
	//根据搜索条件、分页信息、排序条件来获取列表数据
	List<WThreeMonthCustomerFundFlowing> queryList(Map<String, Object> map);
	int queryTotal(Map<String, Object> map);
	WThreeMonthCustomerFundFlowing getRebateById(Long flowingId);
	WThreeMonthCustomerFundFlowing getWithdrawalById(Map<String,Object> map);
}
