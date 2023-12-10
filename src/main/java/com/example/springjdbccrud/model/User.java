package com.example.springjdbccrud.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Data
@Table("users")
public class User implements Persistable<Long> {
    @Id
    private Long id;
    @Column("f_name")
    private String name;
    @Column("l_name")
    private String surname;
    private String email;
    private String password;
    @Column("role_id")
    private AggregateReference<Role, Integer> role;

    @Override
    public boolean isNew() {
        return id == null;
    }
}
