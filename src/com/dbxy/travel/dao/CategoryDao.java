package com.dbxy.travel.dao;

import com.bdqn.travel.pojo.Category;

import java.util.List;

public interface CategoryDao {
    public List<Category> findAllCategory() ;
    Category findCategoryByCid(int cid);
}
