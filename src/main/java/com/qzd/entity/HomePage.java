package org.ieforex.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.ieforex.enums.PubStatus;
import org.ieforex.enums.OptionsModel;

/**
 * 首页图片
 * 
 * @author brilliance
 * @email 
 * @date 2017-05-22 14:04:35
 */
 @Table(name="w_home_page")
public class HomePage implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
	  * 编号
	  */
    @Id
	@Column(name="page_id")
	private Long pageId;
    
    /**
	  * 交易商id
	  */
	@Column(name="dealer_id")
	private Long dealerId;
    
    /**
	  * 图片所属模块
	*/
   @Column(name="image_type")
	private String imageType;
    public String getImageTypeName(){
    	return OptionsModel.getDisplayName(imageType);
    }
    
    /**
	  * 图片路径
	  */
    @Column(name="pic_url")
	private String picUrl;
    
    /**
	  * 主标题
	  */
    @Column(name="main_title")
	private String mainTitle;
    
    /**
	  * 副标题
	  */
    @Column(name="subhead")
	private String subhead;

    /**
	  * 外链接地址
	  */
    @Column(name="link_url")
	private String linkUrl;

    /**
	  * 排序
	  */
    @Column(name="sort_no")
	private Integer sortNo;

    /**
	  * 状态
	  */
    private String status;
    public String getStatusName(){
    	if(status !=null){
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
	  * 发布人姓名
	  */
    @Transient
	private String publishUserName;

    /**
	  * 发布时间
	  */
    @Column(name="publish_date")
	private Date publishDate;

    /**
	  * 创建人
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
	 * 获取“编号”
	 */
	public Long getPageId() {
		return pageId;
	}

	/**
	 * 设置“编号”
	 */
	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}
	
	public Long getDealerId() {
		return dealerId;
	}

	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}

	public String getMainTitle() {
		return mainTitle;
	}

	public void setMainTitle(String mainTitle) {
		this.mainTitle = mainTitle;
	}

	public String getSubhead() {
		return subhead;
	}

	public void setSubhead(String subhead) {
		this.subhead = subhead;
	}

	/**
	 * 获取“图片路径”
	 */
	public String getPicUrl() {
		return picUrl;
	}

	/**
	 * 设置“图片路径”
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	/**
	 * 获取“外链接地址”
	 */
	public String getLinkUrl() {
		return linkUrl;
	}

	/**
	 * 设置“外链接地址”
	 */
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	/**
	 * 获取“排序”
	 */
	public Integer getSortNo() {
		return sortNo;
	}

	/**
	 * 设置“排序”
	 */
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
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
	 * 获取“创建人”
	 */
	public Long getCreateUserId() {
		return createUserId;
	}

	/**
	 * 设置“创建人”
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
	
    /**
     * 获取“图片所属模块”
     * @return
     */
	public String getImageType() {
		return imageType;
	}
	
	/**
	 * 设置“图片所属模块”
	 * @param imageType
	 */
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public String getPublishUserName() {
		return publishUserName;
	}

	public void setPublishUserName(String publishUserName) {
		this.publishUserName = publishUserName;
	}
	
	@Transient
	private String dealerName;
	@Transient
	private String logoUrl2;
	
	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getLogoUrl2() {
		return logoUrl2;
	}

	public void setLogoUrl2(String logoUrl2) {
		this.logoUrl2 = logoUrl2;
	}
	
}
