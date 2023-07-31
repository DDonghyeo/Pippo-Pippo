package com.pippo.ppiyong.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {

    private final String message;

    public ErrorResponse(CustomException e){
        this.message = e.getErrorMessage();
    }

}
