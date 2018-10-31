package org.ieforex.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ieforex.dao.DealerMapper;
import org.ieforex.dao.HelpCenterCategoryMapper;
import org.ieforex.dao.HelpCenterMapper;
import org.ieforex.entity.Dealer;
import org.ieforex.entity.HelpCenter;
import org.ieforex.entity.HelpCenterCategory;
import org.ieforex.service.HelpCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("helpCenterService")
public class HelpCenterServiceImpl implements HelpCenterService {

	@Autowired
	private HelpCenterCategoryMapper helpCenterCategoryMapper;
	@Autowired
	private HelpCenterMapper helpCenterMapper;
	@Autowired
	private DealerMapper dealerMapper;
	
	@Override
	public List<Map<String, Object>> helpMenu() {
		List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
		List<HelpCenterCategory> categoryList = helpCenterCategoryMapper.select();
		if(categoryList != null){
			for(int i=0;i<categoryList.size();i++){
				Map<String, Object> dataMap = new HashMap<String, Object>();
				HelpCenterCategory category = categoryList.get(i);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("categoryId", category.getCategoryId());
				map.put("status", "1");
				List<HelpCenter> list = helpCenterMapper.select(map);
				dataMap.put("parents", category);
				dataMap.put("children", list);
				dataList.add(dataMap);
			}
		}
		return dataList;
	}

	@Override
	public HelpCenter helpRoom(Long classId){
		return helpCenterMapper.selectById(classId);
	}
	
	@Override
	public HelpCenter firstHelp(){
		return helpCenterMapper.selectFirst();
	}

	@Override
	public List<Dealer> dealerList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataStatus", "0");//数据状态
		return dealerMapper.selectMore(map);
	}

}
