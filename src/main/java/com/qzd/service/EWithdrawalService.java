package org.ieforex.service;

import java.util.List;
import java.util.Map;

import org.ieforex.entity.EWithdrawal;


/**
 * 提现申请表
 * 
 * @author brilliance
 * @email 
 * @date 2017-04-26 14:02:16
 */
public interface EWithdrawalService{
	//获取当前用户的当日提现次数
	int getWithdrawalNumOfTheDay(Long customerId);
	
	//是否连续三天提现（>0是,<0否）
	int getWithdrawalForThreeDays(Long customerId);
	
	/**
	 * 新增提现申请表 EWithdrawal
	 * 同时更新客户信息表 ECustomer
	 * 新增客户信息变更流水表 ECustomerFlowing
	 * 新增近三月客户资金流水表 WThreeMonthCustomerFundFlowing
	 * 新增客户资金流水表 WAllCustomerFundFlowing
	 * @param eWithdrawal
	 * @return
	 */
	EWithdrawal insert(EWithdrawal eWithdrawal);
	
	
	//插入数据返回withdrawalId
	Long insertWithResult(EWithdrawal eWithdrawal);
	
	int getWithdrawalNo(String withdrawalNo);
}
