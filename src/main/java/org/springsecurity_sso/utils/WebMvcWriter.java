package org.springsecurity_sso.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;

public class WebMvcWriter {

    public static void writeTo(HttpServletResponse response , Object bean){
       try {
           response.setStatus(HttpServletResponse.SC_FORBIDDEN);
           response.setContentType("application/json;charset=utf-8");
           response.getWriter().write(new ObjectMapper().writeValueAsString(bean));
       }catch (Exception e){
          e.printStackTrace();
       }
    }

}
