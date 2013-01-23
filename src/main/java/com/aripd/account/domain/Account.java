package com.aripd.account.domain;

import static javax.persistence.CascadeType.PERSIST;
import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang.builder.ToStringBuilder.reflectionToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Size(min = 1, max = 100)
	@Column(nullable = true, unique = false)
	//@Pattern(regexp = "[a-z-A-Z]*", message = "First name has invalid characters")
	private String firstName;

	@NotEmpty
	@Size(min = 1, max = 100)
	@Column(nullable = true, unique = false)
	//@Pattern(regexp = "[a-z-A-Z]*", message = "Last name has invalid characters")
	private String lastName;

	@NotNull
	@Size(min = 1, max = 100)
	@Column(nullable = false, unique = false)
	private String password;

	@Column(unique = true)
	@Size(min = 3, max = 8)
	private String username;

	@NotNull
	@Size(min = 1, max = 50)
	@Column(nullable = false, unique = true)
	@Email(message = "It is not a valid format")
	private String email;

	@NotNull
	@Column(nullable = true, unique = false)
	private Boolean isEnabled;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past(message = "Date of birth must be the past")
	@Nullable
	private Date dateOfBirth = null;

	@JoinTable(name = "account_role", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@ManyToMany(cascade = PERSIST)
	private List<Role> roles = new ArrayList<Role>();

	public List<Role> getRoles() {
		// return roles;
		return (roles == null) ? roles = new ArrayList<Role>() : roles;
	}

	// @Transient
	// public List<String> getRoleNames() {
	// List<String> roleNames = new ArrayList<String>();
	//
	// for (Role role : getRoles()) {
	// roleNames.add(role.getRoleName());
	// }
	// return roleNames;
	// }

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

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
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

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public boolean equals(Object obj) {
		return reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return reflectionHashCode(this);
	}

	@Override
	public String toString() {
		return reflectionToString(this);
	}

}
