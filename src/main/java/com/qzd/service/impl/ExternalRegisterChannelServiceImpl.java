package org.ieforex.service.impl;

import org.ieforex.dao.ExternalRegisterChannelMapper;
import org.ieforex.entity.ExternalRegisterChannel;
import org.ieforex.service.ExternalRegisterChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 外部注冊接口
 */
@Transactional
@Service("externalRegisterChannelService")
public class ExternalRegisterChannelServiceImpl implements ExternalRegisterChannelService {
	
	@Autowired
	private ExternalRegisterChannelMapper mapper;

	@Override
	public ExternalRegisterChannel getIncentiveChannel(String channelSource) {
		return mapper.getIncentiveChannel(channelSource);
	}
	@Override
	public int insert(ExternalRegisterChannel externalRegisterChannel) {
		return mapper.insert(externalRegisterChannel);
	}
	@Override
	public void update(ExternalRegisterChannel externalRegisterChannel) {
		mapper.update(externalRegisterChannel);
	}

}
