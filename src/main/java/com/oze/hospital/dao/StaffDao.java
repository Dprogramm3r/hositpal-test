package com.oze.hospital.dao;

import org.springframework.stereotype.Repository;

import com.oze.hospital.model.Staff;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface StaffDao extends JpaRepository<Staff, Long>{
	
    @Query(name = "findByUUID", value = "select s from Staff s where s.uuid = :uuid")
    public Staff findByUUID(@Param("uuid")String uuid);

}
