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
 @Table(name="w_help_center_category")
public class HelpCenterCategory implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @Id
	@Column(name="category_id")
	private Long categoryId;

    @Column(name="category_name")
	private String categoryName;

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
	 * 获取表字段category_name
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * 设置表字段category_name
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
