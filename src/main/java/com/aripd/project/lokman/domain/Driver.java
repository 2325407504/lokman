package com.aripd.project.lokman.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "driver")
public class Driver {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private boolean active;

	private String firstName;
	private String lastName;
	private String phonenumber;

	public String getFullname() {
		return String.format("%s %s", firstName, lastName);
	}

	@OneToMany(mappedBy = "driver")
	private Collection<FMS> fmss;

	public Driver() {
		fmss = new ArrayList<FMS>();
	}

	public Long getId() {
		return id;
	}

	public boolean isActive() {
		return active;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public Collection<FMS> getFmss() {
		return fmss;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public void setFmss(Collection<FMS> fmss) {
		this.fmss = fmss;
	}

}
