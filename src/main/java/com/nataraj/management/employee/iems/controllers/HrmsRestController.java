package com.nataraj.management.employee.iems.controllers;


import com.nataraj.management.employee.iems.entities.Employee;
import com.nataraj.management.employee.iems.mappers.EmployeeMapper;
import com.nataraj.management.employee.iems.model.request.Associate;
import com.nataraj.management.employee.iems.services.HrmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

@RestController
@RequestMapping("/hrms/employees")
public class HrmsRestController {

    @Autowired
    private HrmsService hrmsService;

    @PutMapping("/place/{place}/salary/{increment}")
    public Employee employee(@PathVariable("place") String place,
                             @PathVariable("increment") Double increment ){
        System.out.println("Searching employees for " + place );
        hrmsService.provideIncrement(increment,place);
        return null;
    }

    @GetMapping("/salaries/{searchKey}/{searchValue}")
    public Double getMaxSalary(@PathVariable("searchKey") String searchKey,
                               @PathVariable("searchValue") String searchValue){
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
    public void post(@RequestBody Employee associate){
        System.out.println("Associate Details are  " + associate.toString());

        hrmsService.save(associate);
    }
}
