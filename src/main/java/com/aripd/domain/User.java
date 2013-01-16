package com.aripd.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String firstname;
	private String lastname;
	
	@Column(unique=true)
	private String username;
	private String password;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(
            name="user_roles",
            joinColumns = @JoinColumn( name="user_id"),
            inverseJoinColumns = @JoinColumn( name="role_id")
    )
	private Set<Role> roles;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(
            name="user_addresses",
            joinColumns = @JoinColumn( name="user_id"),
            inverseJoinColumns = @JoinColumn( name="address_id")
    )
	private Set<Address> addresses;

	public Long getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

}
