package com.econovation.recruit.api.config.security;

import java.util.Collection;
import java.util.Collections;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@AllArgsConstructor
@Getter
public class AuthDetails implements UserDetails {
    private static final String ROLE_PREFIX = "ROLE_";

    private String idpId;

    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String authority = role.startsWith(ROLE_PREFIX) ? role : ROLE_PREFIX + role;
        return Collections.singleton(new SimpleGrantedAuthority(authority));
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return idpId;
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
