package com.example.consumer.fallback;

import com.alibaba.fastjson.JSON;
import com.example.consumer.bean.Factory;
import com.example.consumer.bean.QueryInfo;
import com.example.consumer.service.FactoryService;
import com.example.consumer.service.ProductService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class FactoryServiceFallbackFactory implements FallbackFactory {
    @Override
    public Object create(Throwable throwable){
        return new FactoryService() {
            @Override
            public String getFactoryList(QueryInfo queryInfo) {
                HashMap<String, Object> res = new HashMap<>();
                res.put("data",null);
                res.put("number",0);
                return JSON.toJSONString(res);
            }

            @Override
            public String updateState(Factory factory) {
                return "系统故障，服务降级，请稍后再试";
            }

            @Override
            public String deleteFactory(int id) {
                return "系统故障，服务降级，请稍后再试";
            }

            @Override
            public String getFactory(int id) {
                return null;
            }

            @Override
            public String updateFactory(Factory factory) {
                return "系统故障，服务降级，请稍后再试";
            }
        };
    }
}
