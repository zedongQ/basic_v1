package org.ieforex.utils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

import org.ieforex.entity.CustomerAttendance;
import org.ieforex.entity.ECustomer;

public class CustomerUtil {
	public static ECustomer getCustomer(ECustomer customer){
		if(customer!=null){
			if(customer.getAmount()==null){
				customer.setAmount(MathUtil.parseBigDecimal(new BigDecimal(0.00),2,true));
			}else{
				customer.setAmount(MathUtil.parseBigDecimal(customer.getAmount(),2,true));
			}
			if(customer.getInviteAmount()==null){
				customer.setInviteAmount(MathUtil.parseBigDecimal(new BigDecimal(0.00),2,true));
			}else{
				customer.setInviteAmount(MathUtil.parseBigDecimal(customer.getInviteAmount(),2,true));
			}
			if(customer.getRebateAmountSum()==null){
				customer.setRebateAmountSum(MathUtil.parseBigDecimal(new BigDecimal(0.00),2,true));
			}else{
				customer.setRebateAmountSum(MathUtil.parseBigDecimal(customer.getRebateAmountSum(),2,true));
			}
			
			if(customer.getWithdrawlAmountSum()==null){
				customer.setWithdrawlAmountSum(MathUtil.parseBigDecimal(new BigDecimal(0.00),2,true));
			}else{
				customer.setWithdrawlAmountSum(MathUtil.parseBigDecimal(customer.getWithdrawlAmountSum(),2,true));
			}
			
			if(customer.getIncomeAmountSum()==null){
				customer.setIncomeAmountSum(MathUtil.parseBigDecimal(new BigDecimal(0.00),2,true));
			}else{
				customer.setIncomeAmountSum(MathUtil.parseBigDecimal(customer.getIncomeAmountSum(),2,true));
			}
			if(customer.getUserName()!=null && !"".equals(customer.getUserName()) && customer.getUserCardNo()!=null && !"".equals(customer.getUserCardNo())){
				customer.setIsName("yes");
			}
			if(customer.getAvailable()==null){
				customer.setAvailable(MathUtil.parseBigDecimal(new BigDecimal(0.00),2,true));
			}else{
				customer.setAvailable(MathUtil.parseBigDecimal(customer.getAvailable(),2,true));
			}
			if(customer.getRebateAmount()==null){
				customer.setRebateAmount(MathUtil.parseBigDecimal(new BigDecimal(0.00),2,true));
			}else{
				customer.setRebateAmount(MathUtil.parseBigDecimal(customer.getRebateAmount(),2,true));
			}
			if(customer.getUncollected()==null){
				customer.setUncollected(MathUtil.parseBigDecimal(new BigDecimal(0.00),2,true));
			}else{
				customer.setUncollected(MathUtil.parseBigDecimal(customer.getUncollected(),2,true));
			}
			if(customer.getWithDrawAmount()==null){
				customer.setWithDrawAmount(MathUtil.parseBigDecimal(new BigDecimal(0.00),2,true));
			}else{
				customer.setWithDrawAmount(MathUtil.parseBigDecimal(customer.getWithDrawAmount(),2,true));
			}
			if(customer.getTradeName()!=null){
				customer.setTradeName(charEcordingFilterUtil.specialCharFilter(customer.getTradeName()));
			}
		}
		return customer;
	}
	
	public static ECustomer getCustomerForPage(ECustomer customerInfo) {//页面需要用到的客户信息
		ECustomer eCustomer = new ECustomer();
		if(customerInfo!=null){
			eCustomer.setCustomerId(customerInfo.getCustomerId());
			eCustomer.setAvailable(customerInfo.getAvailable());
			eCustomer.setUncollected(customerInfo.getUncollected());
			eCustomer.setRebateAmount(customerInfo.getRebateAmount());
			eCustomer.setLoginPhone(customerInfo.getLoginPhoneView());
			eCustomer.setLoginEmail(customerInfo.getLoginEmailView());
			eCustomer.setWithDrawAmount(customerInfo.getWithDrawAmount());
			eCustomer.setIntegral(customerInfo.getIntegral());
			eCustomer.setInviteAmount(customerInfo.getInviteAmount());
			eCustomer.setHeadImg(customerInfo.getHeadImg());
			eCustomer.setTradeName(customerInfo.getTradeName());
			eCustomer.setHeadImg(customerInfo.getHeadImg());
			eCustomer.setWithdrawlCounts(customerInfo.getWithdrawlCounts());
			eCustomer.setIsName(customerInfo.getIsName());
			eCustomer.setWithdrawlCounts(customerInfo.getWithdrawlCounts());
		}
		return eCustomer;
	}
	
	
	
	// 更新签到
	public static CustomerAttendance getAttendanceDay(int day,Long customerId){
		CustomerAttendance customerAttendance = new CustomerAttendance();
		customerAttendance.setCustomerId(customerId);
		customerAttendance.setAttendancdDate(new Date());
		switch (day) {
			case 0:{
				customerAttendance.setSunday("7");
				break;
			}
			case 1:{
				customerAttendance.setMonday("1");
				break;
			}
			case 2:{
				customerAttendance.setTuesday("2");
				break;
			}
			case 3:{
				customerAttendance.setWednesday("3");
				break;
			}
			case 4:{
				customerAttendance.setThursday("4");
				break;
			}
			case 5:{
				customerAttendance.setFriday("5");
				break;
			}
			case 6:{
				customerAttendance.setSaturday("6");
				break;
			}
		}
		return customerAttendance;
	}
	
	
	//判断签到奖励
	public static int addAttendance(CustomerAttendance customerAttendance){
		
		if(customerAttendance!=null){
			String allQiandao = customerAttendance.getHasAttendaned();
			if("1234567".equals(allQiandao)){
				return 7;
			}else if("0234567".equals(allQiandao)||"123456".equals(allQiandao)||"1234507".equals(allQiandao)){
				Random r = new Random();
				return  20 + r.nextInt(31);
			}else{
				return 1;
			}
		}
		return 0;
	}
}
