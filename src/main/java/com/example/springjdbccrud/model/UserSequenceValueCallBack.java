package com.example.springjdbccrud.model;

import lombok.AllArgsConstructor;
import org.springframework.data.relational.core.mapping.event.BeforeConvertCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
@AllArgsConstructor
public class UserSequenceValueCallBack implements BeforeConvertCallback<User> {
    private JdbcTemplate jdbcTemplate;

    @Override
    public User onBeforeConvert(User user) {
        if(user.isNew()) {
            Long id = jdbcTemplate.query("select nextval('users_id_seq')", rs -> {
                if(rs.next()) {
                    return rs.getLong(1);
                } else {
                    throw new SQLException("Can't reach nextvalue");
                }
            });
            user.setId(id);
        }
        return user;
    }
}
