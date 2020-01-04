package com.dbxy.travel.dao;

import com.bdqn.travel.pojo.*;
import com.dbxy.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate jt=new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public PageRoute<Route> searchRouteByNameAndPrice(int nowpage, String search, String minPrice, String maxPrice) {
        int start = (nowpage-1)*8;
        PageRoute<Route> pr = new PageRoute<>();
        try {
            List<Route> list = jt.query("SELECT * FROM tab_route where rname like '%" + search + "%' and price>? and price<? ORDER BY count desc LIMIT ?,8", new BeanPropertyRowMapper<>(Route.class), minPrice, maxPrice,start);
            int len=jt.queryForObject("select count(*) from tab_route where rname like '%" + search + "%' and price>? and price<?",Integer.class,minPrice,maxPrice);
            pr.setRouteCount(len+"");
            pr.setRouteList(list);
        } catch (DataAccessException e) {
        }
        return pr;
    }

    @Override
    public PageRoute<Route> findRouteBySearchNameAndCid(int nowpage, String decodeSearch, int cid) {
        int start=(nowpage-1)*8;
        PageRoute<Route> pr=new PageRoute<>();
        List<Route> list = new ArrayList<>();
        try {
            list=jt.query("SELECT * from tab_route WHERE cid=? and rname like '%"+decodeSearch+"%' limit ?,8",new BeanPropertyRowMapper<>(Route.class),cid,start);
            int len=jt.queryForObject("select count(*) from tab_route WHERE cid=? and rname like '%"+decodeSearch+"%'",Integer.class,cid);
            pr.setRouteCount(len+"");
            pr.setRouteList(list);

        } catch (DataAccessException e) {
        }
        return pr;
    }

    @Override
    public PageRoute<Route> findFavoriteRoutes(int nowpage) {
        PageRoute<Route> pr=new PageRoute<>();
        List<Route> list1=new ArrayList<>();
        List<Route> list2=new ArrayList<>();
        int start=(nowpage-1)*8;
        try {
            list1=jt.query("SELECT * from tab_route ORDER BY count desc",new BeanPropertyRowMapper<>(Route.class));
            list2=jt.query("SELECT * from tab_route ORDER BY count desc limit ?,8",new BeanPropertyRowMapper<>(Route.class),start);
            pr.setRouteList(list2);
            pr.setRouteCount(list1.size()+"");
        } catch (DataAccessException e) {
        }
        return pr;
    }

    @Override
    public PageRoute<Route> findRouteBySearchName(String name, int nowpage) {
        int start=(nowpage-1)*8;
        PageRoute<Route> pr=new PageRoute<>();
        List<Route> list = new ArrayList<>();
        try {
            list=jt.query("SELECT * from tab_route WHERE rname like '%"+name+"%' limit ?,8",new BeanPropertyRowMapper<>(Route.class),start);
            int len=jt.queryForObject("select count(*) from tab_route WHERE rname like '%"+name+"%'",Integer.class);
            pr.setRouteCount(len+"");
            pr.setRouteList(list);

        } catch (DataAccessException e) {
        }
        return pr;
    }

    @Override
    public Seller findSellerBySid(int sid) {
        Seller seller=new Seller();
        try {
            seller=jt.queryForObject("select * from tab_seller where sid=?",new BeanPropertyRowMapper<>(Seller.class),sid);
        } catch (DataAccessException e) {
        }
        return seller;
    }

    @Override
    public List<RouteImg> findRoutImgs(int rid) {
        List<RouteImg> list=new ArrayList<>();
        try {
            list= jt.query("select * from tab_route_img where rid=?",new BeanPropertyRowMapper<>(RouteImg.class),rid);
        } catch (DataAccessException e) {
        }
        return list;
    }
    @Override
    public List<Route> findAllRoute(int nowpage,int cid) {
        int start=(nowpage-1)*8;
        List<Route> list = new ArrayList<>();
        try {
            list = jt.query("select * from tab_route where cid=? limit ?,8", new BeanPropertyRowMapper<>(Route.class),cid,start);
        } catch (DataAccessException e) {
        }
        return list;
    }

    @Override
    public int itemsNum(int cid) {
        int len=jt.queryForObject("select count(*) from tab_route where cid=?",Integer.class,cid);
        return len;
    }

    @Override
    public Route findRouteByRid(int rid) {
        Route route=new Route();
        try {
            route=jt.queryForObject("select * from tab_route where rid=?",new BeanPropertyRowMapper<>(Route.class),rid);
        } catch (DataAccessException e) {
        }
        return route;
    }
     /*public static void main(String[] args) {
        JdbcTemplate jt=new JdbcTemplate(JDBCUtils.getDataSource());
        *//*List<Route> list = jt.query("select * from tab_route where cid=? limit ?,8", new BeanPropertyRowMapper<Route>(Route.class),5,0);
        int len=jt.queryForObject("select count(*) from tab_route where cid=?",Integer.class,5);
        System.out.println(list);
        System.out.println(len);
        Route route=jt.queryForObject("select * from tab_route where rid=?",new BeanPropertyRowMapper<>(Route.class),18);
        System.out.println(route);
        Route r=jt.queryForObject("select * from tab_route where rid=?",new BeanPropertyRowMapper<>(Route.class),5);
        System.out.println(r);
         List<RouteImg> list=new ArrayList<>();
         try {
             list= jt.query("select * from tab_route_img where rid=?",new BeanPropertyRowMapper<>(RouteImg.class),1);
         } catch (DataAccessException e) {
         }
         System.out.println(list);*//*
         List<Route> list = new ArrayList<>();
         String name="乐山";
         int start=0;
         try {
             list=jt.query("SELECT * from tab_route WHERE rname like '%"+name+"%' limit ?,8",new BeanPropertyRowMapper<>(Route.class),start);
         } catch (DataAccessException e) {
         }
         System.out.println(list);
    }*/
}
