package com.skyeagle.meeting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @projectName:meeting
 * @packageName:com.skyeagle.meeting.config
 * @className:CORSConfig
 * @Description:[一句话描述该类的功能]
 * @Author:lvyang
 * @CreateDate:2021-2-3 9:52
 */

@Configuration
public class CORSConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedHeaders("*")
                        .allowedMethods("*")
                        .allowedOrigins("*");
            }
        };
    }
}