package com.bertotti.backless.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@Nullable CorsRegistry registry) {
        if(registry == null) return;
        registry.addMapping("/**").allowedOriginPatterns("http://localhost:*");
    }
}