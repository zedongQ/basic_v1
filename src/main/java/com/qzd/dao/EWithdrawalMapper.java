package org.ieforex.dao;

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
public interface EWithdrawalMapper{
	
	//获取当前用户的当日提现次数
	int getWithdrawalNumOfTheDay(Long customerId);
	
	//是否连续三天提现（>0是,<0否）
	int getWithdrawalForThreeDays(Long customerId);
	
	//插入数据返回withdrawalId
	Long insertWithResult(EWithdrawal eWithdrawal);
	
	int getWithdrawalNo(String withdrawalNo);
}
