package org.springsecurity_sso.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springsecurity_sso.model.entity.Role;

import java.util.List;


/**
 * @author HHY
 * @date 2021-01-19
 */
@Repository
@Mapper
public interface RoleMapper {

    /**
     * 通过用户id查找角色集合
     * @param userId 用户id
     * @return 角色集合
     * */
    List<Role> getRolesByUserId(Long userId);

    /**
     * 通过api接口查找需要的角色
     * @param menuId 接口id
     * @return 角色集合
     * */
    List<Role> getRolesByMenuId(Long menuId);



}
