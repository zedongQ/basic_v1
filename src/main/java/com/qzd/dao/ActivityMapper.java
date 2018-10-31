package org.ieforex.dao;

import org.ieforex.entity.Activity;
import org.springframework.stereotype.Repository;

@Repository("activityMapper")
public interface ActivityMapper {
	Activity selectById(Long activityId);
	Activity selectNew();
	Activity selectLast();
}
