package org.springsecurity_sso.interceptor;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author HHY
 * 有了权限资源(FilterInvocationSecurityMetadataSource)，知道了当前访问的url需要的具体权限，接下来就是决策当前的访问是否能通过权限验证了
 */
@Component
public class AccessDecisionHandler implements AccessDecisionManager {

    /**
     * @param authentication 包含了当前的用户信息，包括拥有的权限,即之前UserDetailsService登录时候存储的用户对象
     * @param o 就是FilterInvocation对象，可以得到request等web资源。
     * @param collection 是本次访问需要的权限。即上一步的 FilterInvocationSecurityMetadataSourceHandler 中查询核对得到的权限列表
     * */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        Iterator<ConfigAttribute> iterator = collection.iterator();
        //e -> e.getAuthority() 当传入对象 直接调用方法时可以使用糖性语法
        List<String> authentications = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        while (iterator.hasNext()){
            ConfigAttribute item = iterator.next();
            //我包含的角色中没有访问接口需要的角色
            if (!authentications.contains(item.getAttribute())){
                throw new AccessDeniedException("权限不足!");
            }
        }
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
