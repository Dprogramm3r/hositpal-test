package com.oze.hospital.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.oze.hospital.dao.PatientDao;
import com.oze.hospital.dao.StaffDao;
import com.oze.hospital.exception.ApiAbsractException;
import com.oze.hospital.model.Patient;
import com.oze.hospital.model.Staff;

@Service
public class PatientServiceImpl {

	private PatientDao patientDao;

	private StaffDao staffDao;

	private static final String[] HEADERS = {"Name", "Age", "Last Visited", "ID"};

	private static final CSVFormat FORMAT = CSVFormat.DEFAULT.withHeader(HEADERS);

	@Autowired
	public PatientServiceImpl(PatientDao patientDao, StaffDao staffDao) {
		this.patientDao = patientDao;
		this.staffDao = staffDao;
	}

	public Staff validStaff(String id) {
		Staff retrirvedStaff =staffDao.findByUUID(id);
		if(Objects.isNull(retrirvedStaff)) {
			throw new ApiAbsractException("Staff UUID Does not exist within our service.", HttpStatus.FORBIDDEN);
		}
		return retrirvedStaff;
	}

	public List<Patient> findByAgeTwoOnly(String staffId){
		validStaff(staffId);
		return patientDao.findByAge(2);	
	}

	public String deleteByDateRange(String staffId,Date startDate, Date endDate){
		validStaff(staffId);
		Integer count = patientDao.deleteByDateRange(startDate,endDate);
		return "Patients Records Deleted From (" + dateToString(startDate) + " To " + dateToString(endDate) +") Total = "+ count;
	}
	
	private String dateToString(Date date) {
		DateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd");
		return outputFormatter.format(date);
	}

	public InputStream convertDataToCsv(String staffId, Long patientId) {
		validStaff(staffId);
		return convertToCsv(Arrays.asList(patientDao.findById(patientId).get()));
	}

	public InputStream convertToCsv(List<Patient> content) {

		List<String[]> result = new ArrayList<>();
		ListIterator<Patient> convertedData = content.listIterator();
		while (convertedData.hasNext()) {
			Patient currentpatient = convertedData.next();
			result.add(new String[]
					{ currentpatient.getName(), String.valueOf(currentpatient.getAge()), String.valueOf(currentpatient.getPreviousVisit()),String.valueOf(currentpatient.getId())});
		}
		return writeDataToCsv(result);
	}

	private ByteArrayInputStream writeDataToCsv(List<String[]> pateints) {

		try (final ByteArrayOutputStream stream = new ByteArrayOutputStream();
				final CSVPrinter printer = new CSVPrinter(new PrintWriter(stream), FORMAT)) {

			for (final String[] pateint : pateints) {
				final List<String> data = Arrays.asList(pateint);
				printer.printRecord(data);
			}
			printer.flush();
			return new ByteArrayInputStream(stream.toByteArray());
		} catch (final IOException e) {
			throw new ApiAbsractException("Csv writing error: " + e.getMessage(), HttpStatus.CONFLICT);
		}
	}








}
