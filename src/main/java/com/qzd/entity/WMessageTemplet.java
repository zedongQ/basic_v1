package org.ieforex.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 消息邮件模板
 * 
 * @author brilliance
 * @email 
 * @date 2017-05-22 14:29:42
 */
 @Table(name="w_message_templet")
public class WMessageTemplet implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
	  * ID
	  */
    @Id
	@Column(name="templet_id")
	private Long templetId;

    /**
	  * 模板分类
	  */
    @Column(name="templet_category")
	private String templetCategory;

    /**
	  * 模板类型
	  */
    @Column(name="templet_type")
	private String templetType;


    /**
	  * 模板键值
	  */
    @Column(name="templet_key")
	private String templetKey;

    /**
	  * 标题
	  */
    private String title;

    /**
	  * 内容
	  */
    private String content;

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
	  * 更新人
	  */
    @Column(name="update_user_id")
	private Long updateUserId;

    /**
	  * 更新时间
	  */
    @Column(name="update_date")
	private Date updateDate;


	/**
	 * 获取“ID”
	 */
	public Long getTempletId() {
		return templetId;
	}

	/**
	 * 设置“ID”
	 */
	public void setTempletId(Long templetId) {
		this.templetId = templetId;
	}
	/**
	 * 获取“模板分类”
	 */
	public String getTempletCategory() {
		return templetCategory;
	}

	/**
	 * 设置“模板分类”
	 */
	public void setTempletCategory(String templetCategory) {
		this.templetCategory = templetCategory;
	}
	/**
	 * 获取“模板类型”
	 */
	public String getTempletType() {
		return templetType;
	}

	/**
	 * 设置“模板类型”
	 */
	public void setTempletType(String templetType) {
		this.templetType = templetType;
	}
	/**
	 * 获取“模板键值”
	 */
	public String getTempletKey() {
		return templetKey;
	}

	/**
	 * 设置“模板键值”
	 */
	public void setTempletKey(String templetKey) {
		this.templetKey = templetKey;
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
	 * 获取“更新人”
	 */
	public Long getUpdateUserId() {
		return updateUserId;
	}

	/**
	 * 设置“更新人”
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
}
