package com.bertotti.backless.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bertotti.backless.dto.ArticleTokenPayload;
import com.bertotti.backless.model.Article;
import com.bertotti.backless.model.User;
import com.bertotti.backless.service.ArticleRepository;
import com.bertotti.backless.service.JWTAuth;

@RestController
@RequestMapping("${apiPrefix}/article")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private JWTAuth jwtAuth;


    @GetMapping("{id}")
    public ResponseEntity<Article> get(@PathVariable Long id) {
        Article article = articleRepository.findById(id).get();
        return ResponseEntity.ok(article);
    }

    @GetMapping("from-user/{userId}/list/{index}")
    public ResponseEntity<Page<Article>> getPageableList(@PathVariable Long userId, @PathVariable Integer index) {
        Pageable pageLength10 = PageRequest.of(index, 10); 
        Page<Article> articlePage = articleRepository.findAllByUserId(userId, pageLength10);

        return ResponseEntity.ok(articlePage);
    }

    @GetMapping("list/{index}")
    private ResponseEntity<Page<Article>> getPageableList(@PathVariable Integer index) {
        Pageable pageLength10 = PageRequest.of(index, 10); 
        Page<Article> articlePage = articleRepository.findAll(pageLength10);

        return ResponseEntity.ok(articlePage);
    }

    @PostMapping("create")
    private ResponseEntity<Article> create(@RequestBody ArticleTokenPayload payload) {
        User user = jwtAuth.extractUser(payload.getToken());
        if(user == null) return ResponseEntity.ok(null);

        Article article = payload.getArticle();
        article.setUser(user);

        Article savedArticle = articleRepository.save(article);

        return ResponseEntity.ok(savedArticle);
    }

    @PostMapping("edit")
    private ResponseEntity<Article> edit(@RequestBody ArticleTokenPayload payload) {
        User user = jwtAuth.extractUser(payload.getToken());
        if(user == null) return ResponseEntity.ok(null);

        Article articlePayload = payload.getArticle();
        Article articleEdit = articleRepository.findById(articlePayload.getId()).get();

        if(articleEdit == null || !articleEdit.getUser().equals(user)) return ResponseEntity.ok(null);

        if(articlePayload.getTitle() != null || !articlePayload.getTitle().equals("")) {
            articleEdit.setTitle(articlePayload.getTitle());
        }
        if(articlePayload.getContent() != null || !articlePayload.getContent().equals("")) {
            articleEdit.setContent(articlePayload.getContent());
        }

        Article savedArticle = articleRepository.save(articleEdit);

        return ResponseEntity.ok(savedArticle);
    }

    @PostMapping("delete")
    private ResponseEntity<String> delete(@RequestBody ArticleTokenPayload payload) {
        User user = jwtAuth.extractUser(payload.getToken());
        Article articlePayload = payload.getArticle();
        Article article = articleRepository.findById(articlePayload.getId()).get();
        if(user == null || article == null || !article.getUser().equals(user)) return ResponseEntity.ok(null);

        articleRepository.delete(article);

        return ResponseEntity.ok("ok");
    }
}
