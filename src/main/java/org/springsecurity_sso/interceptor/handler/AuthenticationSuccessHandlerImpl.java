package org.springsecurity_sso.interceptor.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springsecurity_sso.model.dto.SecurityUser;
import org.springsecurity_sso.utils.R;
import org.springsecurity_sso.utils.WebMvcWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author HHY
 * 登录成功处理器
 */
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        WebMvcWriter.writeTo(httpServletResponse , R.ok().message("登录成功!").data("user",securityUser.getUser()));
    }
}
