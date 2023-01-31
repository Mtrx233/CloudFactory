package com.example.UserProvider.dao;

import com.example.UserProvider.bean.MainMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDao {
    public List<MainMenu> getMenus(int kind);
}
