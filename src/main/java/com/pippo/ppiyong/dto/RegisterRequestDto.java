package com.pippo.ppiyong.dto;

import com.pippo.ppiyong.auth.Authority;
import com.pippo.ppiyong.domain.User;
import com.pippo.ppiyong.type.Region;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
    private String email;
    private String password;
    private String nickName;
    private Region region;

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .nickName(nickName)
                .region(region)
                .authority(Authority.ROLE_USER)
                .build();
    }

}
