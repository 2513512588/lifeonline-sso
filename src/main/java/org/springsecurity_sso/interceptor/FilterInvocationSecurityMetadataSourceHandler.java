package org.springsecurity_sso.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springsecurity_sso.dao.MenuMapper;
import org.springsecurity_sso.dao.RoleMapper;
import org.springsecurity_sso.model.entity.Menu;
import org.springsecurity_sso.model.entity.Role;

import java.util.Collection;
import java.util.List;

/**
 * @author HHY
 * FilterInvocationSecurityMetadataSource（权限资源过滤器接口）继承了 SecurityMetadataSource（权限资源接口）
 * Spring Security是通过SecurityMetadataSource来加载访问时所需要的具体权限；Metadata是元数据的意思。
 * 自定义权限资源过滤器，实现动态的权限验证
 * 它的主要责任就是当访问一个url时，返回这个url所需要的访问权限
 */
@Component
public class FilterInvocationSecurityMetadataSourceHandler implements FilterInvocationSecurityMetadataSource {

    private MenuMapper menuMapper;

    private RoleMapper roleMapper;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    public void setMenuMapper(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    @Autowired
    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        List<Menu> list = menuMapper.getAllMenu();
        for (Menu menu : list){
            if (antPathMatcher.match(requestUrl, menu.getPath())){
                List<Role> roles = roleMapper.getRolesByMenuId(menu.getId());
                String [] values = new String[roles.size()];
                for (int i = 0 ; i<values.length ; i++){
                    values[i] = roles.get(i).getNameEn();
                }
                return SecurityConfig.createList(values);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
