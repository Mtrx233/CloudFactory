package com.example.consumer.service;


import com.example.consumer.bean.Product;
import com.example.consumer.bean.QueryInfo;
import com.example.consumer.bean.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(name="product-provider",contextId = "product",fallbackFactory = com.example.consumer.fallback.ProductServiceFallbackFactory.class)
public interface ProductService {

    @RequestMapping("/allProduct")
    public String getUserList(@SpringQueryMap QueryInfo queryInfo);

    @RequestMapping("/ProductIndex")
    public String getList();

    @RequestMapping("/getPro")
    public String getPro(@RequestParam("id")int id);

    @RequestMapping("/deleteProduct")
    public String deleteUser(@RequestParam("id") int id);

    @RequestMapping(value = "/updateProduct",method = RequestMethod.POST)
    public String updateUser(@RequestBody Product factory);


    @RequestMapping(value ="/addP",method = RequestMethod.POST)
    public String addUser(@RequestBody Product user);







}
