package com.example.springjdbccrud.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "roles")
public class Role implements Persistable<Integer> {
    @Id
    private Integer id;
    private String name;

    @Override
    public boolean isNew() {
        return id == null;
    }
}
