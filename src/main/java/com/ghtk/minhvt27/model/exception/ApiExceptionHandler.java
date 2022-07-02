package com.ghtk.minhvt27.model.exception;

import com.ghtk.minhvt27.model.response.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseData productNotFoundException(NotFoundException ex) {
        return new ResponseData(false, ex.getMessage() , null);
    }

}
