package org.springsecurity_sso.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springsecurity_sso.model.entity.User;

public interface UserService extends UserDetailsService {

    Boolean insertUser(User user);

}
