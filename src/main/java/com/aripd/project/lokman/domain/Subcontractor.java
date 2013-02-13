package com.aripd.project.lokman.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aripd.common.entity.BaseEntity;

@Entity
@Table(name = "subcontractor")
public class Subcontractor extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -7548528652541560063L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
