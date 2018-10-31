package org.ieforex.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 优惠促销
 * 
 * @author brilliance
 * @email 
 * @date 2017-05-23 14:27:31
 */
 @Table(name="w_promotional_discount")
public class WPromotionalDiscount implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Transient
	private String chnName;
	
	public String getChnName() {
		return chnName;
	}

	public void setChnName(String chnName) {
		this.chnName = chnName;
	}

	@Transient
	private String expired;
	
    public String getExpired() {
		return expired;
	}

	public void setExpired(String expired) {
		this.expired = expired;
	}

	/**
	  * ID
	  */
    @Id
	@Column(name="promotional_id")
	private Long promotionalId;

    /**
	  * 标题
	  */
    private String title;

    /**
	  * 开始日期
	  */
    @Column(name="start_date")
	private Date startDate;

    /**
	  * 结束日期
	  */
    @Column(name="end_date")
	private Date endDate;

    /**
	  * 交易商编号
	  */
    @Column(name="dealer_id")
	private Long dealerId;

    /**
	  * 小图标
	  */
    @Column(name="pic_url")
	private String picUrl;

    /**
	  * 内容
	  */
    private String content;

    /**
	  * 排序
	  */
    @Column(name="sort_no")
	private Long sortNo;

    /**
	  * 描述
	  */
   @Column(name="describer")
	private String describer;

	public String getDescriber() {
		return describer;
	}

	public void setDescriber(String describer) {
		this.describer = describer;
	}

	/**
	  * 阅读次数
	  */
    private Long times;

    /**
	  * 状态
	  */
    private String status;

    /**
	  * 发布人
	  */
    @Column(name="publish_user_id")
	private Long publishUserId;

    /**
	  * 发布时间
	  */
    @Column(name="publish_date")
	private Date publishDate;

    /**
	  * 创建人员
	  */
    @Column(name="create_user_id")
	private Long createUserId;

    /**
	  * 创建时间
	  */
    @Column(name="create_date")
	private Date createDate;

    /**
	  * 更新人员
	  */
    @Column(name="update_user_id")
	private Long updateUserId;

    /**
	  * 更新时间
	  */
    @Column(name="update_date")
	private Date updateDate;
    
    /**
     * 交易商名称
     */
    @Transient
    private String name;
    
    /**
     * 开始时间
     */
    @Transient
    private String startDateTime;
    
    /**
     * 结束时间
     */
    @Transient
    private String endDateTime;

    /**
	  * 是否为中文
	  */
    @Column(name="is_ch")
	private String isCh;

	/**
	 * 获取“ID”
	 */
	public Long getPromotionalId() {
		return promotionalId;
	}

	/**
	 * 设置“ID”
	 */
	public void setPromotionalId(Long promotionalId) {
		this.promotionalId = promotionalId;
	}
	/**
	 * 获取“标题”
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置“标题”
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取“开始日期”
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * 设置“开始日期”
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * 获取“结束日期”
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置“结束日期”
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * 获取“交易商编号”
	 */
	public Long getDealerId() {
		return dealerId;
	}

	/**
	 * 设置“交易商编号”
	 */
	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}
	/**
	 * 获取“小图标”
	 */
	public String getPicUrl() {
		return picUrl;
	}

	/**
	 * 设置“小图标”
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	/**
	 * 获取“内容”
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置“内容”
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取“排序”
	 */
	public Long getSortNo() {
		return sortNo;
	}

	/**
	 * 设置“排序”
	 */
	public void setSortNo(Long sortNo) {
		this.sortNo = sortNo;
	}
	/**
	 * 获取“阅读次数”
	 */
	public Long getTimes() {
		return times;
	}

	/**
	 * 设置“阅读次数”
	 */
	public void setTimes(Long times) {
		this.times = times;
	}
	/**
	 * 获取“状态”
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 设置“状态”
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取“发布人”
	 */
	public Long getPublishUserId() {
		return publishUserId;
	}

	/**
	 * 设置“发布人”
	 */
	public void setPublishUserId(Long publishUserId) {
		this.publishUserId = publishUserId;
	}
	/**
	 * 获取“发布时间”
	 */
	public Date getPublishDate() {
		return publishDate;
	}

	/**
	 * 设置“发布时间”
	 */
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	/**
	 * 获取“创建人员”
	 */
	public Long getCreateUserId() {
		return createUserId;
	}

	/**
	 * 设置“创建人员”
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取“创建时间”
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * 设置“创建时间”
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取“更新人员”
	 */
	public Long getUpdateUserId() {
		return updateUserId;
	}

	/**
	 * 设置“更新人员”
	 */
	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}
	/**
	 * 获取“更新时间”
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * 设置“更新时间”
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}

	public String getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}

	public String getIsCh() {
		return isCh;
	}

	public void setIsCh(String isCh) {
		this.isCh = isCh;
	}
	
}
