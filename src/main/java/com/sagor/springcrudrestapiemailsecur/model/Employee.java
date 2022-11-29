package com.sagor.springcrudrestapiemailsecur.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name= "address_id", referencedColumnName = "id")
//    private Address address;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee")//obossoi back configuration korte hobe
    private List<Address> addresses;
}
