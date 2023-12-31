package com.example.springjdbccrud.model;

import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Date;

@Data
@Table("articles")
public class Article implements Persistable<Long> {
    @Id
    private Long id;
    @Column("photo_url")
    private String photo;
    @Column("body_url")
    private String body;
    @Column("header")
    private String header;
    @Column("creation_date")
    private Date creation;
    @Column("user_id")
    private AggregateReference<User, Long> user;
    @Override
    public boolean isNew() {
        return id == null;
    }
}
