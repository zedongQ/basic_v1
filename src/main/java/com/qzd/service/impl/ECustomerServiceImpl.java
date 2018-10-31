package org.ieforex.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.ieforex.dao.CustomerAttendanceMapper;
import org.ieforex.dao.ECustomerAccountBasicMapper;
import org.ieforex.dao.ECustomerFundFlowingMapper;
import org.ieforex.dao.ECustomerMapper;
import org.ieforex.dao.ExternalRegisterChannelMapper;
import org.ieforex.dao.ExternalRegisterMapper;
import org.ieforex.dao.HomePageMapper;
import org.ieforex.dao.IntegralFlowingMapper;
import org.ieforex.dao.WAllCustomerFundFlowingMapper;
import org.ieforex.entity.CustomerAttendance;
import org.ieforex.entity.ECustomer;
import org.ieforex.entity.ECustomerAccountBasic;
import org.ieforex.entity.ECustomerFundFlowing;
import org.ieforex.entity.ExternalRegisterChannel;
import org.ieforex.entity.HomePage;
import org.ieforex.entity.IntegralFlowing;
import org.ieforex.entity.WAllCustomerFundFlowing;
import org.ieforex.service.ECustomerService;
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
@Service("eCustomerService")
public class ECustomerServiceImpl implements ECustomerService{

	@Autowired
	private ECustomerMapper mapper;
	@Autowired
	private CustomerAttendanceMapper attendanceMapper; 
	@Autowired
	private ECustomerAccountBasicMapper customerAccountBasicMapper; 
	@Autowired
	private IntegralFlowingMapper integralFlowingMapper;
	@Autowired
	private HomePageMapper homePageMapper;
	@Autowired
	private ExternalRegisterMapper externalRegisterMapper;
	@Autowired
	private ExternalRegisterChannelMapper externalRegisterChannelMapper;
	@Autowired
	private WAllCustomerFundFlowingMapper allCustomerFundFlowingMapper;
	@Autowired
	private ECustomerFundFlowingMapper customerFundFlowingMapper ;
	
	@Override
	public int insert(ECustomer customer, boolean isExtern) {
		String loginPhone = customer.getLoginPhone();
		//注册奖励积分
		Long integral = mapper.queryIntegral("6");
		customer.setIntegral(integral);
		if(customer.getInviteId()!=null&&customer.getLoginPhone()!=null){
			Long  integrals= mapper.queryIntegral("4");
			//手机注册邀请奖励积分
			ECustomer eCustomer=new ECustomer();
			eCustomer.setCustomerId(customer.getInviteId());
			eCustomer.setIntegral(integrals);
			eCustomer.setUpdateDate(new Date());
			//流水
			IntegralFlowing integralFlowing=new IntegralFlowing();
			integralFlowing.setCustomerId(customer.getInviteId());
			integralFlowing.setAmount(Integer.parseInt(integrals.toString()));
			integralFlowing.setType("3");
			integralFlowing.setDescribes(integralFlowing.getTypeName());
			integralFlowing.setInsertDate(new Date());
			mapper.updateAmount(eCustomer);
			integralFlowingMapper.insert(integralFlowing);
		}
		
		int flag=mapper.insert(customer);
		
		//渠道有没有注册奖励
		ExternalRegisterChannel incentiveChannel = externalRegisterChannelMapper.getIncentiveChannel(customer.getUserType());
		BigDecimal incentiveAmount = new BigDecimal("0");
		if(incentiveChannel!=null) {
			ECustomer eCustomer=new ECustomer();
			Date d = new Date();
			eCustomer.setCustomerId(customer.getCustomerId());
			eCustomer.setUpdateDate(d);
			//注册随机奖励(0。5-1.5美元)
			Random r = new Random();
			int randomIncentive = r.nextInt(100)+50;
			double randomIncentiveDouble = Double.parseDouble(randomIncentive+"");
			incentiveAmount = new BigDecimal(MathUtil.parseStr(randomIncentiveDouble/100));
			eCustomer.setAmount(incentiveAmount);
			eCustomer.setAvailable(incentiveAmount);
			mapper.updateAmount(eCustomer);
			
			//资金流水
			ECustomerFundFlowing cff = new ECustomerFundFlowing();
			cff.setCustomerId(customer.getCustomerId());
			cff.setAmount(incentiveAmount);
			cff.setDescribes("注册奖励");
			cff.setTradeType("5");//0返现1提现2补录/奖励3绑定4推荐费5注册奖励
			cff.setUpdateDate(d);
			customerFundFlowingMapper.insert(cff);
			
			/**客户资金流水*/
			WAllCustomerFundFlowing acff = new WAllCustomerFundFlowing();
			acff.setCustomerId(customer.getCustomerId());
			acff.setAmount(incentiveAmount);
			acff.setDescribes("注册奖励");
			acff.setOperateDate(d);
			acff.setTradeType("5"); //0返现1提现2补录/奖励3绑定4推荐费5注册奖励
			allCustomerFundFlowingMapper.insert(acff);
			
		}
		
		CustomerAttendance customerAttendance=new CustomerAttendance();
		customerAttendance.setCustomerId(customer.getCustomerId());
		attendanceMapper.saveAttendance(customerAttendance);

		// 刪除外部注冊用戶
		if(isExtern) {
			externalRegisterMapper.deleteByPhone(loginPhone);
		}
		return flag;
	}

	@Override
	public ECustomer queryUser(String userName) {
		return mapper.queryUser(userName);
	}

	@Override
	public ECustomer queryUserById(Long customerId) {
		return mapper.queryUserById(customerId);
	}

	@Override
	public void updatePwdOrEmail(ECustomer customer) {
		mapper.updatePwdOrEmail(customer);
	}

	@Override
	public Integer repeatEmail(String email) {
		return mapper.repeatEmail(email);
	}

	@Override
	public Integer repeatName(String username) {
		return mapper.repeatName(username);
	}

	@Override
	public void updateStatus(Map<String, Object> map) {
		Long inviteId =(Long) map.get("inviteId");
		if(null!=inviteId){
			Long  integrals= mapper.queryIntegral("4");
			//手机注册邀请奖励积分
			ECustomer eCustomer=new ECustomer();
			eCustomer.setCustomerId(inviteId);
			eCustomer.setIntegral(integrals);
			eCustomer.setUpdateDate(new Date());
			//流水
			IntegralFlowing integralFlowing=new IntegralFlowing();
			integralFlowing.setCustomerId(inviteId);
			integralFlowing.setAmount(Integer.parseInt(integrals.toString()));
			integralFlowing.setType("3");
			integralFlowing.setDescribes(integralFlowing.getTypeName());
			integralFlowing.setInsertDate(new Date());
			mapper.updateAmount(eCustomer);
			integralFlowingMapper.insert(integralFlowing);
		}
		mapper.updateStatus(map);
	}

	@Override
	public void updateLoginCount(Map<String, Object> map) {
		mapper.updateLoginCount(map);
	}

	@Override
	public void updateVerifyCode(ECustomer customer) {
		mapper.updateVerifyCode(customer);
	}

	@Override
	public Integer repeatPhone(String phone) {
		return mapper.repeatPhone(phone);
	}

	@Override
	public void updateCode(Map<String, Object> map) {
		
		mapper.updateCode(map);
	}

	@Override
	public void updateAmount(ECustomer customer) {
		mapper.updateAmount(customer);
	}

	@Override
	public int msgUnReadNum(Long customerId) {
		return mapper.msgUnReadNum(customerId);
	}

	@Override
	public void updateBasic(ECustomer customer) {
		mapper.updateBasic(customer);
	}
	//实名认证
	public void updateBasicByShiMing(ECustomer customer){
		ECustomerAccountBasic cab = new ECustomerAccountBasic();
		cab.setCustomerId(customer.getCustomerId());
		cab.setAcctName(customer.getUserName());
		cab.setCardNo(customer.getUserCardNo());
		cab.setCardNoType("0");
		List<ECustomerAccountBasic> li = customerAccountBasicMapper.searchByCustomerId(customer.getCustomerId());
		if(li.size()>0){
			customerAccountBasicMapper.updateByCustomerId(cab);
		}else{
			cab.setDataSource("0");
			cab.setDataStatus("0");
			cab.setIsDefault("1");
			customerAccountBasicMapper.insert(cab);
		}
		mapper.updateBasic(customer);
	}

	@Override
	public Map<String, Object> inviteAllCounts(Long customerId) {
		return mapper.inviteAllCounts(customerId);
	}

	@Override
	public ECustomer queryInviteInfo(Long customerId) {
		return mapper.queryInviteInfo(customerId);
	}

	@Override
	public List<Map<String, Object>> queryInviteAll() {
		return mapper.queryInviteAll();
	}

	@Override
	public Long queryIntegral(String type) {
		return mapper.queryIntegral(type);
	}

	@Override
	public List<HomePage> adviteList(String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("imageType", type);//个人中心底部广告
		map.put("status", "1");//1 已发布
		map.put("offset", 0);
		map.put("limit", 5);
		return homePageMapper.selectByType(map);
	}

	@Override
	public List<ECustomer> queryInviteWithAmount() {
		return mapper.queryInviteWithAmount();
	}

	@Override
	public void updateFeeRate(Map<String, Object> map) {
		mapper.updateFeeRate(map);
	}
}
