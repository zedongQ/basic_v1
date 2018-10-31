package org.ieforex.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="w_litchi_classroom_category")
public class LitchiClassroomCategory implements Serializable {
	private static final long serialVersionUID = 1L;

    /**
	* 编号
	*/
    @Id
	@Column(name="category_id")
	private Long categoryId;
    
    /**
	* 名称
	*/
    @Column(name="category_name")
	private String categoryName;

    /**
	* 排序
	*/
    @Column(name="sort_no")
	private Integer sortNo;

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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
