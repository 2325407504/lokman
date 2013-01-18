package com.aripd.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull(message = "First name is compulsory")
	@NotBlank(message = "First name is compulsory")
	@Pattern(regexp = "[a-z-A-Z]*", message = "First name has invalid characters")
	private String type;

	private String number;

	public Long getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public String getNumber() {
		return number;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}