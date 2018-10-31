package org.ieforex.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface RebateResultMapper {
	int selectCount(Long customerId);
}
