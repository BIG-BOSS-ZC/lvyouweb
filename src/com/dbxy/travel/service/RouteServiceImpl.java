package com.dbxy.travel.service;

import com.bdqn.travel.pojo.*;
import com.dbxy.travel.dao.CategoryDao;
import com.dbxy.travel.dao.CategoryDaoImpl;
import com.dbxy.travel.dao.RouteDao;
import com.dbxy.travel.dao.RouteDaoImpl;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    private RouteDao dao=new RouteDaoImpl();

    @Override
    public PageRoute<Route> favoriteSearch(int nowpage,String search, String minPrice, String maxPrice) {
        PageRoute<Route> pr=new PageRoute<>();
        if(maxPrice.equals("")){
            maxPrice="999999999";
            pr=dao.searchRouteByNameAndPrice(nowpage,search,minPrice,maxPrice);
        }else {
            pr=dao.searchRouteByNameAndPrice(nowpage,search,minPrice,maxPrice);
        }
        String allItems=pr.getRouteCount();
        int all=Integer.parseInt(allItems);
        String routeCount=(all%8==0?all/8:all/8+1)+"";
        //共多少页
        pr.setPageCount(routeCount);
        pr.setCurentPage(nowpage+"");
        return pr;
    }

    @Override
    public PageRoute<Route> findRouteBySearchNameAndCid(int nowpage, String decodeSearch, int cid) {
        PageRoute<Route> pr=dao.findRouteBySearchNameAndCid(nowpage,decodeSearch,cid);
        //当前第几页
        pr.setCurentPage(nowpage+"");
        String allItems=pr.getRouteCount();
        int all=Integer.parseInt(allItems);
        String routeCount=(all%8==0?all/8:all/8+1)+"";
        //共多少页
        pr.setPageCount(routeCount);
        return pr;
    }

    @Override
    public PageRoute<Route> findFavoriteRoutes(int nowpage) {
        PageRoute<Route> pr = dao.findFavoriteRoutes(nowpage);
        pr.setCurentPage(nowpage+"");
        String allItems=pr.getRouteCount();
        int all=Integer.parseInt(allItems);
        String routeCount=(all%8==0?all/8:all/8+1)+"";
        //共多少页
        pr.setPageCount(routeCount);
        return pr;
    }

    @Override
    public PageRoute<Route> findRouteBySearchName(String search, int nowpage) {
        PageRoute<Route> prSearch = dao.findRouteBySearchName(search,nowpage);
        //当前第几页
        prSearch.setCurentPage(nowpage+"");
        String allItems=prSearch.getRouteCount();
        int all=Integer.parseInt(allItems);
        String routeCount=(all%8==0?all/8:all/8+1)+"";
        //共多少页
        prSearch.setPageCount(routeCount);
        return prSearch;
    }

    @Override
    public List<RouteImg> findRoutImgs(int rid) {
        List<RouteImg> routImgs = dao.findRoutImgs(rid);
        return routImgs;
    }

    @Override
    public ResultInfo findRouteByRid(int rid) {
        Route route = dao.findRouteByRid(rid);
        ResultInfo info=new ResultInfo();
        if(route!=null){
            int cid=route.getCid();
            CategoryDao cdao=new CategoryDaoImpl();
            Category category = cdao.findCategoryByCid(cid);
            Seller seller=dao.findSellerBySid(route.getSid());
            List<RouteImg> routImgs = findRoutImgs(rid);
            route.setCategory(category);
            route.setRouteImgList(routImgs);
            route.setSeller(seller);

            info.setFlag(true);
            info.setData(route);
        }

        return info;
    }

    @Override
    public PageRoute<Route> findAllRoute(int nowpage,int cid) {
        PageRoute<Route> pr=new PageRoute<>();
        pr.setCurentPage(nowpage+"");
        int allItems=dao.itemsNum(cid);
        pr.setPageCount(allItems+"");
        String routeCount=(allItems%8==0?allItems/8:allItems/8+1)+"";
        pr.setRouteCount(routeCount);
        List<Route> list=dao.findAllRoute(nowpage,cid);
        pr.setRouteList(list);
        return pr;
    }
}
