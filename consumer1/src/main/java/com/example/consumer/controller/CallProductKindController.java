package com.example.consumer.controller;


import com.example.consumer.bean.Product;
import com.example.consumer.bean.ProductKind;
import com.example.consumer.bean.QueryInfo;
import com.example.consumer.service.ProductKindService;
import com.example.consumer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

@RestController
public class CallProductKindController {
    @Autowired
    ProductKindService productKindService;


    @RequestMapping("/allProductKind")
    public String getUserList(@SpringQueryMap QueryInfo queryInfo){
        return productKindService.getUserList(queryInfo);
    }
    @RequestMapping("/ProductKindIndex")
    public String getList(){
        return productKindService.getList();
    }

    @RequestMapping("/deleteProductKind")
    public String deleteUser(@RequestParam("id")int id){
        return productKindService.deleteUser(id);
    }


    @RequestMapping(value = "/updateProductKind",method = RequestMethod.POST)
    public String updateUser(@RequestBody ProductKind factory){
        return productKindService.updateUser(factory);
    }


    @RequestMapping(value ="/addPk",method = RequestMethod.POST)
    public String addUser(@RequestBody ProductKind user){
        return productKindService.addUser(user);
    }





}
