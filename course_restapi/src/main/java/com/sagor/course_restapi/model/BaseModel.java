package com.sagor.course_restapi.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Data
public abstract class BaseModel implements Serializable {
    public static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    public void setPreInsertDate(){
        this.createdAt = new Date();
        this.isActive = true;
    }

    @PreUpdate
    public void setPreUpdateDate(){
        this.updatedAt=new Date();
    }
}
