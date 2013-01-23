package com.aripd.project.lokman.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "truck")
public class Truck {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private boolean active;

	@Column(unique = true)
	private String plate;

	@OneToMany(mappedBy = "truck")
	private Collection<FMS> fmss;

	public Truck() {
		fmss = new ArrayList<FMS>();
	}

	public Long getId() {
		return id;
	}

	public boolean isActive() {
		return active;
	}

	public String getPlate() {
		return plate;
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

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public void setFmss(Collection<FMS> fmss) {
		this.fmss = fmss;
	}

}
