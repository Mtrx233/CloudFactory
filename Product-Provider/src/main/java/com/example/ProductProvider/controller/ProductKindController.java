package com.example.ProductProvider.controller;

import com.alibaba.fastjson.JSON;
import com.example.ProductProvider.bean.ProductKind;
import com.example.ProductProvider.bean.QueryInfo;
import com.example.ProductProvider.dao.ProductKindDao;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;


@RestController
public class ProductKindController {

    @Autowired
    ProductKindDao factoryDao;

    @HystrixCommand(fallbackMethod = "pk_fallback1")
    @RequestMapping("/allProductKind")
    public String getProductKindList(QueryInfo queryInfo){
        System.out.println(queryInfo.getQuery());
        int numbers = factoryDao.getUserCounts("%"+queryInfo.getQuery()+"%");
        int pageStart =(queryInfo.getPageNum()-1)* queryInfo.getPageSize();
        List<ProductKind> factories= factoryDao.getAllUser("%"+queryInfo.getQuery()+"%",pageStart, queryInfo.getPageSize());
        HashMap<String, Object> res = new HashMap<>();
        res.put("data",factories);
        res.put("number",numbers);
        return JSON.toJSONString(res);
    }

    public String pk_fallback1(QueryInfo queryInfo){
        HashMap<String, Object> res = new HashMap<>();
        res.put("data",null);
        res.put("number",0);
        return JSON.toJSONString(res);
    }

    @HystrixCommand(fallbackMethod = "pk_fallback2")
    @RequestMapping("/ProductKindIndex")
    public String getList(){
        QueryInfo queryInfo=new QueryInfo("%%",1,999);
        int numbers = factoryDao.getUserCounts("%"+queryInfo.getQuery()+"%");
        int pageStart =(queryInfo.getPageNum()-1)* queryInfo.getPageSize();
        List<ProductKind> factories= factoryDao.getAllUser("%"+queryInfo.getQuery()+"%",pageStart, queryInfo.getPageSize());
        HashMap<String, Object> res = new HashMap<>();
        res.put("data",factories);
        res.put("number",numbers);
        return JSON.toJSONString(res);
    }
    public String pk_fallback2(){
        HashMap<String, Object> res = new HashMap<>();
        res.put("data",null);
        res.put("number",0);
        return JSON.toJSONString(res);
    }


    @HystrixCommand(fallbackMethod = "pk_fallback3")
    @RequestMapping("/deleteProductKind")
    public String deleteProductKind(int id){
        System.out.println(id);
        int i=0;
        try {
            i =factoryDao.deleteUser(id);
        }catch (Exception es){

        }
        return i>0?"success":"存在对应的商品，删除失败！";
    }
    public String pk_fallback3(int id){
        return "系统故障，操作失败";
    }

    @HystrixCommand(fallbackMethod = "pk_fallback4")
    @RequestMapping("/updateProductKind")
    public String updateProductKind(@RequestBody ProductKind factory){
        System.out.println(factory);
        int i=factoryDao.updateUser(factory);
        return i>0?"success":"操作失败！！！";
    }

    public String pk_fallback4(@RequestBody ProductKind factory){
        return"系统故障，操作失败";
    }


    @HystrixCommand(fallbackMethod = "pk_fallback5")
    @RequestMapping("/addPk")
    public String addProductKind(@RequestBody ProductKind user){
        System.out.println(user.toString());
        System.out.println(factoryDao.addUser(user));
        return "success";
    }

    public String pk_fallback5(@RequestBody ProductKind user){
        return "系统故障，操作失败";
    }

}
