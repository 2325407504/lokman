package com.aripd.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

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

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@NotNull
	private Date publishedAt;

	@DateTimeFormat(style = "SS")
	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	private DateTime updatedAt;

	@DateTimeFormat(style = "SS")
	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	private DateTime createdAt;

	@PreUpdate
	public void setPreUpdate() {
		setUpdatedAt(new DateTime());
	}

	public FMS() {
		setCreatedAt(new DateTime());
		setUpdatedAt(new DateTime());
	}

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

	public Date getPublishedAt() {
		return publishedAt;
	}

	public DateTime getUpdatedAt() {
		return updatedAt;
	}

	public DateTime getCreatedAt() {
		return createdAt;
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

	public void setPublishedAt(Date publishedAt) {
		this.publishedAt = publishedAt;
	}

	public void setUpdatedAt(DateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

}
