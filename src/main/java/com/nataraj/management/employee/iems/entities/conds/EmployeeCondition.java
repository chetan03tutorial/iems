package com.nataraj.management.employee.iems.entities.conds;


import com.nataraj.management.employee.iems.entities.QEmployee;
import com.querydsl.core.types.Predicate;

public final class EmployeeCondition {

    private EmployeeCondition(){

    }

    public static Predicate fromPlace(String location){
        return QEmployee.employee.place.eq(location);
    }

    public static Predicate fromBU(String businessUnit){
        return QEmployee.employee.bu.eq(businessUnit);
    }

    public static Predicate condition(){
        return null;
    }
}
