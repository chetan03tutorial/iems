package com.nataraj.management.employee.iems.mappers;

import com.nataraj.management.employee.iems.entities.Employee;
import com.nataraj.management.employee.iems.model.request.Associate;


public class EmployeeMapper {



    public static Employee mapEmployee(Associate associate){
        Employee employee = new Employee();
        employee.setBu(associate.getBu());
        employee.setCompetencies(associate.getCompetencies());
        employee.setName(associate.getName());
        employee.setPlace(associate.getPlace());
        employee.setSalary(associate.getSalary());
        employee.setSupervisor(associate.getSupervisor());
        employee.setTitle(associate.getTitle());
        return employee;
    }
}
