package com.oze.hospital.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.oze.hospital.dao.StaffDao;
import com.oze.hospital.exception.ApiAbsractException;
import com.oze.hospital.model.Staff;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Service
public class StaffServiceImpl {

	private StaffDao staffDao;

	@Autowired
	public StaffServiceImpl(StaffDao staffDao) {
		this.staffDao = staffDao;
	}

	public Staff create(Staff staff) {
		if(Objects.isNull(staff) || Objects.isNull(staff.getName())) {
			throw new ApiAbsractException("Staff Name required", HttpStatus.FORBIDDEN);
		}
		
		return staffDao.save(new Staff(staff.getName()));
	}

	public Staff findByUUID(String id) {
		return staffDao.findByUUID(id);
	}
	
	public Staff update(Staff staff) {

		if(Objects.isNull(staff) || Objects.isNull(staff.getName())) {
			throw new ApiAbsractException("Staff request is empty or Staff does not have name to update", HttpStatus.NOT_FOUND);
		}
		Staff retrievedStaff =staffDao.findByUUID(staff.getUuid());
		if(Objects.isNull(retrievedStaff)) {
			throw new ApiAbsractException("Staff UUID does not exist", HttpStatus.NOT_FOUND);
		}
		retrievedStaff.setName(staff.getName());
		return staffDao.save(retrievedStaff);
	}
}
