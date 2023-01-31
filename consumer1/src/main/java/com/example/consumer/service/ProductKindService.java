package com.example.consumer.service;


import com.example.consumer.bean.Product;
import com.example.consumer.bean.ProductKind;
import com.example.consumer.bean.QueryInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(name="product-provider",contextId = "productKind",fallbackFactory = com.example.consumer.fallback.ProductKindServiceFallbackFactory.class)
public interface ProductKindService {

    @RequestMapping("/allProductKind")
    public String getUserList(@SpringQueryMap QueryInfo queryInfo);

    @RequestMapping("/ProductKindIndex")
    public String getList();

    @RequestMapping("/deleteProductKind")
    public String deleteUser(@RequestParam("id")int id);


    @RequestMapping(value = "/updateProductKind",method = RequestMethod.POST)
    public String updateUser(@RequestBody ProductKind factory);


    @RequestMapping(value ="/addPk",method = RequestMethod.POST)
    public String addUser(@RequestBody ProductKind user);







}
