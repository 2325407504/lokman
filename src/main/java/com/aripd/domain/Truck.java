package com.aripd.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="truck")
public class Truck {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique=true)
	private String plate;

	public Long getId() {
		return id;
	}

	public String getPlate() {
		return plate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

}
