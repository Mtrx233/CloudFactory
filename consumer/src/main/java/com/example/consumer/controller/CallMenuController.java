package com.example.consumer.controller;


import com.example.consumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CallMenuController {
    @Autowired
    UserService userService;


    @RequestMapping(value = "/menus",method = RequestMethod.GET)
    public String getAllMenus(int kind){
        return userService.getAllMenus(kind);
    }
}
