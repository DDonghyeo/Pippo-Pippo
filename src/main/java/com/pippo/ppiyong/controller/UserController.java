package com.pippo.ppiyong.controller;

import com.pippo.ppiyong.dto.RegisterRequestDto;
import com.pippo.ppiyong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/user/register")
    public String saveUser(RegisterRequestDto registerRequestDto) {
        return  userService.saveUser(registerRequestDto);
    }
}
