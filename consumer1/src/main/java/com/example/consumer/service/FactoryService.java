package com.example.consumer.service;

import com.example.consumer.bean.Factory;
import com.example.consumer.bean.QueryInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="user-provider",contextId = "factory",fallbackFactory = com.example.consumer.fallback.FactoryServiceFallbackFactory.class)
public interface FactoryService {

    @RequestMapping("/allFactories")
    public String getFactoryList(@SpringQueryMap QueryInfo queryInfo);

    @RequestMapping(value = "/facChangeState",method = RequestMethod.POST)
    public String updateState(@RequestBody Factory factory);

    @RequestMapping("/deleteFactory")
    public String deleteFactory(@RequestParam("id")int id);

    @RequestMapping("/getFactory")
    public String getFactory(int id);

    @RequestMapping(value = "/updateFactory",method = RequestMethod.POST)
    public String updateFactory(@RequestBody Factory factory);



}
