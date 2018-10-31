package org.ieforex.service.impl;

import java.util.List;
import java.util.Map;

import org.ieforex.dao.ETradeAcctMapper;
import org.ieforex.entity.ETradeAcct;
import org.ieforex.service.ETradeAcctService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("eTradeAcctService")
public class ETradeAcctServiceImpl implements ETradeAcctService {

	@Autowired
	private ETradeAcctMapper mapper;
	
	@Override
	public List<ETradeAcct> queryList(Map<String, Object> map) {
		return mapper.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return mapper.queryTotal(map);
	}

	@Override
   public List<ETradeAcct> getAcctByCustomerId(Map<String, Object> map){
		return mapper.getAcctByCustomerId(map);
	}

	@Override
	public int selectCount(Long customerId) {
		return mapper.selectCount(customerId);
	}

	@Override
	public int cancelBindAcctNo(Map<String, Object> map) {
		return mapper.cancelBindAcctNo(map);
	}

	@Override
	public List<ETradeAcct> getIsSameNameOrIsAgency(Long customerId) {
		return mapper.getIsSameNameOrIsAgency(customerId);
	}

	@Override
	public List<ETradeAcct> isExistAcctNo(Map<String, Object> map) {
		return mapper.isExistAcctNo(map);
	}
}
