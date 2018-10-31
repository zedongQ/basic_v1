package org.ieforex.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ieforex.dao.WCustomerMessageMapper;
import org.ieforex.entity.WCustomerMessage;
import org.ieforex.service.WCustomerMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("wCustomerMessageService")
public class WCustomerMessageServiceImpl implements WCustomerMessageService {
	@Autowired
	private WCustomerMessageMapper mapper;
	
	@Override
	public List<WCustomerMessage> queryList(Map<String, Object> map) {
		return mapper.queryList(map);
	}

	@Override
	public int queryTotal(Long customerId) {
		return mapper.queryTotal(customerId);
	}

	@Override
	public void changRead(Map<String, Object> map) {
		mapper.changRead(map);
	}
	
	@Override
	public void deleteById(Long[] messageIds,Long customerId) {
		Map<String,Object> map = new HashMap<String, Object>(); 
		if(messageIds.length>0){
			for (Long messageId : messageIds) {
				map.put("customerId", customerId);
				map.put("messageId", messageId);
				mapper.deleteById(map);
			}
		}
	}
}
