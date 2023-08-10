package com.pippo.ppiyong.dto;

import com.pippo.ppiyong.domain.User;
import com.pippo.ppiyong.type.Region;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegisterResponseDto {

    // 회원 가입이 완료된 후 보여줄 정보를 담는 DTO
    private String email;
    private String nickName;
    private Region region;

    public RegisterResponseDto(User user) {
        this.email = user.getEmail();
        this.nickName = user.getNickName();
        this.region = user.getRegion();
    }

}
