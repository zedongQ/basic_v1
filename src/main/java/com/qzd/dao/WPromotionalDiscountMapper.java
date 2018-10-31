package org.ieforex.dao;


import java.util.List;
import java.util.Map;

import org.ieforex.entity.WPromotionalDiscount;
import org.springframework.stereotype.Repository;

@Repository("wPromotionalDiscountMapper")
public interface WPromotionalDiscountMapper {
	List<WPromotionalDiscount> queryTop();
	int selectCount(Map<String,Object> map);
	List<WPromotionalDiscount> select(Map<String,Object> map);
	WPromotionalDiscount selectById(Long id);
}
