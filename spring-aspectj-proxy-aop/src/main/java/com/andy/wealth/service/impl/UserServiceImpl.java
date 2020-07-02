package com.andy.wealth.service.impl;

import com.andy.wealth.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    public void save(String userName) {
        System.out.println("Save User " + userName);
    }

    public String getUserName() {
        System.out.println("Get User Name...");
        return "Andy";
    }

    public int getAge() {
        return 35;
    }
}
