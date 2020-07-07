package com.andy.wealth.controller;

import com.andy.wealth.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HelloWebFluxController {

    @GetMapping("/hello")
    public String getUserName() {
        return "Andy";
    }

    @GetMapping("/user")
    public Mono<User> getUser() {
        User user = new User();
        user.setAge(34);
        user.setName("Andy");
        return Mono.just(user);
    }
}
