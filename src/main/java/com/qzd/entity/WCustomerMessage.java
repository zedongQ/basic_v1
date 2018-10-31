package org.ieforex.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.ieforex.enums.ReadOrUnRead;

import java.util.Date;

/**
 * 站内消息表
 * 
 * @author brilliance
 * @email 
 * @date 2017-06-16 09:58:26
 */
 @Table(name="w_customer_message")
public class WCustomerMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @Id
	@Column(name="message_id")
	private Long messageId;

    /**
	  * 客户编号
	  */
    @Column(name="customer_id")
	private Long customerId;

    /**
	  * 标题
	  */
    private String title;

    /**
	  * 内容
	  */
    private String content;

    /**
	  * 创建时间
	  */
    @Column(name="create_date")
	private Date createDate;
    
    @Transient
    private String createDateString;
    

    public String getCreateDateString() {
		return createDateString;
	}

	public void setCreateDateString(String createDateString) {
		this.createDateString = createDateString;
	}

	/**
	  * 状态:未读、已读
	  */
    private String status;

    public String getStatusStr() {
		if(status!=null){
			return ReadOrUnRead.getMapValue(status).describe();
		}
		return null;
	}

	/**
	 * 获取表字段message_id	
	 */
	public Long getMessageId() {
		return messageId;
	}

	/**
	 * 设置表字段message_id
	 */
	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
	/**
	 * 获取“客户编号”
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * 设置“客户编号”
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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
	 * 获取“状态:未读、已读”
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 设置“状态:未读、已读”
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
