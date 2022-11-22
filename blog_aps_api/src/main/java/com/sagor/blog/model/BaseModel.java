package com.sagor.blog.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@MappedSuperclass
@Data
public class BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(updatable = false)
	private String createdby;
	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	private String updatedBy;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	public Boolean isActive;

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
