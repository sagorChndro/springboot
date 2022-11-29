package com.sagor.springcrudrestapiemailsecur.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class User extends BaseModel{
    private String name;
    private String email;
    @NaturalId // mane unique id
    private String username;
    private String password;
    // User class er sathe Role er relation hocce ManyToMany
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name="role_id"))
    // Data annotation amader toString provide kore kinto amra chacchi j List<Role> ta
    // toString er baire thakbe tai amra @ToString.Exclude annotation ta babohar korbo
    @ToString.Exclude
    List<Role> roles;
}
