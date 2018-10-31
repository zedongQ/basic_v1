package org.ieforex.dao;

import java.util.List;
import java.util.Map;

import org.ieforex.entity.LitchiClassroom;
import org.springframework.stereotype.Repository;

@Repository("litchiClassroomMapper")
public interface LitchiClassroomMapper{
	  List<LitchiClassroom> select(Map<String, Object> map);
	  LitchiClassroom selectById(Long classId);
	  LitchiClassroom selectFirst();
}
