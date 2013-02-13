package com.aripd.project.lokman.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.aripd.common.entity.BaseEntity;

@Entity
@Table(name = "truck")
public class Truck extends BaseEntity {

	private static final long serialVersionUID = 6697864974198990570L;

	public Truck() {
		trips = new ArrayList<Trip>();
	}
	
	private boolean active;

	@Column(unique = true)
	private String plate;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "truck", fetch = FetchType.EAGER)
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
			trip.setTruck(this);
		}
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
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
