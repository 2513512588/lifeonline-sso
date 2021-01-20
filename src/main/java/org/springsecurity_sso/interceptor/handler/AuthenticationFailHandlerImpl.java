package org.springsecurity_sso.interceptor.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springsecurity_sso.utils.R;
import org.springsecurity_sso.utils.WebMvcWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author HHY
 * 登录失败处理器
 */
public class AuthenticationFailHandlerImpl implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        WebMvcWriter.writeTo(httpServletResponse , R.error().message("账号或者密码错误!"));
    }

}
