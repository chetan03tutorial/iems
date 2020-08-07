package com.nataraj.management.employee.iems.constants;

public enum RedisKeyEmployee {

    EMPLOYEE("EMPLOYEE"),
    PLACE("PLACE");

    private String value;

    RedisKeyEmployee(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

    public String toString(){
        return this.value;
    }

}
