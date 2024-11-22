package com.bertotti.backless.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bertotti.backless.model.User;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JWTAuth {
    static String JWT_SECRET = "backless-server";
    static String JWT_ISSUER = "Backless";
    public Algorithm algorithm;
    public JWTVerifier verifier;

    @Autowired
    private UserRepository userRepository;

    public JWTAuth() {
        algorithm = Algorithm.HMAC256(JWT_SECRET);
        verifier = JWT.require(algorithm).withIssuer(JWT_ISSUER).build();
    }

    public String createToken(User user) {
        return JWT.create()
            .withIssuer(JWT_ISSUER)
            .withSubject("login")
            .withClaim("id", user.getId().toString())
            .withIssuedAt(new Date())
            .withJWTId(UUID.randomUUID().toString())
            .sign(algorithm);
    }
    public DecodedJWT decode(String token) {
        return verifier.verify(token);
    }
    public Long extractId (String token) {
        return Long.valueOf(decode(token).getClaim("id").asString());
    }

    public User extractUser (String token) {
        Long tokenId = extractId(token);
        Optional<User> optionalUser = userRepository.findById(tokenId);
        if(!optionalUser.isPresent()) return null;
        return optionalUser.get();
    }
}
