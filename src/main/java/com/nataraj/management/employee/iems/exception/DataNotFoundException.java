package com.nataraj.management.employee.iems.exception;


public class DataNotFoundException extends BaseException {


    public DataNotFoundException(int code, String message) {
        super(code, message);
    }

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, Throwable th) {
        super(message, th);
    }
}
