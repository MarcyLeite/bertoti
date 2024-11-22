package com.bertotti.backless.dto;

import com.bertotti.backless.model.Article;

import lombok.Getter;

public class ArticleTokenPayload {
    @Getter
    private String token;

    @Getter
    private Article article;
}
