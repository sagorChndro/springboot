package com.sagor.course_restapi.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cityName;
    private String countryName;
    @ManyToMany(mappedBy = "addresses")
    private List<Employee> employees = new ArrayList<>();
}
