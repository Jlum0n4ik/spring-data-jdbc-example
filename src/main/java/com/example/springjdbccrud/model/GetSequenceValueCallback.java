package com.example.springjdbccrud.model;

import lombok.AllArgsConstructor;
import org.springframework.data.relational.core.mapping.event.BeforeConvertCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
@AllArgsConstructor
public class GetSequenceValueCallback implements BeforeConvertCallback<Article> {
    private JdbcTemplate jdbcTemplate;
    @Override
    public Article onBeforeConvert(Article article) {
        if (article.getId() == null) {
            Long id = jdbcTemplate.query("select nextval('articles_id_seq')", rs -> {
                if(rs.next()) {
                    return rs.getLong(1);
                } else {
                    throw new SQLException("Can't reach id");
                }
            });
            article.setId(id);
        }
        return article;
    }
}
