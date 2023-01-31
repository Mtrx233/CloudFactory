package com.example.consumer.bean;

import lombok.Data;

import java.util.List;

@Data
public class MainMenu {
    int id;
    String title;
    String path;
    String icon;
    List<SubMenu> subMenuList;
}
