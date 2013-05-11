package com.aripd.project.lgk.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.aripd.common.entity.BaseEntity;

@Entity
@Table(name = "uatf")
public class Uatf extends BaseEntity {

	private static final long serialVersionUID = 5180174134796884976L;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="forwarding_id")
	private Forwarding forwarding;
	
	private String code;
	private String company;
	private String county;
	private String city;

	private Integer loadWeightInTonne;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getLoadWeightInTonne() {
		return loadWeightInTonne;
	}

	public void setLoadWeightInTonne(Integer loadWeightInTonne) {
		this.loadWeightInTonne = loadWeightInTonne;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Forwarding getForwarding() {
		return forwarding;
	}

	public void setForwarding(Forwarding forwarding) {
		this.forwarding = forwarding;
	}

}
