package com.pippo.ppiyong.controller;

import com.pippo.ppiyong.auth.CustomUserDetail;
import com.pippo.ppiyong.dto.RegisterRequestDto;
import com.pippo.ppiyong.dto.UserLoginDto;
import com.pippo.ppiyong.service.CustomUserDetailsService;
import com.pippo.ppiyong.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final CustomUserDetailsService customUserDetailsService;

    //회원가입
    @PostMapping("register")
    public ResponseEntity<?> saveUser(@RequestBody RegisterRequestDto registerRequestDto) {
        customUserDetailsService.signUpUser(registerRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //로그인
    @PostMapping("login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDto userLoginDto , HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        customUserDetailsService.login(servletRequest, servletResponse, userLoginDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //닉네임 변경
    @PutMapping("nickname")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateNickName(@AuthenticationPrincipal CustomUserDetail customUserDetail, @RequestParam("nickName") String nickName) {
        userService.updateNickName(customUserDetail.getUserEmail(), nickName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //지역 변경
    @PutMapping("nickname")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateRegion(@AuthenticationPrincipal CustomUserDetail customUserDetail, @RequestParam("region") String region) {
        userService.updateRegion(customUserDetail.getUserEmail(), region);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
