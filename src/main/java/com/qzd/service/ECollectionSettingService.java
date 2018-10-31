package org.ieforex.service;

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
public interface ECollectionSettingService{
	List<ECollectionSetting> getFeeByWay(Map<String,Object> map);
	String getWayByType(Map<String,Object> map);
	BigDecimal getMaxFee(Map<String,Object> map);
	BigDecimal getMaxFeePercent(Map<String,Object> map);
}
