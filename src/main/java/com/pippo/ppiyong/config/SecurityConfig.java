package com.pippo.ppiyong.config;

import com.pippo.ppiyong.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                // 회원가입, 로그인 등 인증이 필요없는 요청에 대해서는 전체허용 (permitAll)
                // 그 외 모든 요청 (any) 에 대해서는 인증 요구
                .cors((cors) -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests((authorizeRequest) ->
                        authorizeRequest
                                .requestMatchers("/api/user/login").permitAll() //유저 로그인
                                .requestMatchers("/api/user/register").permitAll() //유저 로그인
                                .requestMatchers("/api/user/findPw").permitAll() //비밀번호 재발급
                                .requestMatchers("/api/usage").permitAll()
                                .requestMatchers("/api/home").permitAll() //메인페이지
                                .requestMatchers(HttpMethod.GET, "/api/post/*").permitAll() //게시물 상세조회
                                .requestMatchers("/api/shelter").permitAll() //대피소 조회
                                .requestMatchers("/api/user/emailCheck").permitAll() //대피소 조회
                                .requestMatchers("/api/user/verification").permitAll() //대피소 조회
                                .requestMatchers(/* swagger v2 */
                                        "/v2/api-docs",
                                        "/swagger-resources",
                                        "/swagger-resources/**",
                                        "/configuration/ui",
                                        "/configuration/security",
                                        "/swagger-ui.html",
                                        "/webjars/**",
                                        /* swagger v3 */
                                        "/v3/api-docs/**",
                                        "/swagger-ui/**").permitAll()
                                //외에 다른 Request는 인증되어야 함
                                .anyRequest().authenticated()
                )


                // Rest 방식으로 로그인을 할 것이므로 form 로그인 사용 안함
                .formLogin(AbstractHttpConfigurer::disable)

                // logout url 설정. 인증을 위해 사용하는 쿠키인 JSESSIONID를 삭제하여 로그아웃 처리
                .logout((configurer) ->
                        configurer
                                .logoutUrl("/api/users/logout")
                                .deleteCookies("JSESSIONID")
                )

                //인증되지 않은 자원에 접근했을 때
                .exceptionHandling((configurer) ->
                        configurer
                                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.FORBIDDEN)));


        return http.build();
    }


    //인증 작업해줄 Provider
    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(authProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // security 에서 제공하는 암호화 알고리즘
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("https://page.ppiyong.shop");
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8000","http://localhost:81","http://localhost:5500","http://localhost:8080", "http://127.0.0.1:81/", "https://page.ppiyong.shop/"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "OPTIONS", "PUT","DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(Boolean.TRUE);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}





