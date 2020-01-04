package com.dbxy.travel.web.servlet;

import com.bdqn.travel.pojo.ResultInfo;
import com.bdqn.travel.pojo.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class BaseServlet extends HttpServlet {
    /**
     * 检验验证码
     * @param request
     * @param response
     * @return
     */
    public boolean checkCode(HttpServletRequest request, HttpServletResponse response) {
        //获取用户输入的验证码
        String check = request.getParameter("check");
//        System.out.println(check);
        //获取后台产生的验证码
        String checkcode = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        //清除验证码，防止缓存bug
//        System.out.println(checkcode);
        request.getSession().removeAttribute("CHECKCODE_SERVER");
        //忽略大小写比对验证码
        if (check==null || (check!=null && !check.equalsIgnoreCase(checkcode))) {
            System.out.println("checkcode error");
            try {
                //如果验证码输入错误
                ResultInfo errorinfo = new ResultInfo();
                errorinfo.setFlag(false);
                errorinfo.setErrorMsg("验证码错误");
                errorinfo.setData(null);
                response.setContentType("application/json;charset=utf-8");
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(response.getOutputStream(), errorinfo);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        } else {
            System.out.println("checkcode true");
            return true;
        }
    }

    public User toUser(HttpServletRequest request){
        User user=new User();
        try {
//            System.out.println("start to");
            //获取用户信息
            request.setCharacterEncoding("utf-8");
            Map<String, String[]> map = request.getParameterMap();
            //塞信息给user
            BeanUtils.populate(user,map);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println("stop");
        return user;
    }

    public void returnResult(Object info,HttpServletResponse response){
        //将结果传给前端
        response.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper=new ObjectMapper();
        try {
            mapper.writeValue(response.getOutputStream(),info);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        //获取URI--->  user/login
        String reqURI = req.getRequestURI();
        //获取请求的方法名--->将URI从最后一次'/'出现的位置切割，提取方法名login
        String method = reqURI.substring(reqURI.lastIndexOf('/')+1);
//        System.out.println(method);
        try {
            Class<? extends BaseServlet> aClass = this.getClass();
            //得到子类Sevlet字节码文件，用字节码文件提取子类servlet的所有方法数组
            Method[] methods = aClass.getMethods();
            //遍历数组
            for (Method m:methods) {
//                System.out.println(m.getName());
                //找到请求的方法名为method的方法
                if(m.getName().equals(method)){
                    //调用方法，this就是子类Servlet对象
                    // m.invoke(servlet,req,resp);
                    m.invoke(this,req,resp);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
