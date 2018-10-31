package org.ieforex.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.transaction.annotation.Transactional;
@Table(name="customer_attendance")
public class CustomerAttendance implements Serializable{

	private static final long serialVersionUID = 1L;
	
	 @Id
	 @Column(name="attendance_id")
	 private Long attendanceId;
	 
	 @Column(name="customer_id")
	 private Long customerId;
	 
	 private String monday;
	 
	 private String tuesday;
	 
	 private String wednesday;
	 
	 private String thursday;
	 
	 private String friday;
	 
	 private String saturday;
	 
	 private String sunday;

	 @Column(name="attendancd_date")
	 private Date attendancdDate;
	 
	 @Transient
	 private String hasAttendaned;
	 
	 @Transient
	 private String attendanceIntegral;
	 
	public String getAttendanceIntegral() {
		return attendanceIntegral;
	}

	public void setAttendanceIntegral(String attendanceIntegral) {
		this.attendanceIntegral = attendanceIntegral;
	}

	public String getHasAttendaned() {
		return hasAttendaned;
	}

	public void setHasAttendaned(String hasAttendaned) {
		this.hasAttendaned = hasAttendaned;
	}

	public Date getAttendancdDate() {
		return attendancdDate;
	}

	public void setAttendancdDate(Date attendancdDate) {
		this.attendancdDate = attendancdDate;
	}

	public Long getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(Long attendanceId) {
		this.attendanceId = attendanceId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getMonday() {
		return monday;
	}

	public void setMonday(String monday) {
		this.monday = monday;
	}

	public String getTuesday() {
		return tuesday;
	}

	public void setTuesday(String tuesday) {
		this.tuesday = tuesday;
	}

	public String getWednesday() {
		return wednesday;
	}

	public void setWednesday(String wednesday) {
		this.wednesday = wednesday;
	}

	public String getThursday() {
		return thursday;
	}

	public void setThursday(String thursday) {
		this.thursday = thursday;
	}

	public String getFriday() {
		return friday;
	}

	public void setFriday(String friday) {
		this.friday = friday;
	}

	public String getSaturday() {
		return saturday;
	}

	public void setSaturday(String saturday) {
		this.saturday = saturday;
	}

	public String getSunday() {
		return sunday;
	}

	public void setSunday(String sunday) {
		this.sunday = sunday;
	}
	 
	 
}
