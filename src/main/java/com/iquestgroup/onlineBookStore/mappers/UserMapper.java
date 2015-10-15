package com.iquestgroup.onlineBookStore.mappers;

import com.iquestgroup.onlineBookStore.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by stefan.pamparau on 10/15/2015.
 */
public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        User user = new User();
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }

}
