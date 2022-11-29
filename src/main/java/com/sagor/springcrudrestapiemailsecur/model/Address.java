package com.sagor.springcrudrestapiemailsecur.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cityName;
    private String countryName;
    @ManyToOne //back configuration e bracket e kichu deya lage na
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
