package org.ieforex.dao;

import org.ieforex.entity.WMessageTemplet;
import org.springframework.stereotype.Repository;

/**
 * 消息邮件模板
 * 
 * @author brilliance
 * @email 
 * @date 2017-05-22 14:29:42
 */

@Repository
public interface WMessageTempletMapper {
	//根据键值查找邮件模版
	WMessageTemplet queryMessageTemplet(String templetKey);
}
