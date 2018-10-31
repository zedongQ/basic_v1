package org.ieforex.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.transaction.annotation.Transactional;

@Table(name="w_activity")
public class Activity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @Id
	@Column(name="activity_id")
	private Long activityId;

    private String title;

    @Column(name="start_date")
	private Date startDate;

    @Column(name="end_date")
	private Date endDate;

    @Column(name="create_user_id")
	private Long createUserId;

    @Column(name="create_date")
	private Date createDate;

    @Column(name="update_user_id")
	private Long updateUserId;

    @Column(name="update_date")
	private Date updateDate;

    @Column(name="lottery_cost")
    private Integer lotteryCost;
    
    @Column(name="prize_url")
    private String prizeUrl;
    
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
	 * 获取表字段title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置表字段title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取表字段start_date
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * 设置表字段start_date
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * 获取表字段end_date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置表字段end_date
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public Integer getLotteryCost() {
		return lotteryCost;
	}

	public void setLotteryCost(Integer lotteryCost) {
		this.lotteryCost = lotteryCost;
	}

	public String getPrizeUrl() {
		return prizeUrl;
	}

	public void setPrizeUrl(String prizeUrl) {
		this.prizeUrl = prizeUrl;
	}
	
	@Transient
	private String attendanceIntegral;

	public String getAttendanceIntegral() {
		return attendanceIntegral;
	}

	public void setAttendanceIntegral(String attendanceIntegral) {
		this.attendanceIntegral = attendanceIntegral;
	}

}
