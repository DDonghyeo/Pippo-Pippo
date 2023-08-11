package com.pippo.ppiyong.service;

import com.pippo.ppiyong.domain.User;
import com.pippo.ppiyong.dto.RegisterRequestDto;
import com.pippo.ppiyong.dto.UserLoginDto;
import com.pippo.ppiyong.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    public String saveUser(RegisterRequestDto registerRequestDto) {
        userRepository.save(registerRequestDto.toEntity().encodePassword(passwordEncoder));
        return registerRequestDto.getEmail();
    }

    public void userLogin(UserLoginDto userLoginDto , HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        customUserDetailsService.authenticateUser(userLoginDto.getEmail(), userLoginDto.getPassword());
        customUserDetailsService.createSessionAndSetCookie(httpServletRequest, httpServletResponse);
    }

}
