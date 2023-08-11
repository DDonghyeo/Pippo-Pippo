package com.pippo.ppiyong.service;

import com.pippo.ppiyong.domain.User;
import com.pippo.ppiyong.dto.RegisterRequestDto;
import com.pippo.ppiyong.dto.UpdatePasswordDto;
import com.pippo.ppiyong.dto.UserInfoResponseDto;
import com.pippo.ppiyong.dto.UserLoginDto;
import com.pippo.ppiyong.exception.CustomException;
import com.pippo.ppiyong.exception.ErrorCode;
import com.pippo.ppiyong.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    //닉네임 변경
    public void updateNickName(String email, String nickName) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        user.updateNickName(nickName);
        userRepository.save(user);
    }

    //지역 변경
    //닉네임 변경
    public void updateRegion(String email, String region) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        user.updateRegion(region);
        userRepository.save(user);
    }

    //비밀번호 변경
    public void updatePaswword(String email, UpdatePasswordDto updatePasswordDto) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        user.updatePassword(passwordEncoder, updatePasswordDto.getPassword());
        userRepository.save(user);
    }

    //회원 정보 가져오기
    public UserInfoResponseDto getUserInfo(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        return UserInfoResponseDto.builder()
                .email(user.getEmail())
                .region(user.getRegion().toString())
                .nickName(user.getNickName())
                .build();
    }
}
