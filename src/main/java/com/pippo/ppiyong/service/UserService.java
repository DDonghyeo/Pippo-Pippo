package com.pippo.ppiyong.service;

import com.pippo.ppiyong.dto.RegisterRequestDto;
import com.pippo.ppiyong.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public String saveUser(RegisterRequestDto registerRequestDto) {
        //패스워드 암호화
        registerRequestDto.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        userRepository.save(registerRequestDto.toEntity());
        return registerRequestDto.getEmail();
    }

}
