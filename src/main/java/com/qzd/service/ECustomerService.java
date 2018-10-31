package org.ieforex.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.ieforex.entity.ECustomer;
import org.ieforex.entity.HomePage;

import com.alibaba.fastjson.serializer.BigDecimalCodec;


/**
 * 用户表
 * 
 * @author brilliance
 * @email 
 * @date 2017-04-25 15:32:48
 */
public interface ECustomerService {
	
	int insert(ECustomer customer, boolean isExtern);
	//获取用户的信息
	ECustomer queryUser(String userName);
	//通过ID获取用户信息
	ECustomer queryUserById(Long customerId);
	//修改密码或者邮箱
	void updatePwdOrEmail(ECustomer customer);
	//验证邮箱是否重复
	Integer repeatEmail(String email);
	//验证用户名是否重复
	Integer repeatName(String username);
	//验证手机是否重复
	Integer repeatPhone(String phone);
	//修改用户状态
	void updateStatus(Map<String, Object> map); 
	//修改登录次数
	void updateLoginCount(Map<String, Object> map); 
	//更新验证码
	void updateVerifyCode(ECustomer customer);
	//更新提现验证码
	void updateCode(Map<String,Object> map);
	//更新数据（金额、积分）--累加模式（传值就行，例：增加 1 减 -1）
	void updateAmount(ECustomer customer);
	//更新头像、昵称等信息
	void updateBasic(ECustomer customer);
	//实名认证更新信息
    void updateBasicByShiMing(ECustomer customer);
	//未读消息数量
	int msgUnReadNum(Long customerId);
	//邀请人数查询
	Map<String,Object> inviteAllCounts(Long customerId); 
	//邀请信息
	ECustomer queryInviteInfo(Long customerId);
	//邀请排行榜
	List<Map<String, Object>> queryInviteAll();
	//通过类型获取积分
	Long queryIntegral(String type);
	//广告
	List<HomePage> adviteList(String type);
	//邀请奖励排行榜
	List<ECustomer> queryInviteWithAmount();
	
	void updateFeeRate(Map<String, Object> map);
}
