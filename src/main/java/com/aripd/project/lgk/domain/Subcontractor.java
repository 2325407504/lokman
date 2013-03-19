package com.aripd.project.lgk.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.aripd.common.entity.BaseEntity;

@Entity
@Table(name = "subcontractor", uniqueConstraints = @UniqueConstraint(columnNames = {"region_id", "name"}))
public class Subcontractor extends BaseEntity {

	private static final long serialVersionUID = -7548528652541560063L;

	private String name;

	@ManyToOne
	private Region region;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
