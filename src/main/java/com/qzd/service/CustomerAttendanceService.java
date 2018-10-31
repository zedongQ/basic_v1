package org.ieforex.service;


import org.ieforex.entity.CustomerAttendance;
import org.ieforex.entity.ECustomer;


/**
 * 用户表
 * 
 * @author brilliance
 * @email 
 * @date 2017-04-25 15:32:48
 */
public interface CustomerAttendanceService {
	void emptyAllDay(Long customerId);
	int updateAttendance (CustomerAttendance customerAttendance,ECustomer customer);
	CustomerAttendance getInfo(Long customerId);
	void saveAttendance(CustomerAttendance customerAttendance);
}