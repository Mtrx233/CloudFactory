package com.example.UserProvider.dao;

import com.example.UserProvider.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserDao {
    public User getUserByMassage(@Param("account") String account, @Param("password") String password);
    public List<User> getAllUser(@Param("account")String account,@Param("pageStart")int pageStart,@Param("pageSize")int pageSize);
    public int getUserCounts(@Param("account")String account);
    public String addUser(User user);
    public int deleteUser(int id);
    public User getUser(int id);
    public int updateUser(User user);
    public int isExist(String email ,String account);
    public int changePassword(String password,String account);
    public List<User> userList();
    public int getState(int user_id);
}
