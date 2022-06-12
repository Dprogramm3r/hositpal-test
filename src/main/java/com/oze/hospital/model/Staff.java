package com.oze.hospital.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "STAFF")
public class Staff implements Serializable {
	
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
	private Long id;
    
    @Column(name = "UUID")
	private String uuid;

    @Column(name = "NAME")
	private String name;
    
    @Column(name = "REGISTRATION_DATE")
	private LocalDateTime registrationDate;
    
    public Staff() {
    	
    }
    public Staff(String name) {
        this.uuid = UUID.randomUUID().toString();
        this.registrationDate = LocalDateTime.now();
        this.name = name;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUuid() {
		return uuid;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}
    
    
}