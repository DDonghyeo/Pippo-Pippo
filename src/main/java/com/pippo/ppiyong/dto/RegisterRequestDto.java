package com.pippo.ppiyong.dto;

import com.pippo.ppiyong.auth.Authority;
import com.pippo.ppiyong.domain.User;
import com.pippo.ppiyong.type.Region;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
    @NotNull(message = "email은 필수입니다.")
    private String email;

    @NotNull(message = "비밀번호는 필수입니다.")
    private String password;
    @NotNull(message = "닉네임은 필수입니다.")
    private String nickName;
    @NotNull(message = "지역은 필수입니다.")
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
