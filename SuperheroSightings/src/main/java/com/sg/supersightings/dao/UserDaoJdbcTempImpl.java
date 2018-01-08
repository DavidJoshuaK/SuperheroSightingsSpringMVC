/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao;

import com.sg.supersightings.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author DavidKing
 */
public class UserDaoJdbcTempImpl implements UserDao {

    private static final String SQL_INSERT_USER
            = "insert into users (username, password, enabled) values (?, ?, 1)";
    private static final String SQL_INSERT_AUTHORITY
            = "insert into authorities (username, authority) values (?, ?)";
    private static final String SQL_DELETE_USER
            = "delete from users where username = ?";
    private static final String SQL_DELETE_AUTHORITIES
            = "delete from authorities where username = ?";
    private static final String SQL_GET_ALL_USERS
            = "select * from users";
    private static final String SQL_GET_USER_BY_ID
            = "select * from users where user_id=?";
    private static final String SQL_UPDATE_USER
            = "update users set username=?, password=? where user_id= ?";
//    private static final String SQL_UPDATE_AUTHORITY
//            = "update authoriteis username=?, "

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User addUser(User newUser) {
        jdbcTemplate.update(SQL_INSERT_USER,
                newUser.getUsername(),
                newUser.getPassword());
        newUser.setId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class));

        // now insert user's roles
        ArrayList<String> authorities = newUser.getAuthorities();
        for (String authority : authorities) {
            jdbcTemplate.update(SQL_INSERT_AUTHORITY,
                    newUser.getUsername(),
                    authority);
        }

        return newUser;
    }

    @Override
    public void deleteUser(String username) {
        // first delete all authorities for this user
        jdbcTemplate.update(SQL_DELETE_AUTHORITIES, username);
        // second delete the user
        jdbcTemplate.update(SQL_DELETE_USER, username);
    }
    
    @Override
    public void deleteAuthority(String username){
         jdbcTemplate.update(SQL_DELETE_AUTHORITIES, username);
    }
    
    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query(SQL_GET_ALL_USERS, new UserMapper());
    }

    @Override
    public void updateUser(User user) {
        jdbcTemplate.update(SQL_UPDATE_USER,
                user.getUsername(),
                user.getPassword(),
                user.getId());

        ArrayList<String> authorities = user.getAuthorities();
        for (String authority : authorities) {
            jdbcTemplate.update(SQL_INSERT_AUTHORITY,
                    user.getUsername(),
                    authority);
        }

    }

    @Override
    public User getUserById(int id) {
        return jdbcTemplate.queryForObject(SQL_GET_USER_BY_ID, 
                new UserMapper(), id);
    }

    private static final class UserMapper implements RowMapper<User> {

        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    }

}
