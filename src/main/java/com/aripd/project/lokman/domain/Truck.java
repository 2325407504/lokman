package com.aripd.project.lokman.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.aripd.common.entity.BaseEntity;

@Entity(name = "truck")
public class Truck extends BaseEntity {

	private static final long serialVersionUID = 6697864974198990570L;

	private boolean active;

	@Column(unique = true)
	private String plate;

	@OneToMany(mappedBy = "truck")
	private Collection<Trip> trips;

	public Truck() {
		trips = new ArrayList<Trip>();
	}

	public boolean isActive() {
		return active;
	}

	public String getPlate() {
		return plate;
	}

	public Collection<Trip> getFmss() {
		return trips;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public void setFmss(Collection<Trip> trips) {
		this.trips = trips;
	}

}
