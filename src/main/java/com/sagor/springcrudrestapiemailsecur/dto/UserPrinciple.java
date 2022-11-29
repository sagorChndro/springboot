package com.sagor.springcrudrestapiemailsecur.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sagor.springcrudrestapiemailsecur.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Data
public class UserPrinciple implements UserDetails {
    // eita kiser maddhome authenticate korbe seitar jonno amader
    // property gula declare korbo
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    // authenticityr jonno ekta user er onekgula roles thakte pare ei
    // karone amra Collection use korbo generics er maddhome
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrinciple(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    // eikhane jeijei property gula ache like username, password etc eigula ashbe
    // amader User class theke. User theke amra UserPrinciple create korar mechanism ba
    // method amra eikhane dibo
    public static UserPrinciple create(User user){
        // ei object ta create korte gele amder List<GrantedAuthority> of GrantedAuthority
        // lage so amra seita user theke create korbo
        try{
            List<GrantedAuthority> authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
            return new UserPrinciple(user.getId(), user.getUsername(), user.getPassword(), authorities);
        }catch (Exception e){
            return null;
        }
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
