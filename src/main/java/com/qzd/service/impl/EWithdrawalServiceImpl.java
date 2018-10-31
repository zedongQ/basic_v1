package org.ieforex.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import org.ieforex.dao.ECustomerFlowingMapper;
import org.ieforex.dao.ECustomerMapper;
import org.ieforex.dao.EWithdrawalMapper;
import org.ieforex.dao.WAllCustomerFundFlowingMapper;
import org.ieforex.dao.WThreeMonthCustomerFundFlowingMapper;
import org.ieforex.entity.ECustomer;
import org.ieforex.entity.ECustomerFlowing;
import org.ieforex.entity.EWithdrawal;
import org.ieforex.entity.WAllCustomerFundFlowing;
import org.ieforex.entity.WThreeMonthCustomerFundFlowing;
import org.ieforex.service.EWithdrawalService;
import org.ieforex.utils.CustomerUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service("eWithdrawalService")
public class EWithdrawalServiceImpl implements EWithdrawalService {
	@Autowired
	private ECustomerMapper eCustomerMapper;
	@Autowired
	private WThreeMonthCustomerFundFlowingMapper wThreeMonthCustomerFundFlowingMapper;
	@Autowired
	private WAllCustomerFundFlowingMapper wAllCustomerFundFlowingMapper;
	@Autowired
	private ECustomerFlowingMapper eCustomerFlowingMapper;
	@Autowired
	private EWithdrawalMapper mapper;
	
	//获取当前用户的当日提现次数
	@Override
	public int getWithdrawalNumOfTheDay(Long customerId){
		return mapper.getWithdrawalNumOfTheDay(customerId);
	}
	
	//是否连续三天提现（>0是,<0否）
	@Override
	public int getWithdrawalForThreeDays(Long customerId){
		return mapper.getWithdrawalForThreeDays(customerId);
	}
	
	//插入数据返回withdrawalId
	@Override
	public Long insertWithResult(EWithdrawal eWithdrawal){
		mapper.insertWithResult(eWithdrawal);
		return eWithdrawal.getWithdrawalId();
	}

	/**
	 * 新增提现申请表 EWithdrawal
	 * 同时更新客户信息表 ECustomer
	 * 新增客户信息变更流水表 ECustomerFlowing
	 * 新增近三月客户资金流水表 WThreeMonthCustomerFundFlowing
	 * 新增客户资金流水表 WAllCustomerFundFlowing
	 * @param eWithdrawal
	 * @return
	 */
	@Override
	public EWithdrawal insert(EWithdrawal eWithdrawal){
		// 设置提现流水号
		String withdrawalNo = eWithdrawal.getWithdrawalNo();
		int withdrawalNo2 = mapper.getWithdrawalNo(withdrawalNo);
		String withdrawalNo3 = new DecimalFormat("00000000").format(withdrawalNo2);
		withdrawalNo = withdrawalNo+withdrawalNo3;
		eWithdrawal.setWithdrawalNo(withdrawalNo);
		mapper.insertWithResult(eWithdrawal);
		Long withdrawalId = eWithdrawal.getWithdrawalId();
		Date d = eWithdrawal.getCreateDate();
		Long userId = eWithdrawal.getCreateUserId(); 
		BigDecimal amount = eWithdrawal.getWithdrawalAmount();
		String flowingNo = eWithdrawal.getWithdrawalNo();
		
		//更新客户信息表
		Long customerId = eWithdrawal.getCustomerId();
		
		ECustomer customer = eCustomerMapper.queryUserById(eWithdrawal.getCustomerId());
		customer.setCustomerId(customerId);
		customer.setAmount(new BigDecimal("0"));
		customer.setAvailable(amount.multiply(new BigDecimal("-1")));
		customer.setFreeze(amount);
		customer.setUpdateDate(d);
		customer.setUpdateUserId(userId);	
		customer.setLoginIp(eWithdrawal.getLoginIp());
		customer.setLoginDate(eWithdrawal.getLoginTime());
		
		//新增客户信息变更流水
		ECustomerFlowing flow = CustomerUnit.Flowing(customer, "0");
		String way = eWithdrawal.getWithdrawalWay();
		String acctNo = "";
		//新增客户All资金流水表
		WAllCustomerFundFlowing all = new WAllCustomerFundFlowing();
		all.setCustomerId(customerId);
		all.setOperateDate(d);
		all.setOperateUserId(userId);
		all.setFlowingNo(flowingNo);
		all.setPayeeName(eWithdrawal.getPayeeName());
		all.setWithdrawalWay(eWithdrawal.getWithdrawalWay());
		all.setWithdrawalFee(eWithdrawal.getWithdrawalFee());
		all.setWithdrawalCurrency(eWithdrawal.getWithdrawalCurrency());
		all.setObjectId(withdrawalId);
		if("0".equals(way)){
			acctNo = eWithdrawal.getPayeeBankAcctNo().replaceAll("(\\d{4})\\d+(\\d{4})","$1***********$2");
			all.setDescribes(eWithdrawal.getPayeeBankName());
			all.setAmount(amount);
			all.setWithdrawalAmountCurrency(eWithdrawal.getWithdrawalAmountCurrency());
		}else if("1".equals(way)){
			acctNo = eWithdrawal.getPayeeTtBankAcctNo().replaceAll("(\\d{4})\\d+(\\d{4})","$1***********$2");
			all.setDescribes("美元电汇");
			all.setAmount(amount);
			all.setWithdrawalAmountCurrency(eWithdrawal.getWithdrawalAmountCurrency());
		}else if("2".equals(way)){
			acctNo = eWithdrawal.getPayeeAlipayAcctNo();
			if(acctNo.contains("@")){
				acctNo = acctNo.replaceAll("(\\w?)(\\w+)(\\w)(@\\w+\\.[a-z]+(\\.[a-z]+)?)", "$1****$3$4");
			}else{
				acctNo = acctNo.replaceAll("(\\d{4})\\d+(\\d{4})","$1***********$2");
			}
			all.setDescribes("支付宝");
			all.setAmount(amount);
			all.setWithdrawalAmountCurrency(eWithdrawal.getWithdrawalAmountCurrency());
		}else if("3".equals(way)){
			acctNo = eWithdrawal.getPayeeMbAcctNo();
			if(acctNo.contains("@")){
				acctNo = acctNo.replaceAll("(\\w?)(\\w+)(\\w)(@\\w+\\.[a-z]+(\\.[a-z]+)?)", "$1****$3$4");
			}else{
				acctNo = acctNo.replaceAll("(\\d{4})\\d+(\\d{4})","$1***********$2");
			}
			all.setDescribes("Skrill账号");
			all.setWithdrawalAmountCurrency(eWithdrawal.getWithdrawalAmountCurrency());
			all.setAmount(amount);
		}else if("4".equals(way)){
			acctNo = eWithdrawal.getPayeePaypalNo();
			if(acctNo.contains("@")){
				acctNo = acctNo.replaceAll("(\\w?)(\\w+)(\\w)(@\\w+\\.[a-z]+(\\.[a-z]+)?)", "$1****$3$4");
			}else{
				acctNo = acctNo.replaceAll("(\\d{4})\\d+(\\d{4})","$1***********$2");
			}
			all.setDescribes("贝宝账号");
			all.setAmount(amount);
			all.setWithdrawalAmountCurrency(eWithdrawal.getWithdrawalAmountCurrency());
		}
		all.setAcctNo(acctNo);
		all.setTradeType("1");//提现
		all.setStatus("2");//处理中
		all.setObjectId(eWithdrawal.getWithdrawalId());
		
		//客户资金流水表
		customer.setLotteryNumber(null);
		customer.setIntegral(null);
		eCustomerMapper.updateAmount(customer);
		eCustomerFlowingMapper.save(flow);
		wAllCustomerFundFlowingMapper.insert(all);
		return eWithdrawal;
	}

	@Override
	public int getWithdrawalNo(String withdrawalNo) {
		return mapper.getWithdrawalNo(withdrawalNo);
	}
}
