package com.bertotti.backless.dto;

import com.bertotti.backless.model.User;

import lombok.Getter;

public class UserTokenPayload {
    @Getter
    private String token;
    
    @Getter
    private User user;
}
