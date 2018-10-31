package org.ieforex.service;



import java.util.List;
import java.util.Map;

import org.ieforex.entity.Dealer;
import org.ieforex.entity.WPromotionalDiscount;

public interface WPromotionalDiscountService {
	List<WPromotionalDiscount> queryTop();
	List<Dealer> dealerList();
	int selectCount();
	WPromotionalDiscount selectById(Long id);
	List<WPromotionalDiscount> promoteList(Map<String, Object> map);
}
