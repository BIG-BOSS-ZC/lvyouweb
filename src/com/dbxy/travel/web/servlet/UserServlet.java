package com.dbxy.travel.web.servlet;

import com.bdqn.travel.pojo.ResultInfo;
import com.bdqn.travel.pojo.User;
import com.dbxy.travel.service.UserService;
import com.dbxy.travel.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/user/*")
public class UserServlet extends BaseServlet{
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("UserServlet login");
        request.setCharacterEncoding("utf-8");
        if(!checkCode(request,response)){
            return;
        }
        User user=toUser(request);
        //调用service的登录方法
        UserService service=new UserServiceImpl();
        //注册方法返回一个结果
        // 调用工具将结果返回前端
        ResultInfo login = service.login(user);
        returnResult(login,response);
        HttpSession session = request.getSession();
        session.setAttribute("info",login);
        session.setAttribute("actionuser",user);
    }
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        if(!checkCode(request,response)){
            return;
        }
        //调用servlet工具，将信息塞入对象
        User user=toUser(request);
        //调用service的注册方法
        UserService service=new UserServiceImpl();
        //注册方法返回一个结果
        ResultInfo register = service.register(user);
        request.getSession().setAttribute("activeuser",user);
        //调用工具将结果返回前端
        returnResult(register,response);
    }
    
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String code = request.getParameter("code");
        if(code!=null) {
            UserService service = new UserServiceImpl();
            ResultInfo active = service.active(code);
            if (active.isFlag()) {
                response.sendRedirect("active_ok.html");
            }
        }
    }
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("http://localhost:8999/lvyou/index.html");
    }
    public void showName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        ResultInfo info = (ResultInfo) session.getAttribute("info");
        returnResult(info,response);
    }
}
