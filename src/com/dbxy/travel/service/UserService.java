package com.dbxy.travel.service;

import com.bdqn.travel.pojo.ResultInfo;
import com.bdqn.travel.pojo.User;

public interface UserService {
    public ResultInfo register(User user);
    public ResultInfo login(User user);
    public ResultInfo active(String code);
}
