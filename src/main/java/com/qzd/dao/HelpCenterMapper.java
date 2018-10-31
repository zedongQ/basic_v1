package org.ieforex.dao;

import java.util.List;
import java.util.Map;

import org.ieforex.entity.HelpCenter;
import org.springframework.stereotype.Repository;

@Repository("helpCenterMapper")
public interface HelpCenterMapper{
	  List<HelpCenter> select(Map<String, Object> map);
	  HelpCenter selectById(Long classId);
	  HelpCenter selectFirst();
}
