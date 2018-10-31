package org.ieforex.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.ieforex.enums.CategoryType;
import org.ieforex.enums.PubStatus;

import java.util.Date;

/**
 * 外汇服务
 * 
 * @author brilliance
 * @email 
 * @date 2017-05-23 10:02:29
 */
 @Table(name="w_foreign_exchange_service")
public class ForeignExchange implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
	  * ID
	  */
    @Id
	@Column(name="service_id")
	private Long serviceId;

    /**
	  * 服务类别
	  */
    @Column(name="category_type")
	private String categoryType;
    public String getCategoryTypeName() {
    	if(categoryType!=null){
    	   return CategoryType.getDisplayName(categoryType);
        }
		return null; 
    }

    /**
	  * 标题
	  */
    private String title;

    /**
	  * 时间
	  */
    @Column(name="service_date")
	private Date serviceDate;

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
    
    /**
     * 1 推荐  0不推荐
     */
    private String recommend;
 
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
    public String getStatusName() {
    	if(status!=null){
    	   return PubStatus.getDisplayName(status);
        }
		return null; 
    }
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
    
    private String writer;

	/**
	 * 获取“ID”
	 */
	public Long getServiceId() {
		return serviceId;
	}

	/**
	 * 设置“ID”
	 */
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	/**
	 * 获取“服务类别”
	 */
	public String getCategoryType() {
		return categoryType;
	}

	/**
	 * 设置“服务类别”
	 */
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
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
	 * 获取“时间”
	 */
	public Date getServiceDate() {
		return serviceDate;
	}

	/**
	 * 设置“时间”
	 */
	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
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

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}
		
}
