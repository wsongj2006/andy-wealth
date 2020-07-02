package com.andy.wealth;

import com.andy.wealth.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class AppTest {
    public static void main(String[] args) {
        ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:spring-aspectj-proxy.xml");
        UserService userService = (UserService) ac.getBean("userServiceImpl");
        //userService.save("Andy");
        //userService.getUserName();
        userService.getAge();
    }

}
