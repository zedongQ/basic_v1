package org.ieforex.service;

import java.util.List;
import java.util.Map;

import org.ieforex.entity.Dealer;

public interface DealerService {
	//列表
	List<Dealer> queryList(Map<String, Object> map);
	//监管机构
	List<Map<String, String>> queryRegulator();
	List<Dealer> selectHome(Map<String, Object> map);
	//获取交易商的名称
	List<Dealer> queryEdealer();
	
	//通过id获取交易商
	Dealer queryDealerById(Long dealerId);
	//
	int queryTotal(Map<String, Object> map);
	//对比交易商
	List<Dealer> dealerCompare(Long[] dealerId);
}
