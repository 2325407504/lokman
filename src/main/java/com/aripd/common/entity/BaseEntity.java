package com.aripd.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.aripd.common.utils.ARIPDJodaDateTimeSerializer;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = -343366475114401897L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JsonSerialize(using = ARIPDJodaDateTimeSerializer.class)
	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	@Column(columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
	private DateTime createdAt;
	
	@JsonSerialize(using = ARIPDJodaDateTimeSerializer.class)
	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	@Column(columnDefinition = "TIMESTAMP", nullable = false, updatable = true)
	private DateTime updatedAt;
	
    @PreUpdate
    public void preUpdate() {
        updatedAt = new DateTime();
    }
    
    @PrePersist
    public void prePersist() {
        DateTime now = new DateTime();
        createdAt = now;
        updatedAt = now;
    }
    
	public DateTime getCreatedAt() {
		return createdAt;
	}

	public DateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt(DateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public Long getId() {
		return id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
