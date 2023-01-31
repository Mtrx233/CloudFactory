package com.example.consumer.bean;

import lombok.Data;

@Data
public class User {
    private Integer user_id;
    private Integer user_kind_id;
    private String account;
    private String password;
    private String user_name;
    private String kind;
    private String email;
    private String phone;
    private String f_brief;
    private String f_name;
}
