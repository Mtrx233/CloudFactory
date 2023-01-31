package com.example.ProductProvider.controller;

import com.alibaba.fastjson.JSON;
import com.example.ProductProvider.bean.Product;
import com.example.ProductProvider.bean.QueryInfo;
import com.example.ProductProvider.dao.ProductDao;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;


@RestController
public class ProductController {

    @Autowired
    ProductDao factoryDao;


    @HystrixCommand(fallbackMethod = "p_fallback1")
    @RequestMapping("/allProduct")
    public String getProductList(QueryInfo queryInfo){
        if(queryInfo.getQuery().equals("")){
            queryInfo.setQuery(" ~ ~ ~ ~ ~");
        }
        String[] temp = queryInfo.getQuery().split("~");
        int q_product_kind_id;
        double q_low;
        double q_high;

        if(temp[0].equals(" ")){
            q_product_kind_id=-1;
        }else {
            q_product_kind_id  = Integer.parseInt(temp[0].substring(0,temp[0].length()-1));
        }
        String q_account = temp[1].substring(0,temp[1].length()-1);
        String q_name = temp[2].substring(0,temp[2].length()-1);
        if(temp[3].equals(" ")){
            q_low = 0;
        }else {
            q_low = Integer.parseInt(temp[3].substring(0,temp[3].length()-1));
        }
        if(temp[4].equals(" ")){
            q_high= Integer.MAX_VALUE;
        }else {
            q_high = Integer.parseInt(temp[4].substring(0,temp[4].length()-1));
        }
        int numbers = factoryDao.getProductCounts(q_name,q_account,q_product_kind_id,q_low,q_high);
        int pageStart =(queryInfo.getPageNum()-1)* queryInfo.getPageSize();
        List<Product> productList = factoryDao.findProduct(q_name,q_account,q_product_kind_id,q_low,q_high,pageStart,queryInfo.getPageSize());
        HashMap<String, Object> res = new HashMap<>();
        res.put("data",productList);
        res.put("number",numbers);
        return JSON.toJSONString(res);
    }

    public String p_fallback1(QueryInfo queryInfo){
        HashMap<String, Object> res = new HashMap<>();
        res.put("data",null);
        res.put("number",0);
        return JSON.toJSONString(res);
    }

    @HystrixCommand(fallbackMethod = "p_fallback2")
    @RequestMapping("/ProductIndex")
    public String getList(){
        QueryInfo queryInfo=new QueryInfo("%%",1,999);
        int numbers = factoryDao.getUserCounts("%"+queryInfo.getQuery()+"%");
        int pageStart =(queryInfo.getPageNum()-1)* queryInfo.getPageSize();
        List<Product> factories= factoryDao.getAllUser("%"+queryInfo.getQuery()+"%",pageStart, queryInfo.getPageSize());
        HashMap<String, Object> res = new HashMap<>();
        res.put("data",factories);
        res.put("number",numbers);
        return JSON.toJSONString(res);
    }

    public String p_fallback2(){
        HashMap<String, Object> res = new HashMap<>();
        res.put("data",null);
        res.put("number",0);
        return JSON.toJSONString(res);
    }


    @HystrixCommand(fallbackMethod = "p_fallback3")
    @RequestMapping("/getPro")
    public String getProduct(int id){
        List<Product> products = factoryDao.getIndex(id);
        return JSON.toJSONString(products);
    }

    public String p_fallback3(int id){
        return null;
    }

    @HystrixCommand(fallbackMethod = "p_fallback4")
    @RequestMapping("/deleteProduct")
    public String deleteProduct(int id){
        int i=0;
        try {
            i =factoryDao.deleteUser(id);
        }catch (Exception ex){

        }
        return i>0?"success":"存在关联订单，删除失败！";
    }

    public String p_fallback4(int id){
        return "系统故障，操作失败！";
    }

    @HystrixCommand(fallbackMethod = "p_fallback5")
    @RequestMapping("/updateProduct")
    public String updateProduct(@RequestBody Product factory){
        System.out.println(factory);
        int i=factoryDao.updateUser(factory);
        return i>0?"success":"操作失败";
    }

    public String p_fallback5(@RequestBody Product factory){
        return "系统故障，操作失败！";
    }


    @HystrixCommand(fallbackMethod = "p_fallback6")
    @RequestMapping("/addP")
    public String addProduct(@RequestBody Product user){
        user.setP_account(String.valueOf(System.currentTimeMillis()));
        System.out.println(user.toString());
        System.out.println(factoryDao.addUser(user));
        return "success";
    }

    public String p_fallback6(@RequestBody Product user){
        return "系统故障，操作失败！";
    }





}
