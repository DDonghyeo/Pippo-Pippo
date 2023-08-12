package com.pippo.ppiyong.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // API 엔드포인트에 대해서만 적용할 수도 있습니다.
                .allowedOrigins("*") // 허용할 오리진(도메인)을 지정합니다.
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메서드를 지정합니다.
                .allowCredentials(true) // 쿠키를 포함할 경우 true로 설정합니다.
                .exposedHeaders("Set-Cookie"); // 노출할 응답 헤더를 지정합니다.
    }
}
