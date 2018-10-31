package org.ieforex.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.ieforex.dao.ECollectionSettingMapper;
import org.ieforex.entity.ECollectionSetting;
import org.ieforex.service.ECollectionSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("eCollectionSettingService")
public class ECollectionSettingServiceImpl implements ECollectionSettingService {

	@Autowired
	private ECollectionSettingMapper mapper;
	
	@Override
	public List<ECollectionSetting> getFeeByWay(Map<String, Object> map) {
		return mapper.getFeeByWay(map);
	}

	@Override
	public String getWayByType(Map<String, Object> map) {
		return mapper.getWayByType(map);
	}

	@Override
	public BigDecimal getMaxFee(Map<String, Object> map) {
		return mapper.getMaxFee(map);
	}

	@Override
	public BigDecimal getMaxFeePercent(Map<String, Object> map) {
		return mapper.getMaxFeePercent(map);
	}
}
