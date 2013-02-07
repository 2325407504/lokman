package com.aripd.account.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.aripd.common.entity.BaseEntity;

/**
 * An entity class which contains the information of a single role.
 * 
 * @author aripd.com
 */
@Entity
@Table(name = "role")
public class Role extends BaseEntity {

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

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

}
