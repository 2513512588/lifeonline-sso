package org.springsecurity_sso.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springsecurity_sso.model.entity.User;

/**
 * @author HHY
 * @date 2020-01-19
 */
@Repository
@Mapper
public interface UserMapper {

     /**
     * 通过用户名查找用户
     *
     * @param username 用户名
     * @return User对象
     */
    User getUserByUsername(String username);

    /**
     * 添加用户
     * @param user 用户对象
     * @return rows 受影响的行数
     * */
    Integer insertUser(User user);

}
