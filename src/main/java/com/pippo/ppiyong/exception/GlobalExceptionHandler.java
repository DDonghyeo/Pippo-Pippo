package com.pippo.ppiyong.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.pippo.ppiyong.exception.ErrorCode.INVALID_VALUE;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //custom Exception
    @ExceptionHandler({CustomException.class})
    protected ResponseEntity<?> handlecustomException(CustomException e) {
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(new ErrorResponse(e));
    }

    //@valid Exception
    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        CustomException validException = new CustomException(INVALID_VALUE, message);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(validException));
    }
}
