package com.example.UserProvider.dao;

import com.example.UserProvider.bean.Factory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactoryDao {
    public List<Factory> getAllFactories(@Param("account")String account, @Param("pageStart")int pageStart, @Param("pageSize")int pageSize);
    public int getFactoryCounts(@Param("account")String account);
    public int updateState(Integer id ,Boolean state);
    public int delete(Integer id );
    public int updateFactory(Factory factory);
    public Factory getFactory(int user_id);

}
