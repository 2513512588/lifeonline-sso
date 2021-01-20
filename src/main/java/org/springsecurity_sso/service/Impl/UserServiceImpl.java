package org.springsecurity_sso.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springsecurity_sso.dao.RoleMapper;
import org.springsecurity_sso.dao.UserMapper;
import org.springsecurity_sso.model.dto.SecurityUser;
import org.springsecurity_sso.model.entity.User;
import org.springsecurity_sso.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HHY
 */
@Service
public class UserServiceImpl implements UserService {


    private UserMapper userMapper;
    private RoleMapper roleMapper;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserDao(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setRoleDao(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.getUserByUsername(s);
        if (user == null){
            throw new UsernameNotFoundException("用户不存在!");
        }
        List<GrantedAuthority> collection = new ArrayList<>();
        roleMapper.getRolesByUserId(user.getId()).forEach(role ->{
            collection.add(new SimpleGrantedAuthority(role.getNameEn()));
        });
        return new SecurityUser(user,collection);
    }

    @Override
    public Boolean insertUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.insertUser(user) != 0;
    }
}
