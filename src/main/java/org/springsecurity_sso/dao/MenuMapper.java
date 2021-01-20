package org.springsecurity_sso.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springsecurity_sso.model.entity.Menu;

import java.util.List;

/**
 * @author HHY
 * @date 2020-01-19
 */
@Repository
@Mapper
public interface MenuMapper {

    /**
     * 查询所有接口信息
     * @return List<Menu> 接口集合
     * */
    List<Menu> getAllMenu();


}
