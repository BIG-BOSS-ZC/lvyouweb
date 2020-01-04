package com.dbxy.travel.dao;

import com.dbxy.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import com.bdqn.travel.pojo.Category;

public class CategoryDaoImpl implements CategoryDao {
    private JdbcTemplate jt=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Category> findAllCategory() {
        List<Category> list=jt.query("select * from tab_category ORDER BY cid asc",new BeanPropertyRowMapper<Category>(Category.class));
        return list;
    }

    @Override
    public Category findCategoryByCid(int cid) {
        Category category=jt.queryForObject("select * from tab_category where cid=?",new BeanPropertyRowMapper<>(Category.class),cid);
        return category;
    }

    /*public static void main(String[] args) {
        JdbcTemplate jt=new JdbcTemplate(JDBCUtils.getDataSource());
        Category category=jt.queryForObject("select * from tab_category where cid=?",new BeanPropertyRowMapper<>(Category.class),5);
        System.out.println(category);
    }*/
}
