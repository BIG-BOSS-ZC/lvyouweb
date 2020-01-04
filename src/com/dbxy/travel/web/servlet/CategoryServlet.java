package com.dbxy.travel.web.servlet;

import com.bdqn.travel.pojo.Category;
import com.dbxy.travel.service.CategoryService;
import com.dbxy.travel.service.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet{
    private CategoryService service=new CategoryServiceImpl();
    public void findAllCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> list = service.findAllCategory();
        returnResult(list,response);
    }
}
