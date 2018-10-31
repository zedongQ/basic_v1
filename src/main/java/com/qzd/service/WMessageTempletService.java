package org.ieforex.service;

import org.ieforex.entity.WMessageTemplet;

/**
 * 消息邮件模板
 * 
 * @author brilliance
 * @email 
 * @date 2017-05-22 14:29:42
 */
public interface WMessageTempletService{
	//根据键值查找邮件模版
	WMessageTemplet queryMessageTemplet(String templetKey);
}
