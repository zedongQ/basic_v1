package org.ieforex.dao;

import java.util.List;

import org.ieforex.entity.WLiveChat;
import org.springframework.stereotype.Repository;

/**
 * 
 * 
 * @author brilliance
 * @email 
 * @date 2017-08-21 14:01:04
 */
@Repository
public interface WLiveChatMapper{
	List<WLiveChat> queryList(String type);
}
