package com.pippo.ppiyong.dto;

import com.pippo.ppiyong.domain.User;
import com.pippo.ppiyong.type.Region;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
                .build();
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
