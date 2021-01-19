package org.springsecurity_sso.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springsecurity_sso.model.entity.User;

/**
 * @author HHY
 */
@Repository
@Mapper
public interface UserDao {

    /**
    * 通过用户名查找用户
    * @return User对象
    * @param username 用户名
    */
    User findUserByUsername(String username);

}
