package org.ieforex.service.impl;

import java.util.List;
import java.util.Map;

import org.ieforex.dao.ECustomerAccountBasicMapper;
import org.ieforex.dao.ECustomerAccountInfoMapper;
import org.ieforex.dao.ETradeAcctMapper;
import org.ieforex.entity.ECustomerAccountBasic;
import org.ieforex.service.ECustomerAccountBasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("eCustomerAccountBasicService")
public class ECustomerAccountBasicServiceImpl implements ECustomerAccountBasicService {

	@Autowired
	private ECustomerAccountBasicMapper mapper;
	@Autowired
	private ETradeAcctMapper acctMapper;
	@Autowired
	private ECustomerAccountInfoMapper customerAccountInfoMapper;
	
	@Override
	public ECustomerAccountBasic searchByCustomerId(Long customerId) {
		List<ECustomerAccountBasic> list = mapper.searchByCustomerId(customerId);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Map<String, Object> getBacicIdbyCustomerId(Long customerId) {
		return mapper.getBacicIdbyCustomerId(customerId);
	}

	@Override
	public int insert(ECustomerAccountBasic cab) {
		return mapper.insert(cab);
	}

}
