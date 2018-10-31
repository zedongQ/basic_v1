package org.ieforex.dao;

import java.util.List;
import java.util.Map;

import org.ieforex.entity.WCustomerMessage;
import tk.mybatis.mapper.common.Mapper;

/**
 * 站内消息表
 * 
 * @author brilliance
 * @email 
 * @date 2017-06-16 09:58:26
 */
public interface WCustomerMessageMapper extends Mapper<WCustomerMessage> {
	//根据搜索条件、分页信息、排序条件来获取列表数据
	List<WCustomerMessage> queryList(Map<String, Object> map);
	int queryTotal(Long customerId);
	void changRead(Map<String, Object> map);
	void deleteById(Map<String, Object> map);
}