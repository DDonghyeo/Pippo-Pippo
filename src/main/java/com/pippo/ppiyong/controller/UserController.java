package com.pippo.ppiyong.controller;

import com.pippo.ppiyong.auth.CustomUserDetail;
import com.pippo.ppiyong.dto.RegisterRequestDto;
import com.pippo.ppiyong.dto.UpdatePasswordDto;
import com.pippo.ppiyong.dto.UserLoginDto;
import com.pippo.ppiyong.exception.CustomException;
import com.pippo.ppiyong.exception.ErrorCode;
import com.pippo.ppiyong.service.CustomUserDetailsService;
import com.pippo.ppiyong.service.EmailService;
import com.pippo.ppiyong.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
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
    private final EmailService emailService;

    @Autowired
    private final CustomUserDetailsService customUserDetailsService;

    //회원가입
    @Operation(summary = "사용자 회원가입", description = "Request Body : email, password, nickName, region 담아서 요청")
    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody RegisterRequestDto registerRequestDto) {
        customUserDetailsService.signUpUser(registerRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //로그인
    @Operation(summary = "사용자 로그인", description = "Request Body : email, password 담아서 요청")
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDto userLoginDto , HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        customUserDetailsService.login(servletRequest, servletResponse, userLoginDto);
        return ResponseEntity.ok("요청에 성공했습니다.");
    }

    //닉네임 변경
    @Operation(summary = "사용자 닉네임 변경", description = "Query String : nickName 담아서 요청")
    @PutMapping("/nickname")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateNickName(@AuthenticationPrincipal CustomUserDetail customUserDetail, @RequestParam("nickName") String nickName) {
        userService.updateNickName(customUserDetail.getUserEmail(), nickName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //지역 변경
    @Operation(summary = "사용자 지역 변경", description = "Query String : region 담아서 요청")
    @PutMapping("/region")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateRegion(@AuthenticationPrincipal CustomUserDetail customUserDetail, @RequestParam("region") String region) {
        userService.updateRegion(customUserDetail.getUserEmail(), region);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //비밀번호 변경
    @Operation(summary = "사용자 비밀번호 변경 (로그인 상태) ", description = "Request Body : password 담아서 요청")
    @PutMapping("/pw")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updatePW(@AuthenticationPrincipal CustomUserDetail customUserDetail, @RequestBody UpdatePasswordDto updatePasswordDto) {
        userService.updatePaswword(customUserDetail.getUserEmail(), updatePasswordDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //회원정보 조회
    @Operation(summary = "사용자 조회 ", description = "파라미터 X")
    @GetMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updatePW(@AuthenticationPrincipal CustomUserDetail customUserDetail) {
        return ResponseEntity.ok(userService.getUserInfo(customUserDetail.getUserEmail()));
    }


    //비밀번호 재발급
    @Operation(summary = "사용자 비밀번호 재발급 ", description = "Query String : email 전달 시 서버에서 사용자 이메일로 임시 비밀번호를 보내고, 사용자 비밀번호를 해당 비밀번호로 변경")
    @PostMapping("/findPw")
    public ResponseEntity<?> passWordReissuance(@RequestParam("email") String email) {
        try {
            emailService.sendMessageForPassword(email);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(ErrorCode.INVALID_VALUE);
        }
    }

    //로그아웃
    @GetMapping("/logout")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<?> logout(@AuthenticationPrincipal CustomUserDetail customUserDetail) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
