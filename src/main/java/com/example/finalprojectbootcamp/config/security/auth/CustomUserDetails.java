package com.example.finalprojectbootcamp.config.security.auth;

import com.example.finalprojectbootcamp.core.base.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
public class CustomUserDetails implements UserDetails {

private String email ;
private String password ;
List<SimpleGrantedAuthority> roles = new ArrayList<>();

    public CustomUserDetails(User user) {
        this.email = user.getEmail() ;
        this.password = user.getPassword() ;
        roles.add(new SimpleGrantedAuthority("ROLE_"+ user.getDiscriminatorValue())) ;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles ;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
