package com.aripd.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity(name = "fms")
public class FMS {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@ManyToOne
	private Truck truck;

	@NotNull
	@ManyToOne
	private Driver driver;

	@NotNull
	private Integer startingKm;
	
	@NotNull
	private Integer endingKm;

	private Integer loadTon;

	@NotNull
	@Min(value = 0, message = "Length must be greater than or equal to 3")
	@Max(value = 100, message = "Length must be less than or equal to 8")
	private Double fuelTL;
	
	@NotNull
	private Integer fuelLiter;

	private Integer periodicMaintenance;
	private Integer breakDownKm;

	@Column(columnDefinition = "TEXT")
	private String remark;

	public Long getId() {
		return id;
	}

	public Truck getTruck() {
		return truck;
	}

	public Driver getDriver() {
		return driver;
	}

	public Integer getStartingKm() {
		return startingKm;
	}

	public Integer getEndingKm() {
		return endingKm;
	}

	public Integer getLoadTon() {
		return loadTon;
	}

	public Double getFuelTL() {
		return fuelTL;
	}

	public Integer getFuelLiter() {
		return fuelLiter;
	}

	public Integer getPeriodicMaintenance() {
		return periodicMaintenance;
	}

	public Integer getBreakDownKm() {
		return breakDownKm;
	}

	public String getRemark() {
		return remark;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTruck(Truck truck) {
		this.truck = truck;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public void setStartingKm(Integer startingKm) {
		this.startingKm = startingKm;
	}

	public void setEndingKm(Integer endingKm) {
		this.endingKm = endingKm;
	}

	public void setLoadTon(Integer loadTon) {
		this.loadTon = loadTon;
	}

	public void setFuelTL(Double fuelTL) {
		this.fuelTL = fuelTL;
	}

	public void setFuelLiter(Integer fuelLiter) {
		this.fuelLiter = fuelLiter;
	}

	public void setPeriodicMaintenance(Integer periodicMaintenance) {
		this.periodicMaintenance = periodicMaintenance;
	}

	public void setBreakDownKm(Integer breakDownKm) {
		this.breakDownKm = breakDownKm;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
