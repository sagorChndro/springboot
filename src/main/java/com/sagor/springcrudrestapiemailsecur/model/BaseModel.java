package com.sagor.springcrudrestapiemailsecur.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@MappedSuperclass // nicher field gula amader database er sathe relation tai
                   // parent class er j property gula ache segula k shey map kore nibe
@Data
public abstract class BaseModel implements Serializable {
    public static final long serialVersionUID =1L;
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

    //kono ekta entity jokhon save hobe tokhn shey by default createdAt k new data dara
    // save korbe abng isaActive t ke shey true korbe

    // creat korar jonno @PrePersist use korte hobe karon amader database e jokhn new
    // row insert hobe abong amra jokhn kono insert query chalabo tokhn amra bole dibo
    // value konta hobe seita shey set kore dey like eikhane createdAt e new Date()
    // bole dichi tai sehy new date ta insert korbe
    @PrePersist
    public void setPreInsertData(){
        this.createdAt = new Date();
        this.isActive = true;
    }

    @PreUpdate
    public void setPreUpdateData(){
        this.updatedAt = new Date();
    }
}
