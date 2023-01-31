package com.example.consumer.fallback;

import com.alibaba.fastjson.JSON;
import com.example.consumer.bean.QueryInfo;
import com.example.consumer.bean.User;
import com.example.consumer.service.UserService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class UserServiceFallbackFactory implements FallbackFactory {


    @Override
    public Object create(Throwable throwable) {
        return new UserService(){

            @Override
            public String login(String account, String password, String code) {
                HashMap<String ,Object> res =new HashMap<>();
                res.put("flag","error");
                res.put("info","系统出现故障，请稍后再试！");
                return JSON.toJSONString(res);
            }

            @Override
            public String getUserList(QueryInfo queryInfo) {
                return null;
            }

            @Override
            public String addUser(User user) {
                return "抱歉，系统故障，服务降级，请稍后再试";
            }

            @Override
            public String deleteUser(int id) {
                return "系统故障，服务降级，请稍后再试";
            }

            @Override
            public String updateUser(User user) {
                return "系统故障，服务降级，请稍后再试";
            }

            @Override
            public String isExist(String email, String account) {
                return "系统故障，服务降级，请稍后再试";
            }

            @Override
            public String ensureUser(String time) {
                return null;
            }

            @Override
            public String changePassword(String password, String account, String time) {
                return "系统故障，服务降级，请稍后再试";
            }

            @Override
            public String getAllMenus(int kind) {
                return null;
            }
        };
    }


}
