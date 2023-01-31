package com.example.consumer.fallback;

import com.alibaba.fastjson.JSON;
import com.example.consumer.bean.Product;
import com.example.consumer.bean.QueryInfo;
import com.example.consumer.service.ProductService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ProductServiceFallbackFactory implements FallbackFactory {
    @Override
    public Object create(Throwable throwable){
        return new ProductService() {
            @Override
            public String getUserList(QueryInfo queryInfo) {
                HashMap<String, Object> res = new HashMap<>();
                res.put("data",null);
                res.put("number",0);
                return JSON.toJSONString(res);
            }

            @Override
            public String getList() {
                HashMap<String, Object> res = new HashMap<>();
                res.put("data",null);
                res.put("number",0);
                return JSON.toJSONString(res);
            }

            @Override
            public String getPro(int id) {
                return null;
            }

            @Override
            public String deleteUser(int id) {
                return "系统故障，服务降级，请稍后再试";
            }

            @Override
            public String updateUser(Product factory) {
                return  "系统故障，服务降级，请稍后再试";
            }

            @Override
            public String addUser(Product user) {
                return  "系统故障，服务降级，请稍后再试";
            }
        };
    }
}
