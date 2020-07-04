package com.andy.wealth.controller;

import com.andy.wealth.service.UserAccountInterface;
import com.andy.wealth.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class UserAccountController {

    @Autowired
    private UserAccountInterface userAccountService;

    @GetMapping("/transfer")
    public String transfer(
            @RequestParam Long from,
            @RequestParam Long to,
            @RequestParam Long amount
    ) {
        userAccountService.transfer(from, to, amount);
        return "success";
    }
}
