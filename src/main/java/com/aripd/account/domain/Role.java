package com.aripd.account.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.aripd.common.entity.BaseEntity;

@Entity
@Table(name = "role")
public class Role extends BaseEntity {

	private static final long serialVersionUID = 1080604793302174491L;

	@Column(nullable = false, unique = true)
	private String code;

	private String name;

	public Role() {
	}

	public Role(String code) {
		this.code = code;
	}

	public Role(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

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
