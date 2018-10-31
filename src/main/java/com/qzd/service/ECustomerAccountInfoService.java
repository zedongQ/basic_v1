package org.ieforex.service;

import java.util.List;
import java.util.Map;

import org.ieforex.entity.ECustomerAccountInfo;
import org.ieforex.entity.ECustomerAndInfoId;

/**
 * 客户收款帐号表
 * 
 * @author brilliance
 * @email 
 * @date 2017-05-10 13:33:08
 */
public interface ECustomerAccountInfoService{
	int insert(ECustomerAccountInfo cai);
	List<ECustomerAccountInfo> getBycustomerId(Map<String, Object> map);
	ECustomerAccountInfo getcollectionById(Map<String,Object> map);
	int queryTotal(Map<String, Object> map);
	int getTotalByCustomerId(Long customerId);
	ECustomerAccountInfo searchById(Long accountId);
	int updateById(ECustomerAccountInfo cai);
	int cancelBind(ECustomerAccountInfo cai);
	int cancelDefaultPay(Long accountId);
	int saveAccountInfor(ECustomerAccountInfo cai);
	String getTypeByAcct(Map<String, Object> map);
	List<ECustomerAndInfoId> getAccountId(Map<String, Object> map);
	ECustomerAccountInfo searchByAcct(Map<String, Object> map);
}
