package com.aripd.account.dto;

import java.util.Date;

import javax.annotation.Nullable;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * A DTO object which is used as a form object in create and edit account forms.
 * 
 * @author aripd.com
 */
public class AccountDto {

	private Long id;

	@NotEmpty
	@Size(min = 1, max = 100)
	@Pattern(regexp = "[a-z-A-Z]*", message = "First name has invalid characters")
	private String firstName;

	@NotEmpty
	@Size(min = 1, max = 100)
	@Pattern(regexp = "[a-z-A-Z]*", message = "First name has invalid characters")
	private String lastName;

	@NotEmpty
	@Size(min = 3, max = 12)
	private String password;

	@NotEmpty
	@Size(min = 3, max = 12)
	private String username;

	@NotEmpty
	@Email(message = "It is not a valid format")
	private String email;

	@Nullable
	@DateTimeFormat(iso=ISO.DATE)
	@Past(message = "Date of birth must be the past")
	private Date dateOfBirth = null;

	public AccountDto() {
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}
