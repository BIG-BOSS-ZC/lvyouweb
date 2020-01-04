package com.dbxy.travel.dao;

import com.bdqn.travel.pojo.*;

import java.util.List;

public interface RouteDao {
    List<Route> findAllRoute(int nowpage,int cid);
    int itemsNum(int cid);
    Route findRouteByRid(int rid);
    List<RouteImg> findRoutImgs(int rid);
    Seller findSellerBySid(int sid);
    PageRoute<Route> findRouteBySearchName(String name, int nowpage);
    PageRoute<Route> findFavoriteRoutes(int nowpage);
    PageRoute<Route> findRouteBySearchNameAndCid(int nowpage, String decodeSearch, int cid);
    PageRoute<Route> searchRouteByNameAndPrice(int nowpage, String search, String minPrice, String maxPrice);
}
