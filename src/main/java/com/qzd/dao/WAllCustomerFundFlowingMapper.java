package org.ieforex.dao;

import java.util.List;
import java.util.Map;

import org.ieforex.entity.WAllCustomerFundFlowing;
import org.ieforex.entity.WThreeMonthCustomerFundFlowing;

/**
 * 
 * 
 * @author brilliance
 * @email 
 * @date 2017-05-19 09:57:43
 */
public interface WAllCustomerFundFlowingMapper{
	int insert(WAllCustomerFundFlowing acf);
	//根据搜索条件、分页信息、排序条件来获取列表数据
	List<WAllCustomerFundFlowing> queryList(Map<String, Object> map);
	int queryTotal(Map<String, Object> map);
	List<WAllCustomerFundFlowing> getRebateById(Map<String, Object> map);
	List<WAllCustomerFundFlowing> getWithdrawalById(Map<String,Object> map);
}
