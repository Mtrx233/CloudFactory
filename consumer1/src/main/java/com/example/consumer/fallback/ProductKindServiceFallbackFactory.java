package com.example.consumer.fallback;

import com.alibaba.fastjson.JSON;
import com.example.consumer.bean.ProductKind;
import com.example.consumer.bean.QueryInfo;
import com.example.consumer.service.ProductKindService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ProductKindServiceFallbackFactory implements FallbackFactory {
    @Override
    public Object create(Throwable throwable){
        return new ProductKindService() {
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
            public String deleteUser(int id) {
                return  "系统故障，服务降级，请稍后再试";
            }

            @Override
            public String updateUser(ProductKind factory) {
                return  "系统故障，服务降级，请稍后再试";
            }

            @Override
            public String addUser(ProductKind user) {
                return  "系统故障，服务降级，请稍后再试";
            }
        };
    }
}
