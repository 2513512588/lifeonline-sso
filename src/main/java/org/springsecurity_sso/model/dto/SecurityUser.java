package org.springsecurity_sso.model.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class SecurityUser extends User {

    private final org.springsecurity_sso.model.entity.User user;

    public SecurityUser(org.springsecurity_sso.model.entity.User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), authorities);
        this.user = user;
    }

    public org.springsecurity_sso.model.entity.User getUser() {
        return user;
    }
}
