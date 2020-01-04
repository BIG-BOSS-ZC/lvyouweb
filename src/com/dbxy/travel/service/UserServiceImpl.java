package com.dbxy.travel.service;

import com.bdqn.travel.pojo.ResultInfo;
import com.bdqn.travel.pojo.User;
import com.dbxy.travel.dao.UserDao;
import com.dbxy.travel.dao.UserDaoImpl;
import com.dbxy.travel.util.MailUtils;
import com.dbxy.travel.util.Md5Util;
import com.dbxy.travel.util.UuidUtil;

import java.util.Map;

public class UserServiceImpl implements UserService{
    private UserDao dao=new UserDaoImpl();
    @Override
    public ResultInfo register(User user) {
        //结果
        ResultInfo info=new ResultInfo();
        //通过名字找user
        User u=dao.findUserByName(user);
        //如果u==null,就注册
        if(u==null){
            user.setStatus("N");
            String code=UuidUtil.getUuid();
            user.setCode(code);
            int i = dao.addUser(user);
            if(i>0){
                //注册成功
                info.setFlag(true);
                info.setErrorMsg("注册成功");
                MailUtils.sendMail(user.getEmail(),
                        "你好，欢迎注册旅游网！<a href=http://localhost:8999/lvyou/user/active?code="+
                                code+">点此激活账号</a>","请激活！欢迎注册旅游网");
            }else {
                //注册失败
                info.setFlag(false);
                info.setErrorMsg("注册失败");
                info.setData(null);
            }
            return info;
        }else {
            //有重名情况
            info.setFlag(false);
            info.setErrorMsg("用户名重复");
            info.setData(null);
            return info;
        }
    }

    @Override
    public ResultInfo login(User user) {
        //结果
        ResultInfo info=new ResultInfo();
        User user1 = dao.checkUser(user);
        if(user1==null){
            //登录失败
            info.setFlag(false);
            info.setErrorMsg("登录失败,密码或账号错误");
        }else {
            if(user1.getStatus().equals("Y")){
                //登录成功
                info.setFlag(true);
                info.setErrorMsg("登录成功");
                info.setData(user1.getUsername());
            }else {
                //登录失败
                info.setFlag(false);
                info.setErrorMsg("登录失败,请在邮件里激活账号");
            }

        }
        return info;
    }

    @Override
    public ResultInfo active(String code) {
        ResultInfo info=new ResultInfo();
        int i = dao.updateStatus(code);
        if(i>0){
            info.setFlag(true);
        }else{
            info.setFlag(false);
            info.setErrorMsg("激活失败，请联系管理员！");
        }
        return info;
    }

}
