package com.TeamProject.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class configuration implements WebMvcConfigurer {
	
   @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // "/api" 접두사 아래의 모든 경로에 대해 CORS를 구성한다.
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("Content-Type", "Authorization") // 요청에 포함될 수 있는 허용할 헤더 지정
                .allowCredentials(true) // 자격 증명 허용 (쿠키, 인증 헤더 등)
        		.exposedHeaders("Authorization"); //헤더에 (Authorization) 포함
    }
}


