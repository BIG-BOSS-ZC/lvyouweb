package com.dbxy.travel.service;

import com.bdqn.travel.pojo.Category;
import com.dbxy.travel.dao.CategoryDao;
import com.dbxy.travel.dao.CategoryDaoImpl;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao dao=new CategoryDaoImpl();
    @Override
    public List<Category> findAllCategory() {
        List<Category> titles=dao.findAllCategory();
        return titles;
    }
    public Category findCategoryByCid(int cid) {
        return dao.findCategoryByCid(cid);
    }
}
