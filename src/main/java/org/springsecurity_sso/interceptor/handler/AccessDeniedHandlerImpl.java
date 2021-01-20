package org.springsecurity_sso.interceptor.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springsecurity_sso.utils.R;
import org.springsecurity_sso.utils.WebMvcWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author HHY
 * 拒签处理器
 */
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        WebMvcWriter.writeTo(httpServletResponse,R.error().message(e.getMessage()));
    }

}
