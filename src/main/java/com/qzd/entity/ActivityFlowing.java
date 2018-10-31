package org.ieforex.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import org.ieforex.utils.charEcordingFilterUtil;

@Table(name="w_activity_flowing")
public class ActivityFlowing implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @Id
	@Column(name="flowing_id")
	private Long flowingId;

    @Column(name="activity_id")
	private Long activityId;

    @Column(name="customer_id")
	private Long customerId;

    @Column(name="prize_id")
	private Long prizeId;

    @Column(name="customer_inf")
	private String customerInf;

    private String type;

    @Column(name="prize_name")
	private String prizeName;

    @Column(name="prize_price")
	private Integer prizePrice;

    @Column(name="insert_date")
	private Date insertDate;


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
	 * 获取表字段customer_inf
	 */
	public String getCustomerInf() {
		return customerInf;
	}
	
	/**
	 * 设置表字段customer_inf
	 */
	public void setCustomerInf(String customerInf) {
		this.customerInf = customerInf;
	}
	
	public String getCustomerInfHtml(){
		if(customerInf!=null){
			return charEcordingFilterUtil.specialCharFilter(customerInf);
		}
		return customerInf;
	}
	
	/**
	 * 获取表字段type
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置表字段type
	 */
	public void setType(String type) {
		this.type = type;
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
}
