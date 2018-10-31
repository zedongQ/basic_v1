package org.ieforex.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * 
 * 
 * @author brilliance
 * @email 
 * @date 2017-09-14 09:52:45
 */
 @Table(name="w_help_center")
public class HelpCenter implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 编号
	 */
    @Id
	@Column(name="help_center_id")
	private Long helpCenterId;
    
    /**
     * 问题类型编号
     */
    @Column(name="category_id")
	private Long categoryId;
    
    /**
	* 类别名称
	*/
    
    
    /**
	* 标题
	*/
    private String title;
    
    /**
	* 内容
	*/
    private String content;
    /**
	* 作者
	*/
    private String author;
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
      * 排序
      */
    @Column(name="sort_no")
	private Integer sortNo;
    /**
	  * 状态
	  */
    private String status;

    @Column(name="create_user_id")
	private Long createUserId;

    @Column(name="create_date")
	private Date createDate;

    @Column(name="update_user_id")
	private Long updateUserId;

    @Column(name="update_date")
	private Date updateDate;


	/**
	 * 获取表字段help_center_id
	 */
	public Long getHelpCenterId() {
		return helpCenterId;
	}

	/**
	 * 设置表字段help_center_id
	 */
	public void setHelpCenterId(Long helpCenterId) {
		this.helpCenterId = helpCenterId;
	}
	/**
	 * 获取表字段category_id
	 */
	public Long getCategoryId() {
		return categoryId;
	}

	/**
	 * 设置表字段category_id
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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
	 * 获取表字段content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置表字段content
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取表字段author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * 设置表字段author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * 获取表字段publish_user_id
	 */
	public Long getPublishUserId() {
		return publishUserId;
	}

	/**
	 * 设置表字段publish_user_id
	 */
	public void setPublishUserId(Long publishUserId) {
		this.publishUserId = publishUserId;
	}
	/**
	 * 获取表字段publish_date
	 */
	public Date getPublishDate() {
		return publishDate;
	}

	/**
	 * 设置表字段publish_date
	 */
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
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
