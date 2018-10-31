package org.ieforex.service.impl;

import java.util.List;
import java.util.Map;

import org.ieforex.dao.ETradeAcctTaskMapper;
import org.ieforex.entity.ETradeAcctTask;
import org.ieforex.service.ETradeAcctTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("eTradeAcctTaskService")
public class ETradeAcctTaskServiceImpl implements ETradeAcctTaskService {

	@Autowired
	private ETradeAcctTaskMapper mapper;
	@Override
	public List<ETradeAcctTask> getAcctByCustomerId(Map<String, Object> map) {
		return mapper.getAcctByCustomerId(map);
	}

	@Override
	public ETradeAcctTask getByTaskId(Long tradeAcctTaskId) {
		return mapper.getByTaskId(tradeAcctTaskId);
	}

	@Override
	public void updateByTaskId(ETradeAcctTask tradeAcctTask) {
		mapper.updateByTaskId(tradeAcctTask);
	}

	@Override
	public void save(ETradeAcctTask tradeAcct) {
		mapper.save(tradeAcct);
	}
	
	
}
