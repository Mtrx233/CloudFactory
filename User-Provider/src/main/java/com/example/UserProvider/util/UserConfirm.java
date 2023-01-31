package com.example.UserProvider.util;

import java.util.HashMap;

public class UserConfirm {
    public static HashMap<String,Object> list =new HashMap<>();

    public static void addItem(String account,String time){
        UserConfirm.list.put(time,account);
    }

    public static void removerItem(String time){
        UserConfirm.list.remove(time);

    }
    public static String getItem(String time){
       return (String) UserConfirm.list.get(time);
    }
}
