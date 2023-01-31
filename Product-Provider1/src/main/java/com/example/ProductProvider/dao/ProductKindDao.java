package com.example.ProductProvider.dao;

import com.example.ProductProvider.bean.ProductKind;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductKindDao {
    public List<ProductKind> getAllUser(@Param("account")String account, @Param("pageStart")int pageStart, @Param("pageSize")int pageSize);
    public int getUserCounts(@Param("account")String account);
    public int addUser(ProductKind user);
    public int deleteUser(int id);
    public int updateUser(ProductKind user);
}
