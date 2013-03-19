package com.aripd.project.lgk.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
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

	@Column(unique = true)
	private String name;
	
	private String phonenumber;

	@JsonIgnore
	@OneToMany(mappedBy = "driver", fetch = FetchType.EAGER)
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
