package com.aripd.account.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * An entity class which contains the information of a single role.
 * 
 * @author aripd.com
 */
@Entity
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "creation_time", nullable = false)
	private Date creationTime;

	@Column(name = "modification_time", nullable = false)
	private Date modificationTime;

	@Column(nullable = false, unique = true)
	private String code;

	private String name;

	public Role() {
	}

	public Role(final String code) {
		this.code = code;
	}

	/**
     * Gets a builder which is used to create Role objects.
     * @param code The code of the created role.
     * @param name  The name of the created role.
     * @return  A new Builder instance.
     */
    public static Builder getBuilder(String code, String name) {
        return new Builder(name, code);
    }

    public void update(String code, String name) {
        this.code = code;
        this.name = name;
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
        Role built;

        /**
         * Creates a new Builder instance.
         * @param code   The first name of the created Account object.
         * @param name    The last name of the created Account object.
         */
        Builder(String code, String name) {
            built = new Role();
            built.code = code;
            built.name = name;
        }

        /**
         * Builds the new Account object.
         * @return  The created Account object.
         */
        public Role build() {
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

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

}
