package org.ieforex.service;

import java.util.List;
import java.util.Map;

import org.ieforex.entity.ECustomerAddress;


/**
 * 
 * 
 * @author zero
 * @email 15638559970
 * @date 2017-08-28 16:56:56
 */
public interface ECustomerAddressService{
	int save(ECustomerAddress eCustomerAddress);
	int deleteById(Long addressId);
	int updateById(ECustomerAddress eCustomerAddress);
	void emptyDefault(Long customerId);
	int updatePartById(Map<String,Object> map);
	ECustomerAddress selectById(Long addressId);
	List<ECustomerAddress> selectByCustomerId(Long customerId);
}
