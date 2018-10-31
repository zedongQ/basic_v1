package org.ieforex.dao;

import org.ieforex.entity.ExternalRegisterChannel;
import org.springframework.stereotype.Repository;

/**
 * 外部注冊接口
 */
@Repository
public interface ExternalRegisterChannelMapper {
	
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
