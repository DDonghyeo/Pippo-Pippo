package com.pippo.ppiyong.service;

import com.pippo.ppiyong.domain.User;
import com.pippo.ppiyong.dto.RegisterRequestDto;
import com.pippo.ppiyong.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;


    public String saveUser(RegisterRequestDto registerRequestDto) {
        userRepository.save(registerRequestDto.toEntity().encodePassword(passwordEncoder));
        return registerRequestDto.getEmail();
    }

}
