package com.oze.hospital.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "PATIENT")
public class Patient implements Serializable  {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
	private Long id;
    
    @Column(name = "AGE")
    private Integer age;
    
    @Column(name = "PREVIOUS_VISIT_DATE")
    private Date previousVisit;
    
    @Column(name = "NAME")
	private String name;
    
    public Patient() {
    }    

	public Long getId() {
		return id;
	}

	public void setAge(Integer age) {
		this.age = age;
	}


	public Integer getAge() {
		return age;
	}

	public Date getPreviousVisit() {
		return previousVisit;
	}

	public void setPreviousVisit(Date previousVisit) {
		this.previousVisit = previousVisit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    
    
	
}
