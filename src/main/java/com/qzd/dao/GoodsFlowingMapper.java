package org.ieforex.dao;

import org.ieforex.entity.GoodsFlowing;
import org.springframework.stereotype.Repository;

@Repository("goodsFlowingMapper")
public interface GoodsFlowingMapper {
	int insert(GoodsFlowing goodsFlowing);
	GoodsFlowing selectById(Long flowingId);
}
