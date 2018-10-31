package org.ieforex.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="w_activity_prize")
public class ActivityPrize implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @Id
	@Column(name="prize_id")
	private Long prizeId;

    @Column(name="activity_id")
	private Long activityId;

    @Column(name="prize_name")
	private String prizeName;

    @Column(name="prize_price")
	private Integer prizePrice;

    @Column(name="prize_amount")
	private Integer prizeAmount;

    @Column(name="prize_type")
	private String prizeType;

    private Integer weight;

    @Column(name="limit_type")
	private String limitType;

    @Column(name="sort_no")
	private Integer sortNo;

    @Column(name="create_user_id")
	private Long createUserId;

    @Column(name="create_date")
	private Date createDate;

    @Column(name="update_user_id")
	private Long updateUserId;

    @Column(name="update_date")
	private Date updateDate;


	/**
	 * 获取表字段prize_id
	 */
	public Long getPrizeId() {
		return prizeId;
	}

	/**
	 * 设置表字段prize_id
	 */
	public void setPrizeId(Long prizeId) {
		this.prizeId = prizeId;
	}
	/**
	 * 获取表字段activity_id
	 */
	public Long getActivityId() {
		return activityId;
	}

	/**
	 * 设置表字段activity_id
	 */
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	/**
	 * 获取表字段prize_name
	 */
	public String getPrizeName() {
		return prizeName;
	}

	/**
	 * 设置表字段prize_name
	 */
	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}
	/**
	 * 获取表字段prize_price
	 */
	public Integer getPrizePrice() {
		return prizePrice;
	}

	/**
	 * 设置表字段prize_price
	 */
	public void setPrizePrice(Integer prizePrice) {
		this.prizePrice = prizePrice;
	}
	/**
	 * 获取表字段prize_amount
	 */
	public Integer getPrizeAmount() {
		return prizeAmount;
	}

	/**
	 * 设置表字段prize_amount
	 */
	public void setPrizeAmount(Integer prizeAmount) {
		this.prizeAmount = prizeAmount;
	}
	/**
	 * 获取表字段prize_type
	 */
	public String getPrizeType() {
		return prizeType;
	}

	/**
	 * 设置表字段prize_type
	 */
	public void setPrizeType(String prizeType) {
		this.prizeType = prizeType;
	}
	/**
	 * 获取表字段weight
	 */
	public Integer getWeight() {
		return weight;
	}

	/**
	 * 设置表字段weight
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	/**
	 * 获取表字段limit_type
	 */
	public String getLimitType() {
		return limitType;
	}

	/**
	 * 设置表字段limit_type
	 */
	public void setLimitType(String limitType) {
		this.limitType = limitType;
	}
	/**
	 * 获取表字段sort_no
	 */
	public Integer getSortNo() {
		return sortNo;
	}

	/**
	 * 设置表字段sort_no
	 */
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
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
