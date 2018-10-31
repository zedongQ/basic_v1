package org.ieforex.dao;

import java.util.Map;

import org.ieforex.entity.CustomerAttendance;


/**
 * 用户表
 * 
 * @author brilliance
 * @email 
 * @date 2017-04-25 15:32:48
 */
public interface CustomerAttendanceMapper {
	//根据客户Id清空签到记录
	void emptyAllDay(Long customerId);
	//新增签到
	void saveAttendance(CustomerAttendance customerAttendance);
	//执行签到
	void updateAttendance (CustomerAttendance customerAttendance);
	//获取签到数据
	CustomerAttendance getInfo(Map<String,Object> map);
}
