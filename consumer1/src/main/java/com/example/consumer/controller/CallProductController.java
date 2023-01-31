package com.example.consumer.controller;


import com.example.consumer.bean.Product;
import com.example.consumer.bean.QueryInfo;
import com.example.consumer.service.ProductService;
import com.example.consumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

@RestController
public class CallProductController {
    @Autowired
    ProductService productService;


    @RequestMapping("/allProduct")
    public String getUserList(@SpringQueryMap QueryInfo queryInfo) {
        System.out.println(queryInfo.toString());
        return productService.getUserList(queryInfo);
    }

    @RequestMapping("/ProductIndex")
    public String getList(){
        return productService.getList();
    }

    @RequestMapping("/getPro")
    public String getPro(@RequestParam("id")int id){
        return productService.getPro(id);
    }

    @RequestMapping("/deleteProduct")
    public String deleteUser(@RequestParam("id") int id){
        return productService.deleteUser(id);
    }

    @RequestMapping(value = "/updateProduct",method = RequestMethod.POST)
    public String updateUser(@RequestBody Product factory){
        return productService.updateUser(factory);
    }


    @RequestMapping(value ="/addP",method = RequestMethod.POST)
    public String addUser(@RequestBody Product user){
        return productService.addUser(user);
    }




}
