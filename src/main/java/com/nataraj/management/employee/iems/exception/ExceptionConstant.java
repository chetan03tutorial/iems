package com.nataraj.management.employee.iems.exception;

public enum ExceptionConstant {
    NOT_FOUND_EXCEPTION(29018,"NOT_FOUND"),
    BAD_REQUEST_EXCEPTION(29019, "BAD_REQUEST");


    public int code;
    public String message;
    private ExceptionConstant(int code, String message){
        this.code = code;
        this.message = message;
    }

}
