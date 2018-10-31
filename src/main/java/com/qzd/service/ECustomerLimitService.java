package org.ieforex.service;


import java.util.List;
import java.util.Map;

import org.ieforex.entity.ECustomerLimit;

/**
 * 用户限制表
 * 
 * @author brilliance
 * @email 
 * @date 2017-05-08 14:23:27
 */
public interface ECustomerLimitService{
	//根据搜索条件、分页信息、排序条件来获取列表数据
	List<ECustomerLimit> queryList(Map<String, Object> map);
	int queryTotal(Map<String, Object> map);
	ECustomerLimit searchById(Long customerId);
}
