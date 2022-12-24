package com.sagor.rating.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(updatable = false)
	private String createdBy;
	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdAt;
	private String updatedBy;
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime updatedAt;
	private Boolean isActive;

	@PrePersist
	public void setPreInsertDate() {
		this.createdAt = LocalDateTime.now();
		this.isActive = true;
	}

	@PreUpdate
	public void setPreUpdateData() {
		this.updatedAt = LocalDateTime.now();
	}

}
