package com.pippo.ppiyong.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoResponseDto {

    String email;

    String nickName;

    String region;
}
