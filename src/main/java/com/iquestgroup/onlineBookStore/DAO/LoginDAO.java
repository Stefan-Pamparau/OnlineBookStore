package com.iquestgroup.onlineBookStore.DAO;

import com.iquestgroup.onlineBookStore.mappers.UserMapper;
import com.iquestgroup.onlineBookStore.model.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by stefan.pamparau on 10/15/2015.
 */
public class LoginDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public boolean checkUser(String username, String password) {
        String queryUser = "SELECT * FROM User WHERE name = ? and password = ?";
        User user = jdbcTemplate.queryForObject(queryUser, new Object[] {username, password}, new UserMapper());

        return user != null;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        initializeJdbcTemplate();
    }

    private void initializeJdbcTemplate() {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
