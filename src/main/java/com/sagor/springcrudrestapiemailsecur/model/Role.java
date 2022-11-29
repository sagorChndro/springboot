package com.sagor.springcrudrestapiemailsecur.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Role extends BaseModel{
    @NaturalId // mane unique id
    private String name;
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore //future e jate kono dhoroner stack over flow error na dey tai eita use hoiche
    List<User> users = new ArrayList<>();

}
