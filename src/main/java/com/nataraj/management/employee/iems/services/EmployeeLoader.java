package com.nataraj.management.employee.iems.services;

import com.nataraj.management.employee.iems.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeLoader {

    @Autowired
    private HrmsService hrmsService;

    public void readCSV(String filePath){
        List<Employee> employees = new ArrayList<>();
        InputStream inputFileStream = this.getClass().getClassLoader().getResourceAsStream("dev/employee_details.csv");
        BufferedReader br = new BufferedReader(new InputStreamReader(inputFileStream));
        employees = br.lines().skip(1).map(this::mapToEmployee).collect(Collectors.toList());
        System.out.println("HRMS Service Id is " + hrmsService);
        employees.forEach(hrmsService::save);
    }


    public Employee mapToEmployee(String line){
        String[] fields = line.split(",");
        Employee employee = new Employee();
        Optional<Long> id = Optional.ofNullable(fields[0]).map(Long::valueOf);

        // Skip the row when ID is null
        employee.setId(id.get());
        employee.setName(fields[1]);
        employee.setBu(fields[2]);
        employee.setPlace(fields[3]);

        Optional<Long> supervisor = Optional.ofNullable(fields[5]).map(Long::valueOf);
        Optional<Double> salary = Optional.ofNullable(fields[4]).map(Double::valueOf);

        employee.setSalary(salary.isPresent() ? salary.get() : Double.valueOf("0") );
        employee.setSupervisor(supervisor.isPresent() ? supervisor.get(): Long.valueOf("0"));
        employee.setTitle(fields[6]);

        return employee;
    };

    /*public static void main(String[] args) {
        EmployeeLoader employeeLoader = new EmployeeLoader();
        List<Employee> empList = employeeLoader.readCSV("dev/employee_details.csv");
        empList.stream().forEach(System.out::println);

    }*/


}
