package com.dbxy.travel.dao;

import com.bdqn.travel.pojo.User;

public interface UserDao {
    public User findUserByName(User user);
    public User checkUser(User user);
    public int updateStatus(String code);
    public int addUser(User user);
}
