package com.example.UserProvider.controller;

import com.alibaba.fastjson.JSON;

import com.example.UserProvider.bean.Factory;
import com.example.UserProvider.bean.MainMenu;
import com.example.UserProvider.dao.MenuDao;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class MenuController {

    @Autowired
    MenuDao menuDao;
    @HystrixCommand(fallbackMethod = "fallbackMenus")
    @RequestMapping("/menus")
    public String getAllMenus(int kind){
        HashMap<String ,Object> data =new HashMap<>();
        int flag=404;//错误
        System.out.println(kind);
        List<MainMenu> mainMenus=menuDao.getMenus(kind);
        if(mainMenus!=null){
            data.put("menus",mainMenus);
            data.put("flag",200);
        }else {
            data.put("flag",404);
        }
        return JSON.toJSONString(data);
    }

    public String fallbackMenus(int kind){
        HashMap<String ,Object> data =new HashMap<>();
        data.put("flag",404);
        return JSON.toJSONString(data);
    }
}
