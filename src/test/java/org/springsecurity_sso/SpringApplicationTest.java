package org.springsecurity_sso;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springsecurity_sso.controller.UserController;
import org.springsecurity_sso.model.entity.User;
import org.springsecurity_sso.service.UserService;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringApplicationTest {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private UserController controller;

    @Test
    public void contextLoads(){
        Set<String> noLoginUrlSet = new HashSet<>();
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();// 就是这个
        for (RequestMappingInfo rmi : handlerMethods.keySet()) {
            HandlerMethod handlerMethod = handlerMethods.get(rmi);
            if (handlerMethod.hasMethodAnnotation(RequestMapping.class)) {
                PatternsRequestCondition prc = rmi.getPatternsCondition();
                Set<String> patterns = prc.getPatterns();
                noLoginUrlSet.addAll(patterns);
            }
        }
        log.info("noLoginUrlSet = " + noLoginUrlSet);

        RequestMapping mapping1 = controller.getClass().getMethods().getClass().getAnnotation(RequestMapping.class);

        log.info("mapper {}",mapping1);

    }

    @Autowired
    private UserService userService;

    @Test
    public void registerUser(){
        User user = new User();
        user.setId(4154641515454456578L);
        user.setUsername("admin");
        user.setPassword("123");
        user.setAvatar("default");
        boolean result = userService.insertUser(user);
        log.info("添加用户结果: {}", result);
    }

}
