package org.ieforex.service;

import org.ieforex.entity.ExternalRegisterChannel;

/**
 *渠道
 */
public interface ExternalRegisterChannelService {
	/**
	 * 获取有注册奖励的渠道
	 * 
	 * */
	ExternalRegisterChannel getIncentiveChannel(String channelSource);
	/**
	 * 新增渠道
	 * 
	 */
	int insert(ExternalRegisterChannel externalRegisterChannel);

	/**
	 * 更新渠道
	 * 
	 */
	void update(ExternalRegisterChannel externalRegisterChannel);
}
