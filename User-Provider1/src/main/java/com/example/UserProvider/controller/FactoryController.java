package com.example.UserProvider.controller;

import com.alibaba.fastjson.JSON;
import com.example.UserProvider.bean.Factory;
import com.example.UserProvider.bean.QueryInfo;
import com.example.UserProvider.dao.FactoryDao;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;


@RestController
public class FactoryController {

    @Autowired
    FactoryDao factoryDao;

    @HystrixCommand(fallbackMethod = "fallback1")
    @RequestMapping("/allFactories")
    public String getFactoryList(QueryInfo queryInfo){
        System.out.println(queryInfo.getQuery());
        int numbers = factoryDao.getFactoryCounts("%"+queryInfo.getQuery()+"%");
        int pageStart =(queryInfo.getPageNum()-1)* queryInfo.getPageSize();
        List<Factory> factories= factoryDao.getAllFactories("%"+queryInfo.getQuery()+"%",pageStart, queryInfo.getPageSize());
        HashMap<String, Object> res = new HashMap<>();
        res.put("data",factories);
        res.put("number",numbers);
        return JSON.toJSONString(res);
    }

    public String fallback1(QueryInfo queryInfo){
        HashMap<String, Object> res = new HashMap<>();
        res.put("data",null);
        res.put("number",0);
        return JSON.toJSONString(res);
    }

    @HystrixCommand(fallbackMethod = "fallback2")
    @RequestMapping("/facChangeState")
    public String updateState(@RequestBody Factory factory){
        int i = factoryDao.updateState(factory.getFactory_id(),factory.isState());
        return i>0?"success":"操作失败";
    }

    public String fallback2(@RequestBody Factory factory){
        return "系统故障，请稍后再试";
    }

    @HystrixCommand(fallbackMethod = "fallback3")
    @RequestMapping("/deleteFactory")
    public String deleteFactory(int id){
        System.out.println(id);
        int i =factoryDao.delete(id);
        return i>0?"success":"删除失败！";
    }

    public String fallback3(int id){
        return "系统故障，请稍后再试";
    }

    @HystrixCommand(fallbackMethod = "fallback4")
    @RequestMapping("/getFactory")
    public String getFactory(int id){
        System.out.println("getid" + id);
        Factory factory =factoryDao.getFactory(id);
        System.out.println(factory);
        return JSON.toJSONString(factory);
    }

    public String fallback4(int id){
        return null;
    }


    @HystrixCommand(fallbackMethod = "fallback5")
    @RequestMapping("/updateFactory")
    public String updateFactory(@RequestBody Factory factory){
        System.out.println(factory);
        int i=factoryDao.updateFactory(factory);
        return i>0?"success":"更新失败！";
    }

    public String fallback5(@RequestBody Factory factory){
        return "系统故障，请稍后再试";
    }

}
