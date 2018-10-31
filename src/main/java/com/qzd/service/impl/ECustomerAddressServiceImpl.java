package org.ieforex.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ieforex.service.ECustomerAddressService;
import org.ieforex.entity.ECustomerAddress;
import org.ieforex.dao.ECustomerAddressMapper;


@Service("eCustomerAddressService")
public class ECustomerAddressServiceImpl implements ECustomerAddressService {
	
	@Autowired
	private ECustomerAddressMapper mapper;
	
	@Override
	public int save(ECustomerAddress eCustomerAddress){
		
		if("1".equals(eCustomerAddress.getIsDefault())){
			mapper.emptyDefault(eCustomerAddress.getCustomerId());
		}
		return mapper.save(eCustomerAddress);
	}
	
	@Override
	public int deleteById(Long addressId){
		return mapper.deleteById(addressId);
	}
	
	@Override
	public int updateById(ECustomerAddress eCustomerAddress){
		if("1".equals(eCustomerAddress.getIsDefault())){
			mapper.emptyDefault(eCustomerAddress.getCustomerId());
		}
		return mapper.updateById(eCustomerAddress);
	}
	
	@Override
	public int updatePartById(Map<String,Object> map){
		return mapper.updatePartById(map);
	}
	
	@Override
	public ECustomerAddress selectById(Long addressId){
		return mapper.selectById(addressId);
	}

	@Override
	public List<ECustomerAddress> selectByCustomerId(Long customerId) {
		return mapper.selectByCustomerId(customerId);
	}

	@Override
	public void emptyDefault(Long customerId) {
		mapper.emptyDefault(customerId);
	}
	
}
