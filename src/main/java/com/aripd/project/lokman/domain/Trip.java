package com.aripd.project.lokman.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import com.aripd.account.domain.Account;
import com.aripd.common.entity.BaseEntity;

@Entity
@Table(name = "trip")
public class Trip extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JsonIgnore
	private Account account;

	@ManyToOne
	@JsonIgnore
	private Truck truck;

	@ManyToOne
	@JsonIgnore
	private Driver driver;

	private String startingPoint;

	private Integer startingKm;

	private String startingTime;

	private String endingPoint;

	private Integer endingKm;

	private String endingTime;

	private Integer loadWeightInTonne;

	@Column(columnDefinition = "TEXT", nullable = true)
	private String remark;

	// @Temporal(TemporalType.DATE)
	@DateTimeFormat(style = "S-")
	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	@Column(columnDefinition = "DATE", nullable = false)
	private DateTime publishedAt;

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

	public String getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(String startingTime) {
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

	public String getEndingTime() {
		return endingTime;
	}

	public void setEndingTime(String endingTime) {
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

	public DateTime getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(DateTime publishedAt) {
		this.publishedAt = publishedAt;
	}

}
