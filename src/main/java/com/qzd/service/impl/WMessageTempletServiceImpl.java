package org.ieforex.service.impl;

import org.ieforex.dao.WMessageTempletMapper;
import org.ieforex.entity.WMessageTemplet;
import org.ieforex.service.WMessageTempletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("wMessageTempletService")
public class WMessageTempletServiceImpl implements WMessageTempletService {

	@Autowired
	private WMessageTempletMapper mapper; 
	
	@Override
	public WMessageTemplet queryMessageTemplet(String templetKey) {
		// TODO Auto-generated method stub
		return mapper.queryMessageTemplet(templetKey);
	}

	
}
