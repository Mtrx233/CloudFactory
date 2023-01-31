package com.example.consumer.controller;


import com.example.consumer.bean.QueryInfo;
import com.example.consumer.bean.User;
import com.example.consumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

@RestController
public class CallUserController {

    @Autowired
    UserService userService;

    @RequestMapping(value="/a")
    String test(){
        return "yes";
    }

    @RequestMapping(value="/login")
    String login(String account , String password ,String code){
        return userService.login(account,password,code);
    }

    @RequestMapping(value="/allUsers",method = RequestMethod.GET)
    String getUserList(QueryInfo queryInfo){
        return userService.getUserList(queryInfo);
    }

    @RequestMapping(value="/addUser",method = RequestMethod.POST)
    public String addUser(@RequestBody User user){
        System.out.println(user.toString());
        return  userService.addUser(user);
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("id")int id){
        return  userService.deleteUser(id);
    }

    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    public String updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @RequestMapping("/isExist")
    public String isExist(@RequestParam("email")String email ,@RequestParam("account")String account) {
        return userService.isExist(email,account);
    }

    @RequestMapping("/ensureUser")
    public String ensureUser(@RequestParam("time")String time){
        return userService.ensureUser(time);
    }

    @RequestMapping("/changePassword")
    public String changePassword(@RequestParam("password")String password,@RequestParam("account")String account,@RequestParam("time")String time){
        return userService.changePassword(password,account,time);
    }
}
