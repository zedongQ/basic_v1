package org.ieforex.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


 @Table(name="goods_flowing")
public class GoodsFlowing implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @Id
	@Column(name="flowing_id")
	private Long flowingId;

    @Column(name="customer_id")
	private Long customerId;
    
    @Column(name="activity_id")
	private Long activityId;

    @Column(name="goods_id")
	private Long goodsId;

    private String status;

    @Column(name="insert_date")
	private Date insertDate;

    @Column(name="update_user_id")
	private Long updateUserId;

    @Column(name="update_date")
	private Date updateDate;
    
    /**收货人*/
    private String receiver;
    /**收货地址*/
    @Column(name="receive_address")
    private String receiveAddress;
    /**联系方式*/
    private String contact;
    /**备注*/
    private String remark;

	/**
	 * 获取表字段flowing_id
	 */
	public Long getFlowingId() {
		return flowingId;
	}

	/**
	 * 设置表字段flowing_id
	 */
	public void setFlowingId(Long flowingId) {
		this.flowingId = flowingId;
	}
	/**
	 * 获取表字段customer_id
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * 设置表字段customer_id
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
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
	 * 获取表字段status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 设置表字段status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取表字段insert_date
	 */
	public Date getInsertDate() {
		return insertDate;
	}

	/**
	 * 设置表字段insert_date
	 */
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
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

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
