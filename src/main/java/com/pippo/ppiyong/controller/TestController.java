package com.pippo.ppiyong.controller;


import com.pippo.ppiyong.domain.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("")
public class TestController {


    @GetMapping("/test")
    public String test() {
        return "Test !!";
    }
}
