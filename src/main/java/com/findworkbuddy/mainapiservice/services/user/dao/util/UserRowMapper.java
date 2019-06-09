package com.findworkbuddy.mainapiservice.services.user.dao.util;

import com.findworkbuddy.mainapiservice.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();

        user.setId(rs.getInt("user_id"));
        user.setFirstName(rs.getString("user_firstName"));
        user.setLastName(rs.getString("user_lastName"));
        user.setEmail(rs.getString("user_email"));
        user.setHeadLine(rs.getString("user_headline"));
        user.setSummary(rs.getString("user_summary"));

        return user;
    }
}