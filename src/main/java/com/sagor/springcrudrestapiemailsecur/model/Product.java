package com.sagor.springcrudrestapiemailsecur.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Entity
public class Product extends BaseModel{
    private String name;
    private Double price;
    private String description;
}
