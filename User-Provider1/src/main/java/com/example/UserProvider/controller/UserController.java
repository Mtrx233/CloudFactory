package com.example.UserProvider.controller;

import com.alibaba.fastjson.JSON;
import com.example.UserProvider.bean.Factory;
import com.example.UserProvider.dao.UserDao;
import com.example.UserProvider.bean.QueryInfo;
import com.example.UserProvider.bean.User;
import com.example.UserProvider.util.EmailUtil;
import com.example.UserProvider.util.HashUtils;
import com.example.UserProvider.util.UserConfirm;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {


    @Autowired
    UserDao userDao;

    @HystrixCommand(fallbackMethod = "u_fallback1")
    @RequestMapping("/login")
    public String login(String account ,String password ,String code){
        password= HashUtils.getSHA256StrJava(password);
        String temp = com.example.UserProvider.util.code.getCode();
        HashMap<String ,Object> res =new HashMap<>();
        String flag ="error";

        User user=userDao.getUserByMassage(account,password);
        if(user==null){
            res.put("info","用户名或密码错误！");
        }else {
            if(user.getKind().equals("云工厂")){
                int i = userDao.getState(user.getUser_id());
                System.out.println("state-->"+i);
                if(i==0){
                    res.put("info","账户权限未开通！");
                    res.put("flag",flag);
                    String res_json =JSON.toJSONString(res);
                    return res_json;
                }
            }
            flag="ok";
            res.put("kind",user.getUser_kind_id());
            res.put("user",user);
        }

        res.put("flag",flag);
        String res_json =JSON.toJSONString(res);
        System.out.printf(res_json);
        return res_json;
    }

    public String u_fallback1(String account ,String password ,String code){
        HashMap<String ,Object> res =new HashMap<>();
        res.put("flag","error");
        res.put("info","系统错误！");
        return JSON.toJSONString(res);
    }

    @HystrixCommand(fallbackMethod = "u_fallback2")
    @RequestMapping("/allUsers")
    public String getUserList(QueryInfo queryInfo){
        int numbers = userDao.getUserCounts("%"+queryInfo.getQuery()+"%");
        int pageStart =(queryInfo.getPageNum()-1)* queryInfo.getPageSize();
        List<User> users= userDao.getAllUser("%"+queryInfo.getQuery()+"%",pageStart, queryInfo.getPageSize());
        HashMap<String, Object> res = new HashMap<>();
        res.put("data",users);
        res.put("number",numbers);
        return  JSON.toJSONString(res);
    }

    public String u_fallback2(QueryInfo queryInfo){
        HashMap<String ,Object> res =new HashMap<>();
        res.put("data",null);
        res.put("number",0);
        return JSON.toJSONString(res);
    }

    @HystrixCommand(fallbackMethod = "u_fallback3")
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public String addUser(@RequestBody User user){
        System.out.println(user.toString());
        user.setPassword(HashUtils.getSHA256StrJava(user.getPassword()));
        System.out.println(user.getPassword());
        return  userDao.addUser(user);
    }

    public String u_fallback3(@RequestBody User user){
        return "抱歉，系统故障！请稍后再试";
    }

    @HystrixCommand(fallbackMethod = "u_fallback4")
    @RequestMapping("/deleteUser")
    public String deleteUser(int id){
        System.out.println(id);
        int i =userDao.deleteUser(id);
        return i>0?"success":"删除失败";
    }

    public String u_fallback4(int id){
        return "系统故障，请稍后再试";
    }


    @HystrixCommand(fallbackMethod = "u_fallback5")
    @RequestMapping("/updateUser")
    public String updateUser(@RequestBody User user){
        System.out.println(user);
        int i=userDao.updateUser(user);
        return i>0?"success":"更新失败";
    }

    public String u_fallback5(@RequestBody User user){
        return "系统故障，请稍后再试";
    }

    @HystrixCommand(fallbackMethod = "u_fallback6")
    @RequestMapping("/isExist")
    public String isExist(String email ,String account) throws MessagingException {
        int i =userDao.isExist(email,account);
        if(i>0){
            String time = String.valueOf(System.currentTimeMillis());
            UserConfirm.addItem(account,time);
            String context ="<a href=\"http://localhost:8080/#/changePasword?time="+time+"\">点击修改密码</a>";
            EmailUtil.sendEmail(email,"修改密码",context);
        }
        return i>0?"success":"用户名或邮箱错误！！";
    }

    public String u_fallback6(String email ,String account){
        return "success";
    }



    @HystrixCommand(fallbackMethod = "u_fallback7")
    @RequestMapping("/ensureUser")
    public String ensureUser(String time){
        String account =UserConfirm.getItem(time);
        HashMap<String,Object> res =new HashMap<>();
        String flag ="error";
        if(account!=null){
            flag="success";
            res.put("account",account);
        }
        UserConfirm.removerItem(time);
        res.put("flag",flag);
        return JSON.toJSONString(res);
    }

    public String u_fallback7(String time){
        HashMap<String,Object> res =new HashMap<>();
        res.put("flag","error");
        return JSON.toJSONString(res);
    }


    @HystrixCommand(fallbackMethod = "u_fallback8")
    @RequestMapping("/changePassword")
    public String changePassword(String password,String account,String time){
        password= HashUtils.getSHA256StrJava(password);
        int i =userDao.changePassword(password,account);
        return  i>0?"success":"修改失败";
    }

    public String u_fallback8(String password,String account,String time){
        return "系统故障，请稍后再试";
    }

}
