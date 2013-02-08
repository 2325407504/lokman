package com.aripd.project.lokman.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.aripd.common.entity.BaseEntity;

@Entity(name = "driver")
public class Driver extends BaseEntity {

	private boolean active;

	private String firstName;
	private String lastName;
	private String phonenumber;

	public String getFullname() {
		return String.format("%s %s", firstName, lastName);
	}

	@OneToMany(mappedBy = "driver")
	private Collection<Trip> trips;

	public Driver() {
		trips = new ArrayList<Trip>();
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

	public Collection<Trip> getFmss() {
		return trips;
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

	public void setFmss(Collection<Trip> trips) {
		this.trips = trips;
	}

}
