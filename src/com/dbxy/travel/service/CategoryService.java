package com.dbxy.travel.service;

import com.bdqn.travel.pojo.Category;
import com.bdqn.travel.pojo.ResultInfo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

public interface CategoryService {
    public List<Category> findAllCategory();
    public Category findCategoryByCid(int cid) ;
}
