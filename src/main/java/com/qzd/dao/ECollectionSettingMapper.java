package org.ieforex.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.ieforex.entity.ECollectionSetting;

/**
 * 提款方式设置
 * 
 * @author brilliance
 * @email 
 * @date 2017-05-15 15:56:49
 */
public interface ECollectionSettingMapper{
	//根据取款方式获取手续费
	List<ECollectionSetting> getFeeByWay(Map<String,Object> map);
	//根据取款类型获取手续费方式
	String getWayByType(Map<String,Object> map);
	//获取最大值手续费
	BigDecimal getMaxFee(Map<String,Object> map);
	//获取最大手续费百分比
	BigDecimal getMaxFeePercent(Map<String,Object> map);
}
