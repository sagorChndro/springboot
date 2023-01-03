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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ei class e amra seisob property gula rakhbo jeigula amr
	// sobgula model class er moddhe commonly use hobe like id ta amdare prottek ta
	// model er moddhe lagbe
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(updatable = false)
	private String createdBy;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date createdAt;
	private String updatedBy;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	private Boolean isActive;

	@PrePersist
	public void setPrePersist() {
		this.createdAt = new Date();
		this.isActive = true;
	}

	@PreUpdate
	public void setPreUpdate() {
		this.updatedAt = new Date();
	}

}
