package com.example.consumer.service;


import com.example.consumer.bean.QueryInfo;
import com.example.consumer.bean.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@FeignClient(name="user-provider",contextId = "user",fallbackFactory = com.example.consumer.fallback.UserServiceFallbackFactory.class)
public interface UserService {

    @RequestMapping(value="/login")
    String login(@RequestParam("account")String account , @RequestParam("password")String password , @RequestParam("code")String code);

    @RequestMapping(value="/allUsers")
    String getUserList(@SpringQueryMap QueryInfo queryInfo);

    @RequestMapping(value="/addUser",method = RequestMethod.POST)
    public String addUser(@RequestBody User user);

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("id")int id);

    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    public String updateUser(@RequestBody User user);

    @RequestMapping("/isExist")
    public String isExist(@RequestParam("email")String email ,@RequestParam("account")String account);

    @RequestMapping("/ensureUser")
    public String ensureUser(@RequestParam("time")String time);

    @RequestMapping("/changePassword")
    public String changePassword(@RequestParam("password")String password,@RequestParam("account")String account,@RequestParam("time")String time);


    @RequestMapping("/menus")
    public String getAllMenus(@RequestParam("kind")int kind);






}
