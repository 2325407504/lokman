package com.aripd.project.lokman.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import com.aripd.account.domain.Account;
import com.aripd.common.entity.BaseEntity;
import com.aripd.common.utils.ARIPDJodaDateSerializer;

@Entity
@Table(name = "forwarding")
public class Forwarding extends BaseEntity {

	private static final long serialVersionUID = 7142407700649461014L;

	@Column(nullable = false)
	private boolean submitted = false;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(nullable = false, insertable = true, updatable = false)
	private Account account;

	@Column(unique = true)
	private String waybillNo;
	
	private String driver;
	
	private String plate;

	// @Temporal(TemporalType.DATE)
	@JsonSerialize(using = ARIPDJodaDateSerializer.class)
	@DateTimeFormat(style = "S-")
	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	@Column(columnDefinition = "DATE", nullable = false)
	private DateTime startingAt;

	// @Temporal(TemporalType.DATE)
	@JsonSerialize(using = ARIPDJodaDateSerializer.class)
	@DateTimeFormat(style = "S-")
	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	@Column(columnDefinition = "DATE", nullable = false)
	private DateTime endingAt;

	private String endingPoint;
	
	private Integer loadWeightInTonne;

	private Double shippingCost;
	
	@ManyToOne
	private Subcontractor subcontractor;

	@ManyToOne
	private Quota quota;

	@JsonIgnore
	@OneToMany(mappedBy = "forwarding", cascade = CascadeType.ALL)
	private Set<Uatf> uatfs;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getWaybillNo() {
		return waybillNo;
	}

	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public DateTime getStartingAt() {
		return startingAt;
	}

	public void setStartingAt(DateTime startingAt) {
		this.startingAt = startingAt;
	}

	public DateTime getEndingAt() {
		return endingAt;
	}

	public void setEndingAt(DateTime endingAt) {
		this.endingAt = endingAt;
	}

	public Integer getLoadWeightInTonne() {
		return loadWeightInTonne;
	}

	public void setLoadWeightInTonne(Integer loadWeightInTonne) {
		this.loadWeightInTonne = loadWeightInTonne;
	}

	public Double getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(Double shippingCost) {
		this.shippingCost = shippingCost;
	}

	public Subcontractor getSubcontractor() {
		return subcontractor;
	}

	public void setSubcontractor(Subcontractor subcontractor) {
		this.subcontractor = subcontractor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEndingPoint() {
		return endingPoint;
	}

	public void setEndingPoint(String endingPoint) {
		this.endingPoint = endingPoint;
	}

	public Quota getQuota() {
		return quota;
	}

	public void setQuota(Quota quota) {
		this.quota = quota;
	}

	public Set<Uatf> getUatfs() {
		return uatfs;
	}

	public void setUatfs(Set<Uatf> uatfs) {
		this.uatfs = uatfs;
	}

	public boolean isSubmitted() {
		return submitted;
	}

	public void setSubmitted(boolean submitted) {
		this.submitted = submitted;
	}

}
