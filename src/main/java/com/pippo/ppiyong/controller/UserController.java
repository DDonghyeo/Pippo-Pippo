package com.pippo.ppiyong.controller;

import com.pippo.ppiyong.dto.RegisterRequestDto;
import com.pippo.ppiyong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/user/register")
    public String saveUser(RegisterRequestDto registerRequestDto) {
        return  userService.saveUser(registerRequestDto);
    }
}
