package org.ieforex.dao;

import java.util.List;

import org.ieforex.entity.LitchiClassroomCategory;
import org.springframework.stereotype.Repository;

@Repository("litchiClassroomCategoryMapper")
public interface LitchiClassroomCategoryMapper{
	  List<LitchiClassroomCategory> select();
}
