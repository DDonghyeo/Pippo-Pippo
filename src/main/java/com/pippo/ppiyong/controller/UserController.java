package com.pippo.ppiyong.controller;

import com.pippo.ppiyong.dto.RegisterRequestDto;
import com.pippo.ppiyong.dto.UserLoginDto;
import com.pippo.ppiyong.service.UserService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @PostMapping("/api/user/register")
    public String saveUser(@RequestBody RegisterRequestDto registerRequestDto) {
        return  userService.saveUser(registerRequestDto);
    }

    @PostMapping("/api/user/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDto userLoginDto , HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        userService.userLogin(userLoginDto, servletRequest, servletResponse);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
