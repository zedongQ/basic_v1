package org.ieforex.service.impl;

import java.util.List;
import java.util.Map;

import org.ieforex.dao.HomePageMapper;
import org.ieforex.entity.HomePage;
import org.ieforex.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("homePageService")
public class HomePageServiceImpl implements HomePageService {

	@Autowired
	private HomePageMapper mapper;
	
	@Override
	public List<HomePage> selectByType(Map<String, Object> map) {
		return mapper.selectByType(map);
	}

	@Override
	public List<HomePage> selectDealer(Map<String, Object> map) {
		return mapper.selectDealer(map);
	}

}
