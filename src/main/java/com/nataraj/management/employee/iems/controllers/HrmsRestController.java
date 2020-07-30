package com.nataraj.management.employee.iems.controllers;


import com.nataraj.management.employee.iems.entities.Employee;
import com.nataraj.management.employee.iems.services.HrmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

@RestController
@RequestMapping("/hrms")
public class HrmsRestController {

    @Autowired
    private HrmsService hrmsService;

    @GetMapping("/movies/{movieId}")
    public Employee employee(@PathVariable("movieId") Integer movieId){
        System.out.println("Movie Id is " + movieId);
        return null;
    }

    @GetMapping
    public List<String> employees(){
        return Arrays.asList("Movie-Name");
    }

    @PostMapping
    public void post(@RequestBody Employee employee){
        System.out.println("Movie Name is " + employee);
        hrmsService.save(employee);
    }
}
