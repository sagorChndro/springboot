package com.sagor.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(updatable = false)
	private String createdBy;
	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	private String updatedBy;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	private Boolean isActive;

	@PrePersist
	public void setPreInsertDate() {
		this.createdAt = new Date();
		this.isActive = true;
	}

	@PreUpdate
	public void setPreUpdateDate() {
		this.updatedAt = new Date();
	}

}
