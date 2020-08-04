package com.nataraj.management.employee.iems;

public enum EmployeeConstants {

    EMPLOYEE_KEY("EMPLOYEE");

    private String value;

    EmployeeConstants(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

}
