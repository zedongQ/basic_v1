package org.ieforex.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ieforex.dao.ECustomerAccountBasicMapper;
import org.ieforex.dao.ECustomerAccountInfoMapper;
import org.ieforex.entity.ECustomerAccountBasic;
import org.ieforex.entity.ECustomerAccountInfo;
import org.ieforex.entity.ECustomerAndInfoId;
import org.ieforex.service.ECustomerAccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("eCustomerAccountInfoService")
public class ECustomerAccountInfoServiceImpl implements ECustomerAccountInfoService{

	@Autowired
	private ECustomerAccountBasicMapper accountBasicMapper;
	@Autowired
	private ECustomerAccountInfoMapper mapper;
	
	@Override
	public int queryTotal(Map<String, Object> map) {
		return mapper.queryTotal(map);
	}
	
	@Override
	public ECustomerAccountInfo searchById(Long accountId){
		return mapper.searchById(accountId);
	}

	@Override
	public int updateById(ECustomerAccountInfo cai) {
		ECustomerAccountBasic cab = new ECustomerAccountBasic();
		cab.setCustomerId(cai.getCustomerId());
		cab.setUpdateUserId(cai.getCustomerId());
		cab.setUpdateDate(new Date());
		if("0".equals(cai.getType())||"2".equals(cai.getType())){
			 cab.setIsDefault("1");
		}else if("1".equals(cai.getType())||"3".equals(cai.getType())||"4".equals(cai.getType())){
			cab.setIsDefault("0");
		}
		accountBasicMapper.updateByCustomerId(cab);
		return mapper.updateById(cai);
	}

	@Override
	public int cancelBind(ECustomerAccountInfo cai) {
		Long customerId = cai.getCustomerId();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> basicMap = accountBasicMapper.getBacicIdbyCustomerId(customerId);
		if (basicMap != null && basicMap.size() > 0) {
			map.put("basicId", basicMap.get("basicId"));
		}
		map.put("customerId", customerId);
		cai.setCustomerId(customerId);
		int i = mapper.cancelBind(cai);
		//解绑后，如果该用户账号没有默认账号或者没有交易账号，更新用户基础信息中的默认返现方式
		List<ECustomerAccountInfo> accountInfos = mapper.getBycustomerId(map);
		if(accountInfos.size()<0){
			ECustomerAccountBasic accountBasic = new ECustomerAccountBasic();
			accountBasic.setCustomerId(cai.getCustomerId());
			accountBasic.setIsDefault(null);
			accountBasic.setUpdateDate(new Date());
			accountBasic.setUpdateUserId(cai.getCustomerId());
			accountBasicMapper.updateByCustomerId(accountBasic);
		}else if(accountInfos.size()>0){
			String isDefault = accountInfos.get(0).getIsDefault();
			if(!"1".equals(isDefault)){
				ECustomerAccountBasic accountBasic = new ECustomerAccountBasic();
				accountBasic.setCustomerId(cai.getCustomerId());
				accountBasic.setIsDefault("3");//3表示存在账号却无默认账号
				accountBasic.setUpdateDate(new Date());
				accountBasic.setUpdateUserId(cai.getCustomerId());
				accountBasicMapper.updateByCustomerId(accountBasic);
			}
		}
		return i;
	}

	@Override
	public int saveAccountInfor(ECustomerAccountInfo cai) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("accountAcct", cai.getAccountAcct());
		map.put("type", cai.getType());
		List<ECustomerAndInfoId> list = mapper.getAccountId(map);//判断账号是否已经存在
		if(list.size() > 0 && list.get(0).getBasicId()!=null&&list.get(0).getBasicId()!=0){
			if("0".equals(list.get(0).getDataStatus())){
				return 0;
			}
		}
		if(cai.getIsDefault() != null && "1".equals(cai.getIsDefault())){//是否重新默认收款方式
			mapper.cancelDefaultPay(cai.getCustomerId());
		}else{
			cai.setIsDefault("3");
		}
		cai.setCreateDate(new Date());
		List<ECustomerAccountBasic> li = accountBasicMapper.searchByCustomerId(cai.getCustomerId());
		if(li.size()==0){
			ECustomerAccountBasic accountBasic = new ECustomerAccountBasic();
			accountBasic.setCustomerId(cai.getCustomerId());
			accountBasic.setAccountPhone(cai.getAccountPhone());
			accountBasic.setAcctName(cai.getAcctName());
			accountBasic.setCardNoType("0");
			accountBasic.setCardNo(cai.getCardNo());
			accountBasic.setNationality(cai.getNationality());
			accountBasic.setCreateUserId(cai.getCustomerId());
			accountBasic.setCreateDate(new Date());
			if(cai.getIsDefault() != null && "1".equals(cai.getIsDefault())){//是否重新默认收款方式
				if("0".equals(cai.getType())||"2".equals(cai.getType())){
					accountBasic.setIsDefault("1");
				}else if("1".equals(cai.getType())||"3".equals(cai.getType())||"4".equals(cai.getType())){
					accountBasic.setIsDefault("0");
				}
			}else {
				accountBasic.setIsDefault("3");
			}
			accountBasic.setEngName(cai.getEngName());
			accountBasic.setEngSurname(cai.getEngSurname());
			accountBasic.setDataSource("0");
			accountBasic.setDataStatus("0");
			accountBasicMapper.insert(accountBasic);
			li = accountBasicMapper.searchByCustomerId(cai.getCustomerId());
		}else if(li.get(0).getEngName()==null && li.get(0).getEngSurname()==null){
			ECustomerAccountBasic accountBasic = new ECustomerAccountBasic();
			accountBasic.setCustomerId(cai.getCustomerId());
			accountBasic.setEngName(cai.getEngName());
			accountBasic.setEngSurname(cai.getEngSurname());
			if(cai.getIsDefault() != null && "1".equals(cai.getIsDefault())){//是否重新默认收款方式
				if("0".equals(cai.getType())||"2".equals(cai.getType())){
					accountBasic.setIsDefault("1");
				}else if("1".equals(cai.getType())||"3".equals(cai.getType())||"4".equals(cai.getType())){
					accountBasic.setIsDefault("0");
				}
			}else {
				accountBasic.setIsDefault("3");
			}
			accountBasic.setUpdateDate(new Date());
			accountBasic.setUpdateUserId(cai.getCustomerId());
			accountBasicMapper.updateByCustomerId(accountBasic);
		}else if(li.get(0).getEngName()!=null && li.get(0).getEngSurname()!=null){
			ECustomerAccountBasic accountBasic = new ECustomerAccountBasic();
			accountBasic.setCustomerId(cai.getCustomerId());
			if(cai.getIsDefault() != null && "1".equals(cai.getIsDefault())){//是否重新默认收款方式
				if("0".equals(cai.getType())||"2".equals(cai.getType())){
					accountBasic.setIsDefault("1");
				}else if("1".equals(cai.getType())||"3".equals(cai.getType())||"4".equals(cai.getType())){
					accountBasic.setIsDefault("0");
				}
			}else {
				accountBasic.setIsDefault("3");
			}
			accountBasicMapper.updateByCustomerId(accountBasic);
		}
		cai.setBasicId(li.get(0).getBasicId());
		if(list.size() > 0 && (list.get(0).getBasicId()==null||list.get(0).getBasicId()==0)){
			//if(list.get(0).getCustomerId()==null||list.get(0).getCustomerId()==0){
				cai.setAccountId(list.get(0).getAccountId());
				cai.setDataStatus("0");
				return mapper.updateById(cai);
			//}
		}
		if(list.size() > 0&&"1".equals(list.get(0).getDataStatus())){
			cai.setAccountId(list.get(0).getAccountId());
			cai.setDataStatus("0");
			return mapper.updateById(cai);
		}
		return insert(cai);
	}


	@Override
	public List<ECustomerAccountInfo> getBycustomerId(Map<String, Object> map) {
		return mapper.getBycustomerId(map);
	}

	@Override
	public String getTypeByAcct(Map<String, Object> map){
		return mapper.getTypeByAcct(map);
	}

	@Override
	public List<ECustomerAndInfoId> getAccountId(Map<String, Object> map) {
		return mapper.getAccountId(map);
	}

	@Override
	public ECustomerAccountInfo searchByAcct(Map<String, Object> map) {
		return mapper.searchByAcct(map);
	}

	@Override
	public int cancelDefaultPay(Long accountId) {
		return mapper.cancelDefaultPay(accountId);
	}

	@Override
	public ECustomerAccountInfo getcollectionById(Map<String,Object> map) {
		return mapper.getcollectionById(map);
	}

	@Override
	public int insert(ECustomerAccountInfo cai) {
		return mapper.insert(cai);
	}

	@Override
	public int getTotalByCustomerId(Long customerId) {
		return mapper.getTotalByCustomerId(customerId);
	}
}
