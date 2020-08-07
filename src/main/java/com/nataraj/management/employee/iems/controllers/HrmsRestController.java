package com.nataraj.management.employee.iems.controllers;


import com.nataraj.management.employee.iems.cache.CacheManager;
import com.nataraj.management.employee.iems.cache.EmployeeCacheManager;
import com.nataraj.management.employee.iems.entities.Employee;
import com.nataraj.management.employee.iems.mappers.EmployeeMapper;
import com.nataraj.management.employee.iems.model.request.Associate;
import com.nataraj.management.employee.iems.model.response.SalaryRangeResponse;
import com.nataraj.management.employee.iems.services.HrmsService;
import org.hibernate.annotations.GeneratorType;
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

    @Autowired
    private EmployeeCacheManager cacheManager;

    /*public final Predicate<String> place = "loc"::equalsIgnoreCase,
            supervisor = "su"::equalsIgnoreCase,
            bu = "bu"::equalsIgnoreCase;
    Predicate<String> searchKeyPredicate = bu.or(place).or(supervisor);*/


    @GetMapping("/{place}")
    public List<Employee> employees(@PathVariable("place") String place){
        return hrmsService.getEmployeeByPlace(place);
    }

    @PutMapping("/place/{place}/salary/{increment}")
    public List<Employee> employee(@PathVariable("place") String place,
                             @PathVariable("increment") Double increment ){
        List<Employee> employee = hrmsService.provideIncrement(increment,place);
        return employee;
    }

    @GetMapping("/salaries/{searchKey}/{searchValue}")
    public Double getMaxSalary(@PathVariable("searchKey") String searchKey,
                                       @PathVariable("searchValue") String searchValue){

        Double result = new Double(-1);
        Predicate<String> searchKeyPredicate = getSearchKeyPredicate();
        boolean isSearchKeyValid  = searchKeyPredicate.test(searchKey);
        if(isSearchKeyValid){
             result = hrmsService.findMaxSalary(searchKey,searchValue);
        }
        return result;
    }

    @GetMapping("/salaries/{title}")
    public SalaryRangeResponse getSalaryRange(@PathVariable("title") String title){
        return hrmsService.findSalaryRangeByTitle(title);
    }

    @GetMapping("/hierarchy/{supervisorId}")
    public Double getSupervisee(@PathVariable("supervisorId") String supervisorId){
        return null;
    }

    @PostMapping
    public void post(@RequestBody Associate associate){
        hrmsService.save(EmployeeMapper.mapEmployee(associate));
    }

    private boolean validateSearchKey(String searchKey){
        List<String> lists = Arrays.asList("su","loc","bu");
        return Stream.of(searchKey).anyMatch(lists::contains);
    }

    private Predicate<String> getSearchKeyPredicate() {
        Predicate<String> place = "loc"::equalsIgnoreCase,
                supervisor = "su"::equalsIgnoreCase,
                bu = "bu"::equalsIgnoreCase;
        return bu.or(place).or(supervisor);
    }
}
