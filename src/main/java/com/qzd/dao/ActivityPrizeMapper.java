package org.ieforex.dao;

import java.util.List;
import java.util.Map;

import org.ieforex.entity.ActivityPrize;
import org.springframework.stereotype.Repository;

@Repository("activityPrizeMapper")
public interface ActivityPrizeMapper {
	List<ActivityPrize> activityPrize(Long activityId);
	ActivityPrize selectById(Long prizeId);
	ActivityPrize selectByType(Map<String, Object> map);
	int updateAmount(ActivityPrize activityPrize);
}
