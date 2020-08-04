package com.nataraj.management.employee.iems.controllers;


import com.nataraj.management.employee.iems.entities.Employee;
import com.nataraj.management.employee.iems.mappers.EmployeeMapper;
import com.nataraj.management.employee.iems.model.request.Associate;
import com.nataraj.management.employee.iems.services.HrmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

@RestController
@RequestMapping("/hrms/employees")
public class HrmsRestController {

    @Autowired
    private HrmsService hrmsService;

    Predicate<String> place = "loc"::equalsIgnoreCase,
            supervisor = "su"::equalsIgnoreCase,
            bu = "bu"::equalsIgnoreCase;
    Predicate<String> searchKeyPredicate = bu.or(place).or(supervisor);

    @PutMapping("/place/{place}/salary/{increment}")
    public List<Employee> employee(@PathVariable("place") String place,
                             @PathVariable("increment") Double increment ){
        System.out.println("Searching employees for " + place );
        List<Employee> employee = hrmsService.provideIncrement(increment,place);
        return employee;
    }

    @GetMapping("/salaries/{searchKey}/{searchValue}")
    public Double getMaxSalary(@PathVariable("searchKey") String searchKey,
                               @PathVariable("searchValue") String searchValue){

        Predicate<String> searchKeyPredicate = getSearchKeyPredicate();
        boolean isSearchKeyValid  = searchKeyPredicate.test(searchKey);
        if(isSearchKeyValid){
            hrmsService.findMaxSalary(searchKey,searchValue);
        }
        return null;
    }

    @GetMapping("/salaries/{title}")
    public Double getSalaryRange(@PathVariable("title") String title){
        return null;
    }

    @GetMapping("/hierarchy/{supervisorId}")
    public Double getSupervisee(@PathVariable("supervisorId") String supervisorId){
        return null;
    }

    @GetMapping
    public List<String> employees(){
        return null;
    }

    @PostMapping
    public void post(@RequestBody Associate associate){
        System.out.println("Associate Details are  " + associate.toString());

        System.out.println("HRMS Service Id is " + hrmsService);
        hrmsService.save(EmployeeMapper.mapEmployee(associate));
    }

    public boolean validateSearchKey(String searchKey){
        List<String> lists = Arrays.asList("su","loc","bu");
        return Stream.of(searchKey).anyMatch(lists::contains);
    }
    public Predicate<String> getSearchKeyPredicate() {
        Predicate<String> place = "loc"::equalsIgnoreCase,
                supervisor = "su"::equalsIgnoreCase,
                bu = "bu"::equalsIgnoreCase;
        return bu.or(place).or(supervisor);
    }
}
