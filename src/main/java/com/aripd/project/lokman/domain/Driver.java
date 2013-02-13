package com.aripd.project.lokman.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.aripd.common.entity.BaseEntity;

@Entity
@Table(name = "driver")
public class Driver extends BaseEntity {

	private static final long serialVersionUID = -6849484656846432441L;

	public Driver() {
		trips = new ArrayList<Trip>();
	}
	
	private boolean active;

	private String firstName;
	private String lastName;
	private String phonenumber;

	public String getFullname() {
		return String.format("%s %s", firstName, lastName);
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "driver", fetch = FetchType.EAGER)
	private List<Trip> trips;

	public void addTrip(Trip trip) {
		if (trip == null) {
			return;
		}
		else {
			if (trips == null) {
				trips = new ArrayList<Trip>();
			}
			trips.add(trip);
			trip.setDriver(this);
		}
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Trip> getTrips() {
		return trips;
	}

	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}

}
