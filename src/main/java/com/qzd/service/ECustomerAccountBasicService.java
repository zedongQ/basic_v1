package org.ieforex.service;


import java.util.Map;

import org.ieforex.entity.ECustomerAccountBasic;

/**
 * 客户收款帐号基本信息表
 * 
 * @author brilliance
 * @email 
 * @date 2017-05-10 13:33:07
 */
public interface ECustomerAccountBasicService{
	int insert(ECustomerAccountBasic cab);
	ECustomerAccountBasic searchByCustomerId(Long customerId);
	Map<String, Object> getBacicIdbyCustomerId(Long customerId);
}
