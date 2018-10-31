package org.ieforex.utils;

import java.math.BigDecimal;
import java.util.Date;

import org.ieforex.entity.ECustomer;
import org.ieforex.entity.ECustomerFlowing;
import org.ieforex.entity.ECustomerFundFlowing;
import org.ieforex.entity.WAllCustomerFundFlowing;


public class CustomerUnit {
	
	//ecustomer操作流水
	public static ECustomerFlowing Flowing(ECustomer eCustomer,String type){
		//0 新增 1编辑 2删除
		ECustomerFlowing flowing = new ECustomerFlowing();
		flowing.setCustomerId(eCustomer.getCustomerId());
		flowing.setTradeName(eCustomer.getTradeName());
		flowing.setPwd(eCustomer.getPwd());
		flowing.setWithDrawPwd(eCustomer.getWithDrawPwd());
		flowing.setUserName(eCustomer.getUserName());
		flowing.setLoginPhone(eCustomer.getLoginPhone());
		flowing.setLoginIp(eCustomer.getLoginIp());
		flowing.setLoginDate(eCustomer.getLoginDate());
		flowing.setForumName(eCustomer.getForumName());
		flowing.setUserQq(eCustomer.getUserQq());
		flowing.setUserCardNo(eCustomer.getUserCardNo());
		flowing.setRebateCount(eCustomer.getRebateCount());
		flowing.setSecurityLevel(eCustomer.getSecurityLevel());
		flowing.setStatus(eCustomer.getStatus());
		flowing.setUserType(eCustomer.getUserType());
		flowing.setAmount(eCustomer.getAmount().multiply(new BigDecimal("-1")));
		flowing.setAvailable(eCustomer.getAvailable().multiply(new BigDecimal("-1")));
		flowing.setFreeze(eCustomer.getFreeze());
		flowing.setUncollected(eCustomer.getUncollected());
		flowing.setRebateAmount(eCustomer.getRebateAmount());
		flowing.setWithDrawAmount(eCustomer.getWithDrawAmount());
		flowing.setCreateUserId(eCustomer.getCreateUserId());
		flowing.setCreateDate(eCustomer.getCreatedate());
		flowing.setUpdateUserId(eCustomer.getUpdateUserId());
		flowing.setUpdateDate(eCustomer.getUpdateDate());
		flowing.setOperType(type);
		flowing.setInfoType("02");
		flowing.setOperDate(new Date());
		//flowing.setOperTaskId();
		return flowing;
	}
	//ecustomer资金流水
	public static ECustomerFundFlowing FundFlowing(WAllCustomerFundFlowing all){
		ECustomerFundFlowing fundFlowing = new ECustomerFundFlowing();
		fundFlowing.setAcctNo(all.getAcctNo());
		fundFlowing.setAmount(all.getAmount());
		fundFlowing.setCustomerId(all.getCustomerId());
		fundFlowing.setDealerName(all.getDealerName());
		fundFlowing.setDescribes(all.getDescribes());
		fundFlowing.setTradeType(all.getTradeType());
		fundFlowing.setUpdateDate(all.getUpdateDate());
		fundFlowing.setUpdateUserId(all.getUpdateUserId());
		return fundFlowing;
	}

}
