package com.example.springjdbccrud.repository;

import com.example.springjdbccrud.model.Article;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {
    List<Article> findAll();
    <T>T save(T entity);
    @Modifying
    @Query("delete from articles a where a.id = :id")
    void deleteById(@Param("id") Long id);

}
