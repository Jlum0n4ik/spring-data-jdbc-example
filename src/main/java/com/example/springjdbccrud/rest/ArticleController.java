package com.example.springjdbccrud.rest;

import com.example.springjdbccrud.model.Article;
import com.example.springjdbccrud.model.GetSequenceValueCallback;
import com.example.springjdbccrud.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@AllArgsConstructor
public class ArticleController {
    private ArticleRepository articleRepository;
    private GetSequenceValueCallback getSequenceValueCallback;
    @GetMapping("/")
    public ResponseEntity<List<Article>> allArticles() {
        var articles = articleRepository.findAll();
        if(articles == null || articles.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<Article> saveArticle(@RequestBody Article article) {
        if(article != null) {
            var saveArticle = getSequenceValueCallback.onBeforeConvert(article);
            articleRepository.save(saveArticle);
            return new ResponseEntity<>(saveArticle,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@RequestBody Article article) {
        if (!article.isNew()) {
            articleRepository.save(article);
            return new ResponseEntity<>(article, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Article> deleteArticle(@PathVariable Long id) {
        if (id != null) {
            articleRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
