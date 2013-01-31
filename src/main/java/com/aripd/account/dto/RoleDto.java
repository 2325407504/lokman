package com.aripd.account.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * A DTO object which is used as a form object in create and edit role.
 * 
 * @author aripd.com
 */
public class RoleDto {

	private Long id;
	
	@NotEmpty
	@Size(min = 1, max = 100)
	@Pattern(regexp = "[a-z-A-Z]*", message = "First name has invalid characters")
	private String code;

	private String name;

	public RoleDto() {
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

}
