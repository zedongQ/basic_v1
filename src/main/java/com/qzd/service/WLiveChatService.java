package org.ieforex.service;


import java.util.List;

import org.ieforex.entity.WLiveChat;


/**
 * 
 * 
 * @author brilliance
 * @email 
 * @date 2017-08-21 14:01:04
 */
public interface WLiveChatService{
	List<WLiveChat> queryList(String type);
}
