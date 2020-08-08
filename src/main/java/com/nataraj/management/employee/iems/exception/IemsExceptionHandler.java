package com.nataraj.management.employee.iems.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@RestControllerAdvice
public class IemsExceptionHandler {


    private Logger logger;

    @ExceptionHandler(value = { DataNotFoundException.class })
    public ResponseEntity<IemsErrorResponse> notFoundExceptionHandler(DataNotFoundException ex)
    {
        //log the exception ex.getMessage() and then build response
        return buildResponseEntityWithError(ex,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { BadRequestException.class })
    public ResponseEntity<IemsErrorResponse> badRequestExceptionHandler(BadRequestException ex)
    {
        //log the exception and then build response
        return buildResponseEntityWithError(ex,HttpStatus.BAD_REQUEST);
    }

    private IemsErrorResponse buildErrorResponse(BaseException ex, HttpStatus status){
        IemsErrorResponse error = new IemsErrorResponse();
        error.setCode(ex.getErrorCode());
        error.setMessage(ex.getErrorMessage());
        error.setTimestamp(LocalDateTime.now());
        error.setHttpStatus(status.value());
        return error;
    }

    private ResponseEntity<IemsErrorResponse> buildResponseEntityWithError(BaseException ex,HttpStatus status){
        return new ResponseEntity<IemsErrorResponse>(buildErrorResponse(ex,status),status);
    }
}
