package org.ieforex.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="w_goods")
public class Goods implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @Id
	@Column(name="goods_id")
	private Long goodsId;

    @Column(name="goods_name")
	private String goodsName;

    @Column(name="pic_url")
	private String picUrl;

    @Column(name="total_number")
	private Integer totalNumber;

    @Column(name="data_status")
	private String dataStatus;

    @Column(name="cost_integral")
	private Integer costIntegral;

    private String describes;

    @Column(name="create_user_id")
	private Long createUserId;

    @Column(name="create_date")
	private Date createDate;

    @Column(name="update_user_id")
	private Long updateUserId;

    @Column(name="update_date")
	private Date updateDate;


	/**
	 * 获取表字段goods_id
	 */
	public Long getGoodsId() {
		return goodsId;
	}

	/**
	 * 设置表字段goods_id
	 */
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * 获取表字段goods_name
	 */
	public String getGoodsName() {
		return goodsName;
	}

	/**
	 * 设置表字段goods_name
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	/**
	 * 获取表字段pic_url
	 */
	public String getPicUrl() {
		return picUrl;
	}

	/**
	 * 设置表字段pic_url
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	/**
	 * 获取表字段total_number
	 */
	public Integer getTotalNumber() {
		return totalNumber;
	}

	/**
	 * 设置表字段total_number
	 */
	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}
	/**
	 * 获取表字段data_status
	 */
	public String getDataStatus() {
		return dataStatus;
	}

	/**
	 * 设置表字段data_status
	 */
	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}
	/**
	 * 获取表字段cost_integral
	 */
	public Integer getCostIntegral() {
		return costIntegral;
	}

	/**
	 * 设置表字段cost_integral
	 */
	public void setCostIntegral(Integer costIntegral) {
		this.costIntegral = costIntegral;
	}
	/**
	 * 获取表字段describes
	 */
	public String getDescribes() {
		return describes;
	}

	/**
	 * 设置表字段describes
	 */
	public void setDescribes(String describes) {
		this.describes = describes;
	}
	/**
	 * 获取表字段create_user_id
	 */
	public Long getCreateUserId() {
		return createUserId;
	}

	/**
	 * 设置表字段create_user_id
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取表字段create_date
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * 设置表字段create_date
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取表字段update_user_id
	 */
	public Long getUpdateUserId() {
		return updateUserId;
	}

	/**
	 * 设置表字段update_user_id
	 */
	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}
	/**
	 * 获取表字段update_date
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * 设置表字段update_date
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
