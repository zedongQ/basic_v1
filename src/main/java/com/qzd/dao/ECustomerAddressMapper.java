package org.ieforex.dao;

import java.util.List;
import java.util.Map;

import org.ieforex.entity.ECustomerAddress;
import org.springframework.stereotype.Repository;

@Repository("eCustomerAddressMapper")
public interface ECustomerAddressMapper {
	int save(ECustomerAddress eCustomerAddress);
	int deleteById(Long addressId);
	int updateById(ECustomerAddress eCustomerAddress);
	void emptyDefault(Long customerId);
	int updatePartById(Map<?,?> map);
	ECustomerAddress selectById(Long addressId);
	List<ECustomerAddress> selectByCustomerId(Long customerId);
	int addressCount(Long customerId);
}
