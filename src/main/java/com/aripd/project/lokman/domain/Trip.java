package com.aripd.project.lokman.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.aripd.account.domain.Account;
import com.aripd.common.entity.BaseEntity;
import com.aripd.common.utils.ARIPDJodaDateTimeSerializer;

@Entity
@Table(name = "trip")
public class Trip extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -8215609876889575062L;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(nullable = false, insertable = true, updatable = false)
	private Account account;

	@ManyToOne
	private Truck truck;

	@ManyToOne
	private Driver driver;

	private String startingPoint;

	private Integer startingKm;

	@JsonSerialize(using = ARIPDJodaDateTimeSerializer.class)
	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	@Column(columnDefinition = "TIMESTAMP")
	private DateTime startingTime;

	private String endingPoint;

	private Integer endingKm;

	@JsonSerialize(using = ARIPDJodaDateTimeSerializer.class)
	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	@Column(columnDefinition = "TIMESTAMP")
	private DateTime endingTime;

	private Integer loadWeightInTonne;

	@Column(columnDefinition = "TEXT", nullable = true)
	private String remark;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Truck getTruck() {
		return truck;
	}

	public void setTruck(Truck truck) {
		this.truck = truck;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public String getStartingPoint() {
		return startingPoint;
	}

	public void setStartingPoint(String startingPoint) {
		this.startingPoint = startingPoint;
	}

	public Integer getStartingKm() {
		return startingKm;
	}

	public void setStartingKm(Integer startingKm) {
		this.startingKm = startingKm;
	}

	public DateTime getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(DateTime startingTime) {
		this.startingTime = startingTime;
	}

	public String getEndingPoint() {
		return endingPoint;
	}

	public void setEndingPoint(String endingPoint) {
		this.endingPoint = endingPoint;
	}

	public Integer getEndingKm() {
		return endingKm;
	}

	public void setEndingKm(Integer endingKm) {
		this.endingKm = endingKm;
	}

	public DateTime getEndingTime() {
		return endingTime;
	}

	public void setEndingTime(DateTime endingTime) {
		this.endingTime = endingTime;
	}

	public Integer getLoadWeightInTonne() {
		return loadWeightInTonne;
	}

	public void setLoadWeightInTonne(Integer loadWeightInTonne) {
		this.loadWeightInTonne = loadWeightInTonne;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
