package org.ieforex.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.ieforex.dao.CustomerAttendanceMapper;
import org.ieforex.dao.ECustomerMapper;
import org.ieforex.dao.IntegralFlowingMapper;
import org.ieforex.entity.CustomerAttendance;
import org.ieforex.entity.ECustomer;
import org.ieforex.entity.IntegralFlowing;
import org.ieforex.service.CustomerAttendanceService;
import org.ieforex.utils.CustomerUtil;
import org.ieforex.utils.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 用户表
 * 
 * @author brilliance
 * @email 
 * @date 2017-04-25 15:32:48
 */
@Transactional
@Service
public class CustomerAttendanceServiceImpl implements CustomerAttendanceService{

	@Autowired
	private CustomerAttendanceMapper customerAttendanceMapper;
	@Autowired
	private ECustomerMapper customerMapper;
	@Autowired
	private IntegralFlowingMapper integralFlowingMapper;
	@Override
	public void emptyAllDay(Long customerId) {
		customerAttendanceMapper.emptyAllDay(customerId);
	}
	
	@Override
	public int updateAttendance(CustomerAttendance customerAttendance,ECustomer customer) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		int toDay = MathUtil.getDay(new Date());
		map.put("customerId", customer.getCustomerId());
		map.put("toDay", toDay);
		customerAttendanceMapper.updateAttendance(customerAttendance);//更新签到数据
		
		CustomerAttendance info = customerAttendanceMapper.getInfo(map);//获取更新后数据
		int i = CustomerUtil.addAttendance(info);//判断加多少积分
		if(i==7){
			//抽奖次数加 +1
			customer.setLotteryNumber(1L);
		}else if(i==0){
			i = 0;
		}else{
			if(i==1){
				Long integral = customerMapper.queryIntegral("3");
				i = MathUtil.parseInt((integral==null?1L:integral)+"");
			}
			//积分增加+n
			customer.setIntegral(MathUtil.parseLong(i+""));
			//添加积分流水
			IntegralFlowing integralFlowing = new IntegralFlowing();
			integralFlowing.setAmount(i);
			integralFlowing.setCustomerId(customer.getCustomerId());
			integralFlowing.setDescribes("每日签到");
			integralFlowing.setType("2");
			integralFlowing.setInsertDate(new Date());
			integralFlowingMapper.insert(integralFlowing);
		}
		//更新客户积分和抽奖次数
		customerMapper.updateAmount(customer);
		return i;
	}

	@Override
	public CustomerAttendance getInfo(Long customerId) {
		Map<String, Object> map = new HashMap<String, Object>();
		int toDay = MathUtil.getDay(new Date());
		map.put("customerId", customerId);
		map.put("toDay", toDay);
		return customerAttendanceMapper.getInfo(map);
	}

	@Override
	public void saveAttendance(CustomerAttendance customerAttendance) {
		customerAttendanceMapper.saveAttendance(customerAttendance);
	}
}
