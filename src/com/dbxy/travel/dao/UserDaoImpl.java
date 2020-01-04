package com.dbxy.travel.dao;

import com.bdqn.travel.pojo.User;
import com.dbxy.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao{
    private JdbcTemplate jt=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public User findUserByName(User user) {
        User u=null;
        try {
            u=jt.queryForObject("SELECT * FROM tab_user WHERE username=?",new
                    BeanPropertyRowMapper<User>(User.class),user.getUsername());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public User checkUser(User user) {
        User u=null;
        try {
            u=jt.queryForObject("SELECT * FROM tab_user WHERE username=? and password=?",new
                    BeanPropertyRowMapper<User>(User.class),user.getUsername(),user.getPassword());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public int updateStatus(String code) {
        return jt.update("update tab_user set status='Y' where code=?",code);
    }

    @Override
    public int addUser(User user) {
        int len=0;
        try {
            len=jt.update("insert into tab_user values (null,?,?,?,?,?,?,?,?,?)",
                    user.getUsername(),user.getPassword(),user.getName(),
                    user.getBirthday(),user.getSex(),user.getTelephone(),user.getEmail(),
                    user.getStatus(),user.getCode());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return len;
    }
}
