package org.ieforex.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ieforex.dao.DealerMapper;
import org.ieforex.dao.LitchiClassroomCategoryMapper;
import org.ieforex.dao.LitchiClassroomMapper;
import org.ieforex.entity.Dealer;
import org.ieforex.entity.LitchiClassroom;
import org.ieforex.entity.LitchiClassroomCategory;
import org.ieforex.service.LitchiClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("litchiClassroomService")
public class LitchiClassroomServiceImpl implements LitchiClassroomService {

	@Autowired
	private LitchiClassroomMapper litchiClassroomMapper;
	@Autowired
	private LitchiClassroomCategoryMapper litchiClassroomCategoryMapper;
	@Autowired
	private DealerMapper dealerMapper;
	
	@Override
	public List<Map<String, Object>> litchiMenu() {
		List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
		List<LitchiClassroomCategory> categoryList = litchiClassroomCategoryMapper.select();
		if(categoryList != null){
			for(int i=0;i<categoryList.size();i++){
				Map<String, Object> dataMap = new HashMap<String, Object>();
				LitchiClassroomCategory category = categoryList.get(i);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("categoryId", category.getCategoryId());
				map.put("status", "1");
				List<LitchiClassroom> list = litchiClassroomMapper.select(map);
				dataMap.put("parents", category);
				dataMap.put("children", list);
				dataList.add(dataMap);
			}
		}
		return dataList;
	}

	@Override
	public LitchiClassroom classRoom(Long classId){
		return litchiClassroomMapper.selectById(classId);
	}
	
	@Override
	public LitchiClassroom firstClassroom(){
		return litchiClassroomMapper.selectFirst();
	}

	@Override
	public List<Dealer> dealerList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataStatus", "0");//数据状态
		return dealerMapper.selectMore(map);
	}

}
