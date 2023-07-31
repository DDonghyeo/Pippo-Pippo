package com.pippo.ppiyong.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Getter
@AllArgsConstructor
public enum ErrorCode {


    /* 200 NO_CONTENT : 자료를 찾을 수 없음 */
    NO_CONTENT_FOUND(NO_CONTENT, 204, "요청된 자료를 찾을 수 없습니다."),

    /* 400 BAD_REQUEST : 잘못된 요청 */
    INVALID_VALUE(BAD_REQUEST, 400, "잘못된 값입니다." ),


    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    INVALID_SESSION(UNAUTHORIZED,401, "권한 정보가 없는 세션입니다"),
    UNAUTHORIZED_MEMBER(UNAUTHORIZED,401, "현재 내 계정 정보가 존재하지 않습니다"),


    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    MEMBER_NOT_FOUND(NOT_FOUND,404, "해당 유저 정보를 찾을 수 없습니다"),


    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
    DUPLICATE_RESOURCE(CONFLICT,409, "데이터가 이미 존재합니다"),

    //500 INTERNAL SERVER ERROR
    SERVER_ERROR(INTERNAL_SERVER_ERROR, 500, "서버 에러입니다.");


    private final HttpStatus httpStatus;
    private final int errorCode;
    private final String detail;
}
