package org.ieforex.dao;

import java.util.List;

import org.ieforex.entity.HelpCenterCategory;
import org.springframework.stereotype.Repository;

@Repository("helpCenterCategoryMapper")
public interface HelpCenterCategoryMapper{
	  List<HelpCenterCategory> select();
}
