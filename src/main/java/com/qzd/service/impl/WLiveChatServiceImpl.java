package org.ieforex.service.impl;

import java.util.List;

import org.ieforex.dao.WLiveChatMapper;
import org.ieforex.entity.WLiveChat;
import org.ieforex.service.WLiveChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("wLiveChatService")
public class WLiveChatServiceImpl implements WLiveChatService {
	
	@Autowired
	private WLiveChatMapper wLiveChatMapper;
	
	@Override
//	@Cacheable(value="cache180",key="'liveChat'")  
	public List<WLiveChat> queryList(String type) {
		return wLiveChatMapper.queryList(type);
	}

}
