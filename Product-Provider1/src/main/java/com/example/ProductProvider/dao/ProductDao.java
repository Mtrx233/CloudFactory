package com.example.ProductProvider.dao;

import com.example.ProductProvider.bean.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao {



     List<Product> getAllUser(@Param("account")String account, @Param("pageStart")int pageStart, @Param("pageSize")int pageSize);
     int getUserCounts(@Param("account")String account);
     int addUser(Product user);
     int deleteUser(int id);
     int updateUser(Product user);
     List<Product> getIndex(int equipment_id);


     int getProductCounts(@Param("p_name") String p_name, @Param("p_account") String p_account, @Param("product_kind_id") int product_kind_id, @Param("q_low") double q_low, @Param("q_high") double q_high);
     List<Product> findProduct(@Param("p_name") String p_name, @Param("p_account") String p_account, @Param("product_kind_id") int product_kind_id, @Param("q_low") double q_low, @Param("q_high") double q_high, @Param("pageStart")int pageStart, @Param("pageSize")int pageSize);



}
