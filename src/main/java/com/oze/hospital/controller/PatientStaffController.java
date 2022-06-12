package com.oze.hospital.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oze.hospital.controller.utils.RequestClass;
import com.oze.hospital.exception.ApiAbsractException;
import com.oze.hospital.model.Patient;
import com.oze.hospital.model.Staff;
import com.oze.hospital.service.PatientServiceImpl;
import com.oze.hospital.service.StaffServiceImpl;

@RestController
@RequestMapping("/api")
public class PatientStaffController {


	private StaffServiceImpl staffImpl;
	private PatientServiceImpl PatientImpl;

	@Autowired
	public PatientStaffController(StaffServiceImpl staffImpl,PatientServiceImpl PatientImpl) {
		this.staffImpl = staffImpl;
		this.PatientImpl = PatientImpl;
	}

	@PostMapping("/staff")
	public Staff create(@RequestBody Staff staff) {
		return staffImpl.create(staff);
	}

	@PutMapping(value = "/staff")
	public Staff update( @RequestBody Staff staff) {
		return staffImpl.update(staff);
	}

	@PostMapping(value = "/patient/findbyagetwo")	
	public List<Patient> findByPatientAgeTwo(@RequestBody RequestClass request) {
			return PatientImpl.findByAgeTwoOnly(request.getUuid());
	}

	@PostMapping(path = "/patient/download-file", produces = "application/csv")
	public ResponseEntity<Resource> downloadFilePerDomain(@RequestBody RequestClass request) {
		InputStreamResource result = new InputStreamResource(PatientImpl.convertDataToCsv(request.getUuid(),request.getPatientId()));
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+request.getPatientId())
				.contentType(MediaType.parseMediaType("application/csv"))
				.body(result);

	}

	@PostMapping(path = "/patient/delete/date-range")
	public String deleteByDateRange(@RequestBody RequestClass request) {
		return PatientImpl.deleteByDateRange(request.getUuid(),request.getStartDate(), request.getEndDate());		 
	}




}
