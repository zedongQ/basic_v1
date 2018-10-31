package org.ieforex.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 
 * @author brilliance
 * @email 
 * @date 2017-08-21 14:01:04
 */
 @Table(name="w_live_chat")
public class WLiveChat implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @Id
	@Column(name="live_chat_id")
	private Long liveChatId;

    /**
     * qq
     */
    @Column(name="live_chat_qq")
	private String liveChatQq;
    
    /**
     * 状态
     */
    @Column(name="status")
    private String status;
    
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
    * 类型 0弹出客服 1侧边客服
    */
   @Column(name="type")
   private String type;
   
	/**
	 * 获取表字段live_chat_id
	 */
	public Long getLiveChatId() {
		return liveChatId;
	}

	/**
	 * 设置表字段live_chat_id
	 */
	public void setLiveChatId(Long liveChatId) {
		this.liveChatId = liveChatId;
	}
	/**
	 * 获取表字段live_chat_qq
	 */
	public String getLiveChatQq() {
		return liveChatQq;
	}

	/**
	 * 设置表字段live_chat_qq
	 */
	public void setLiveChatQq(String liveChatQq) {
		this.liveChatQq = liveChatQq;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
