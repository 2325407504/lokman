package com.aripd.account.domain;

import static javax.persistence.CascadeType.PERSIST;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * An entity class which contains the information of a single account.
 * 
 * @author aripd.com
 */
@Entity
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "creation_time", nullable = false)
	private Date creationTime;

	@Column(name = "modification_time", nullable = false)
	private Date modificationTime;

	@Column(nullable = true, unique = false)
	private String firstName;

	@Column(nullable = true, unique = false)
	private String lastName;

	@Column(nullable = false, unique = false)
	private String password;

	@Column(unique = true)
	private String username;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = true, unique = false)
	private Boolean isEnabled = false;

	@Temporal(TemporalType.DATE)
	private Date dateOfBirth = null;

	@JoinTable(name = "account_role", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@ManyToMany(cascade = PERSIST)
	private List<Role> roles = new ArrayList<Role>();

	public List<Role> getRoles() {
		// return roles;
		return (roles == null) ? roles = new ArrayList<Role>() : roles;
	}

	/**
     * Gets a builder which is used to create Account objects.
     * @param firstName   The first name of the created account.
     * @param lastName    The last name of the created account.
	 * @param email       The email of the created account.
	 * @param username    The username of the created account.
	 * @param password    The password of the created account.
	 * @param dateOfBirth The dateOfBirth of the created account.
     * @return  A new Builder instance.
     */
    public static Builder getBuilder(String firstName, String lastName, String email, String username, String password, Date dateOfBirth) {
        return new Builder(firstName, lastName, email, username, password, dateOfBirth);
    }
    
    /**
     * Gets the full name of the account.
     * @return  The full name of the account.
     */
    @Transient
    public String getName() {
        StringBuilder name = new StringBuilder();
        
        name.append(firstName);
        name.append(" ");
        name.append(lastName);
        
        return name.toString();
    }

    public void update(String firstName, String lastName, String email, String username, String password, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }
    
    @PreUpdate
    public void preUpdate() {
        modificationTime = new Date();
    }
    
    @PrePersist
    public void prePersist() {
        Date now = new Date();
        creationTime = now;
        modificationTime = now;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    /**
     * A Builder class used to create new Account objects.
     */
    public static class Builder {
        Account built;

        /**
         * Creates a new Builder instance.
         * @param firstName   The first name of the created Account object.
         * @param lastName    The last name of the created Account object.
         * @param email       The email of the created Account object.
         * @param username    The username of the created Account object.
         * @param password    The password of the created Account object.
         * @param dateOfBirth The date of birth of the created Account object.
         */
        Builder(String firstName, String lastName, String email, String username, String password, Date dateOfBirth) {
            built = new Account();
            built.firstName = firstName;
            built.lastName = lastName;
            built.email = email;
            built.username = username;
            built.password = DigestUtils.md5Hex(password);
            built.dateOfBirth = dateOfBirth;
        }

        /**
         * Builds the new Account object.
         * @return  The created Account object.
         */
        public Account build() {
            return built;
        }
    }
    
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	/**
     * This setter method should only be used by unit tests.
     * @param id
     */
	public /*protected*/ void setId(Long id) {
        this.id = id;
    }
    
	public Long getId() {
		return id;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public Date getModificationTime() {
		return modificationTime;
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

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
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

}
