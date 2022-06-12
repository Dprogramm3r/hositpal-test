package com.oze.hospital;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.oze.hospital.dao.StaffDao;
import com.oze.hospital.model.Patient;
import com.oze.hospital.model.Staff;
import com.oze.hospital.service.PatientServiceImpl;

@SpringBootTest
class HospitalApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private PatientServiceImpl patientServiceImpl;

	@Test 
	public void FindAge(){ 
		Staff currenStaff = new Staff("Joshua");
		List<Patient> result = patientServiceImpl.findByAgeTwoOnly(currenStaff.getUuid());
        Assert.notNull(result, "This result Can not be Null");		
	}
	
	@Test 
	public void createStaff(){ 
		Staff currentStaff = new Staff("Joshua");
        Assert.notNull(currentStaff.getUuid(), "This result Can not be Null");		
	}
}
