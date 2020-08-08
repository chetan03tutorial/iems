package com.nataraj.management.employee.iems.exception;

public class BadRequestException extends BaseException {


    public BadRequestException(int code, String message) {
        super(code, message);
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable th) {
        super(message, th);
    }
}
