package dev.rahulrajesh.PicPayData.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;

@Component
public class LoginCreatorDestroyer {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LoginCreatorDestroyer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createLogin(long user, String username, String password) {
        jdbcTemplate.execute(String
                .format("INSERT INTO login(user, username, password) VALUES('%d','%s', '%s')",
                        user, username, password));
    }

    public void destroyLoginByUsername(String username) throws DataAccessException {
        jdbcTemplate.execute(String.format("DELETE FROM login WHERE username='%s'", username));
    }


}
