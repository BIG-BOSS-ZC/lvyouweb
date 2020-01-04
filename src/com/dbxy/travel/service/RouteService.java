package com.dbxy.travel.service;

import com.bdqn.travel.pojo.*;

import java.util.List;

public interface RouteService {
    PageRoute<Route> findAllRoute(int nowpage, int cid);
    ResultInfo findRouteByRid(int rid);
    List<RouteImg> findRoutImgs(int rid);
    PageRoute<Route> findFavoriteRoutes(int nowpage);
    PageRoute<Route> findRouteBySearchName(String search, int parseInt);

    PageRoute<Route> findRouteBySearchNameAndCid(int parseInt, String decodeSearch, int parseInt1);

    PageRoute<Route> favoriteSearch(int nowpage,String search, String minPrice, String maxPrice);
}
