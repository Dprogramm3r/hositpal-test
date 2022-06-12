package com.oze.hospital.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oze.hospital.model.Patient;

import java.util.Date;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface PatientDao extends JpaRepository<Patient, Long>{
	
    @Query(name = "findByAge", value = "select p from Patient p where p.age = :age")
    public List<Patient> findByAge(@Param("age")Integer age);
    
    @Modifying
    @Transactional
    @Query(name = "deleteByDateRange", value = "delete from Patient e where e.previousVisit between :startDate AND :endDate")
    public Integer deleteByDateRange(@Param("startDate") Date startDate,@Param("endDate") Date endDate);

}
