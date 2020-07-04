package com.andy.wealth.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "user_account")
@Data
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    private Long amount;
}
