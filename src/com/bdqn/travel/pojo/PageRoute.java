package com.bdqn.travel.pojo;

import java.io.Serializable;
import java.util.List;

public class PageRoute<T> implements Serializable {
    /**
     * 当前第几页
     */
    private String curentPage;
    /**
     * 共多少条数据
     */
    private String routeCount;
    /**
     * 共多少页
     */
    private String pageCount;
    /**
     * 每一行显示的对象
     */
    private List<T> routeList;

    //getter sertter fn + alt + insert

    public String getCurentPage() {
        return curentPage;
    }

    public void setCurentPage(String curentPage) {
        this.curentPage = curentPage;
    }

    public String getRouteCount() {
        return routeCount;
    }

    public void setRouteCount(String routeCount) {
        this.routeCount = routeCount;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<T> routeList) {
        this.routeList = routeList;
    }

    @Override
    public String toString() {
        return "PageRoute{" +
                "curentPage='" + curentPage + '\'' +
                ", routeCount='" + routeCount + '\'' +
                ", pageCount='" + pageCount + '\'' +
                ", routeList=" + routeList +
                '}';
    }
}
