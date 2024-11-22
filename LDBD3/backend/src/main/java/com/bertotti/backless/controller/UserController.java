package com.bertotti.backless.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bertotti.backless.dto.UserTokenPayload;
import com.bertotti.backless.model.Article;
import com.bertotti.backless.model.User;
import com.bertotti.backless.service.ArticleRepository;
import com.bertotti.backless.service.JWTAuth;
import com.bertotti.backless.service.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("${apiPrefix}/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ArticleRepository articleRepository;
    
    @Autowired
    private JWTAuth jwtAuth;

    @Autowired
    private ArticleController articleController;

    @GetMapping("{id}")
    public ResponseEntity<Optional<User>> getById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("{id}/articles/{index}")
    public ResponseEntity<Page<Article>> getPageableArticleList(@PathVariable Long id, @PathVariable Integer index) {
        return articleController.getPageableList(id, index);
    }

    @GetMapping("list/{index}")
    public ResponseEntity<Page<User>> getPageableList(@PathVariable Integer index) {
        Pageable pageLength10 = PageRequest.of(index, 10); 
        Page<User> userPage = userRepository.findAll(pageLength10);

        return ResponseEntity.ok(userPage);
    }
    
    @PostMapping("signup")
    public ResponseEntity<User> signup(@Valid @RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }
    
    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody User user) {
        User loggedUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        
        if (loggedUser == null) {
            return ResponseEntity.ok("");
        }

        String token = jwtAuth.createToken(loggedUser);
        return ResponseEntity.ok(token);
    }

    @PostMapping("edit")
    public ResponseEntity<User> edit(@RequestBody UserTokenPayload payload) {
        User userEdit = jwtAuth.extractUser(payload.getToken());
        if(userEdit == null) return ResponseEntity.ok(null);
        
        User userPayload = payload.getUser();
        
        if(userPayload.getEmail() != null || !userPayload.getEmail().equals("")) {
            userEdit.setEmail(userPayload.getEmail());
        }
        if(userPayload.getName() != null || !userPayload.getName().equals("")) {
            userEdit.setName(userPayload.getName());
        }

        User savedUser = userRepository.save(userEdit);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("delete")
    public ResponseEntity<String> delete(@RequestBody UserTokenPayload payload) {
        User user = jwtAuth.extractUser(payload.getToken());
        if(user == null) return ResponseEntity.ok(null);

        List<Article> articleList = articleRepository.findAllByUserId(user.getId());
        
        for (Article article: articleList) {
            articleRepository.delete(article);
        }

        userRepository.delete(user);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("verify-me/{token}/{id}")
    public ResponseEntity<Boolean> verifyMe(@PathVariable String token, @PathVariable Long id) {
        Long tokenId = jwtAuth.extractId(token);
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) return ResponseEntity.ok(false);
        return ResponseEntity.ok(tokenId.equals(user.get().getId()));
    }

    @GetMapping("verify/{token}")
    public ResponseEntity<Boolean> verify(@PathVariable String token) {
        try {
            User user = jwtAuth.extractUser(token);
            return ResponseEntity.ok(user != null);
        } catch (Error e) {
            return ResponseEntity.ok(false);
        }
    }
}
