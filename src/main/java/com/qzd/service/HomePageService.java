package org.ieforex.service;

import java.util.List;
import java.util.Map;

import org.ieforex.entity.HomePage;

public interface HomePageService {

	List<HomePage> selectByType(Map<String,Object> map);
	List<HomePage> selectDealer(Map<String,Object> map);
}
