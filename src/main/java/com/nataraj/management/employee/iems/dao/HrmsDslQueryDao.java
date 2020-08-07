package com.nataraj.management.employee.iems.dao;

import com.nataraj.management.employee.iems.entities.Employee;
import com.nataraj.management.employee.iems.entities.QEmployee;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;


@Component
public class HrmsDslQueryDao {

    @Autowired
    EntityManager em;

    public Double getMaxSalaryByLocation(String location){
        JPAQuery<Employee> empQuery = new JPAQuery<>(em);
        QEmployee qEmployee = QEmployee.employee;
        return empQuery.select(qEmployee.salary.max()).from(qEmployee).
                where(qEmployee.place.lower().eq(location.toLowerCase())).fetchOne();

    }

    public Double getMaxSalaryByBU(String location){
        JPAQuery<Employee> empQuery = new JPAQuery<>(em);
        QEmployee qEmployee = QEmployee.employee;
        return empQuery.select(qEmployee.salary.max()).from(qEmployee).
                where(qEmployee.bu.lower().eq(location.toLowerCase())).fetchOne();
    }

}
