package com.example.consumer.controller;

import com.example.consumer.bean.Factory;
import com.example.consumer.bean.QueryInfo;
import com.example.consumer.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

@RestController
public class CallFactoryController {

    @Autowired
    FactoryService factoryService;



    @RequestMapping("/allFactories")
    public String getFactoryList( QueryInfo queryInfo){
        return factoryService.getFactoryList(queryInfo);
    }

    @RequestMapping(value = "/facChangeState",method = RequestMethod.POST)
    public String updateState(@RequestBody Factory factory){
        return factoryService.updateState(factory);
    }

    @RequestMapping("/deleteFactory")
    public String deleteFactory(@RequestParam("id")int id){
        return factoryService.deleteFactory(id);
    }

    @RequestMapping("/getFactory")
    public String getFactory(int id){
        return factoryService.getFactory(id);
    }

    @RequestMapping(value = "/updateFactory",method = RequestMethod.POST)
    public String updateFactory(@RequestBody Factory factory){
        return factoryService.updateFactory(factory);
    }
}
