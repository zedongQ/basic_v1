package org.ieforex.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "w_external_register_channel")
public class ExternalRegisterChannel {
    /*
     * 渠道
     * 
     * */
	@Id
	@Column(name="channel_source")
	private String channelSource;
	 /*
     * 是否有注册奖励
     * 0:没有奖励
     * 1:有奖励
     * */
	@Column(name="is_incentive")
	private String isIncentive;
	
	
	public String getChannelSource() {
		return channelSource;
	}
	public void setChannelSource(String channelSource) {
		this.channelSource = channelSource;
	}
	public String getIsIncentive() {
		return isIncentive;
	}
	public void setIsIncentive(String isIncentive) {
		this.isIncentive = isIncentive;
	}
	
	
}
