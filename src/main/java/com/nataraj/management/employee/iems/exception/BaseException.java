package com.nataraj.management.employee.iems.exception;

abstract public class BaseException extends RuntimeException{
    private int errorCode;
    private String errorMessage;


    public BaseException(int code, String message){
        this.errorCode = code;
        this.errorMessage = message;
    }

    public BaseException(String message){
        super(message);
    }

    public BaseException(String message, Throwable th){
        super(message,th);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
