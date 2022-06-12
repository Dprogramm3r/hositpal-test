package com.oze.hospital.controller.utils;

import java.util.Date;

public class RequestClass {
	private String uuid;
	private Long patientId;
	private Date startDate;
	private Date endDate;

	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	@Override
	public String toString() {
		return "RequestClass [uuid=" + uuid + ", patientId=" + patientId + ", startDate=" + startDate + ", endDate="
				+ endDate + "]";
	}
	
	
	
}
