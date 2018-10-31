package org.ieforex.dao;

import java.util.List;
import java.util.Map;

import org.ieforex.entity.ECustomerAccountBasic;

/**
 * 客户收款帐号基本信息表
 * 
 * @author brilliance
 * @email 
 * @date 2017-05-10 13:33:07
 */
public interface ECustomerAccountBasicMapper{
	//添加客户基础信息
	int insert(ECustomerAccountBasic cab);
	//更新客户基础信息
	int updateByCustomerId(ECustomerAccountBasic cab);
	//获取客户基础信息
	List<ECustomerAccountBasic> searchByCustomerId(Long customerId);
	//根据客户id获取基础信息Id
	Map<String, Object> getBacicIdbyCustomerId(Long customerId);
}
