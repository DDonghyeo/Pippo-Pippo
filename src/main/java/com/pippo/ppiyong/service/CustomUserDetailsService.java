package com.pippo.ppiyong.service;

import com.pippo.ppiyong.auth.CustomUserDetail;
import com.pippo.ppiyong.domain.User;
import com.pippo.ppiyong.dto.RegisterRequestDto;
import com.pippo.ppiyong.dto.UserLoginDto;
import com.pippo.ppiyong.exception.CustomException;
import com.pippo.ppiyong.exception.ErrorCode;
import com.pippo.ppiyong.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailsService(UserRepository userRepository, @Lazy AuthenticationManager authenticationManager, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    //시큐리티 내부의 로그인 프로세스중 인증처리를 하는 메소드 중 하나
    //이 메서드를 오버라이드하여 데이터베이스의 유저 정보를 가져와서
    @Override
    public UserDetails loadUserByUsername(String email){
        log.info("Loading User : " + email);
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        log.info("user found : " + user.getEmail());
        log.info("user found : " + user.getNickName());
        return new CustomUserDetail(user);
    }

    //헤더에 담긴
    public void login(HttpServletRequest request, HttpServletResponse response,
                      UserLoginDto userLoginDto) {
        Authentication authentication = authenticateUser(userLoginDto.getEmail(), userLoginDto.getPassword());
        //인증된 객체 저장
        setAuthenticationInContext(authentication);
        createSessionAndSetCookie(request, response);
    }

    private Authentication authenticateUser(String email, String password) {
        UserDetails userDetails = loadUserByUsername(email);
        log.info("userDetail - Password : " + userDetails.getPassword());
        log.info("Present - Password : " + password);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, password);
        log.info("name : " + authentication.getName());

        try {
            return authenticationManager.authenticate(authentication);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }
    }

    private void setAuthenticationInContext(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private void createSessionAndSetCookie(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());

        Cookie cookie = new Cookie("JSESSIONID", session.getId());
        cookie.setPath("/");
        cookie.setMaxAge(30000 * 60);
        cookie.setSecure(true);
        response.addCookie(cookie);
    }

    public void signUpUser(RegisterRequestDto requestDto) {
        try {
            userRepository.save(requestDto.toEntity().encodePassword(passwordEncoder));
        } catch (Exception e) {
            throw new CustomException(ErrorCode.USER_DUPLICATION);
        }
    }
}
