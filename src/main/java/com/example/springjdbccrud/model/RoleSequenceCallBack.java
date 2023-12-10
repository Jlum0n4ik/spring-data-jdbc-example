package com.example.springjdbccrud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.event.BeforeConvertCallback;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

@Data
@AllArgsConstructor
public class RoleSequenceCallBack implements BeforeConvertCallback<Role> {
    private JdbcTemplate jdbcTemplate;

    @Override
    public Role onBeforeConvert(Role role) {
        if(role.isNew()) {
            Integer id = jdbcTemplate.query("select nextval('roles_id_seq')", rs -> {
                if(rs.next()){
                    return rs.getInt(1);
                } else {
                    throw new SQLException("Can't reach value for role");
                }
            });
            role.setId(id);
        }
        return role;
    }
}
