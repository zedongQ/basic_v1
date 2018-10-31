package org.ieforex.dao;

import java.util.List;
import java.util.Map;

import org.ieforex.entity.Goods;
import org.springframework.stereotype.Repository;

@Repository("goodsMapper")
public interface GoodsMapper {
	List<Goods> select(Map<String,Object> map);
	Goods selectById(Long goodId);
	int updateNumber(Goods goods);
}
