package org.ieforex.dao;

import java.util.List;
import java.util.Map;

import org.ieforex.entity.HomePage;
import org.springframework.stereotype.Repository;

@Repository("homePageMapper")
public interface HomePageMapper {
	List<HomePage> selectByType(Map<String,Object> map);
	List<HomePage> selectDealer(Map<String,Object> map);
}
