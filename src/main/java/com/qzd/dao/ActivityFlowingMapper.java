package org.ieforex.dao;

import java.util.List;

import org.ieforex.entity.ActivityFlowing;
import org.springframework.stereotype.Repository;

@Repository("activityFlowingMapper")
public interface ActivityFlowingMapper {
	int insert(ActivityFlowing activityFlowing);
	List<ActivityFlowing> select(Long activityId);
	List<ActivityFlowing> selectBigPrize();//假的中奖信息
}
