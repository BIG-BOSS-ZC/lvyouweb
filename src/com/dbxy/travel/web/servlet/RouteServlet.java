package com.dbxy.travel.web.servlet;

import com.bdqn.travel.pojo.Category;
import com.bdqn.travel.pojo.PageRoute;
import com.bdqn.travel.pojo.ResultInfo;
import com.bdqn.travel.pojo.Route;
import com.dbxy.travel.service.CategoryService;
import com.dbxy.travel.service.CategoryServiceImpl;
import com.dbxy.travel.service.RouteService;
import com.dbxy.travel.service.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet{
    private RouteService service=new RouteServiceImpl();
    public void findAllRoute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nowpage = request.getParameter("curentPage");
        String cid=request.getParameter("cid");
        PageRoute<Route> allRoute = service.findAllRoute(Integer.parseInt(nowpage), Integer.parseInt(cid));
        returnResult(allRoute,response);
    }
    public void findTheRoute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid=request.getParameter("rid");
        ResultInfo info = service.findRouteByRid(Integer.parseInt(rid));
        returnResult(info,response);
    }
    public void findRouteBySearchName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nowpage = request.getParameter("curentPage");
        String search= request.getParameter("search");
        String decodeSearch = null;
        try {
            System.out.println(nowpage);
            decodeSearch = URLDecoder.decode(search, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//        System.out.println("now"+nowpage);
        PageRoute<Route> pageRoute=service.findRouteBySearchName(decodeSearch,Integer.parseInt(nowpage));
        returnResult(pageRoute,response);
    }
    public void findRouteBySearchNameAndCid(HttpServletRequest request, HttpServletResponse response){
        String nowpage = request.getParameter("curentPage");
        String search= request.getParameter("search");
        String cid = request.getParameter("cid");
        String decodeSearch = null;
        try {
            System.out.println(nowpage);
            decodeSearch = URLDecoder.decode(search, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        PageRoute<Route> pr=service.findRouteBySearchNameAndCid(Integer.parseInt(nowpage),decodeSearch,Integer.parseInt(cid));
        returnResult(pr,response);
    }
    public void findFavoriteRoutes(HttpServletRequest request, HttpServletResponse response){
        String nowpage = request.getParameter("curentPage");
        PageRoute<Route> favoriteRoutes = service.findFavoriteRoutes(Integer.parseInt(nowpage));
        returnResult(favoriteRoutes,response);
       // System.out.println(favoriteRoutes);
    }
    public void favoriteSearch(HttpServletRequest request, HttpServletResponse response){
        String search= request.getParameter("searchName");
        String nowpage= request.getParameter("curentPage");
        String decodeSearch = "";
        try {
            decodeSearch = URLDecoder.decode(search, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String minPrice= request.getParameter("minPrice");
        String maxPrice= request.getParameter("maxPrice");
        PageRoute<Route> pr =service.favoriteSearch(Integer.parseInt(nowpage),decodeSearch,minPrice,maxPrice);
        returnResult(pr,response);
    }
}
