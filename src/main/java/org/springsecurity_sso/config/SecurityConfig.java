package org.springsecurity_sso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.web.cors.CorsUtils;
import org.springsecurity_sso.interceptor.AccessDecisionHandler;
import org.springsecurity_sso.interceptor.handler.AccessDeniedHandlerImpl;
import org.springsecurity_sso.interceptor.handler.AuthenticationFailHandlerImpl;
import org.springsecurity_sso.interceptor.handler.AuthenticationSuccessHandlerImpl;
import org.springsecurity_sso.service.Impl.UserServiceImpl;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
            .anyRequest().authenticated().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                o.setAccessDecisionManager(new AccessDecisionHandler());
                return o;
            }
        })
            .and().formLogin()
                  .successHandler(new AuthenticationSuccessHandlerImpl())
                  .failureHandler(new AuthenticationFailHandlerImpl())
                  .and()
            .cors().disable().csrf().disable()
            .exceptionHandling().accessDeniedHandler(new AccessDeniedHandlerImpl());

    }
}
