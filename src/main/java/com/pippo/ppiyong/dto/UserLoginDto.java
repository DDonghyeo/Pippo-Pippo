package com.pippo.ppiyong.dto;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class UserLoginDto {

    String email;

    String password;
}
