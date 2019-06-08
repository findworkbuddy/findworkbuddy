package com.findworkbuddy.mainapiservice.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import static java.util.Collections.EMPTY_LIST;
import static java.util.stream.Collectors.toList;

@Data
public class CustomUserDetails implements UserDetails {

    private static final long serialVersionUID = 121L;

    private User user;

    private String email;

    public CustomUserDetails(String email, User user) {
        this.email = email;
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //return user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(toList());
        return EMPTY_LIST;
    }

    @Override
    public String getPassword() {
        return null;
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
