package org.ieforex.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 外部注册接口
 */
@Table(name = "w_external_register")
public class ExternalRegister implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "register_id")
	private Long registerId;

	/**
	 * 注册手机号
	 */
	@Column(name = "register_phone")
	private String registerPhone;

	/**
	 * 渠道来源
	 */
	@Column(name = "channel_source")
	private String channelSource;

	/**
	 * 验证码
	 */
	@Column(name = "identifying_code")
	private String identifyingCode;

	/**
	 * 创建时间
	 */
	@Column(name = "create_date")
	private Date createDate;

	/**
	 * 更新時間
	 */
	@Column(name = "update_date")
	private Date updateDate;

	public Long getRegisterId() {
		return registerId;
	}

	public void setRegisterId(Long registerId) {
		this.registerId = registerId;
	}

	public String getRegisterPhone() {
		return registerPhone;
	}

	public void setRegisterPhone(String registerPhone) {
		this.registerPhone = registerPhone;
	}

	public String getChannelSource() {
		return channelSource;
	}

	public void setChannelSource(String channelSource) {
		this.channelSource = channelSource;
	}

	public String getIdentifyingCode() {
		return identifyingCode;
	}

	public void setIdentifyingCode(String identifyingCode) {
		this.identifyingCode = identifyingCode;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
