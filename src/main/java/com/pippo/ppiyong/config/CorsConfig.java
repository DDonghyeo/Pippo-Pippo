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
        registry.addMapping("/**") // 모든 엔드포인트에 대해 CORS 설정을 적용합니다.
                .allowedOrigins("http://localhost:8000","http://localhost:81","http://localhost:5500") // 모든 오리진을 허용합니다.
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메서드를 지정합니다.
                .allowCredentials(true) // 쿠키를 포함할 경우 true로 설정합니다.
                .exposedHeaders("Set-Cookie"); // 노출할 응답 헤더를 지정합니다.
    }
}
