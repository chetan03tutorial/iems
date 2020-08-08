package com.nataraj.management.employee.iems.controllers;

import com.nataraj.management.employee.iems.entities.Employee;
import com.nataraj.management.employee.iems.exception.BadRequestException;
import static com.nataraj.management.employee.iems.exception.ExceptionConstant.*;
import com.nataraj.management.employee.iems.mappers.EmployeeMapper;
import com.nataraj.management.employee.iems.model.request.Associate;
import com.nataraj.management.employee.iems.model.response.SalaryRangeResponse;
import com.nataraj.management.employee.iems.services.HrmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.function.Predicate;

@RestController
@RequestMapping("/hrms/employees")
public class HrmsRestController {

    @Autowired
    private HrmsService hrmsService;

    /*@Autowired
    private EmployeeCacheManager cacheManager;*/

    /*public final Predicate<String> place = "loc"::equalsIgnoreCase,
            supervisor = "su"::equalsIgnoreCase,
            bu = "bu"::equalsIgnoreCase;
    Predicate<String> searchKeyPredicate = bu.or(place).or(supervisor);*/


    @GetMapping("/{place}")
    public ResponseEntity<List<Employee>> employees(@PathVariable("place") String place){
        List<Employee> employees = hrmsService.getEmployeeByPlace(place);
        return ResponseEntity.ok(employees);
    }


    @GetMapping("/salaries/{searchKey}/{searchValue}")
    public ResponseEntity<SalaryRangeResponse> getMaxSalary(@PathVariable("searchKey") String searchKey,
                                                            @PathVariable("searchValue") String searchValue){

        SalaryRangeResponse result;
        Predicate<String> searchKeyPredicate = getSearchKeyPredicate();
        boolean isSearchKeyValid  = searchKeyPredicate.test(searchKey);
        if(isSearchKeyValid){
            result = hrmsService.findMaxSalary(searchKey,searchValue);
            return ResponseEntity.ok(result);
        }
        throw new BadRequestException(BAD_REQUEST_EXCEPTION.code, BAD_REQUEST_EXCEPTION.message);
    }



    @GetMapping("/salaries/range/{title}")
    public ResponseEntity<SalaryRangeResponse> getSalaryRange(@PathVariable("title") String title){
        return ResponseEntity.ok(hrmsService.findSalaryRangeByTitle(title));
    }

    @GetMapping("/hierarchy/{supervisorId}")
    public Long getSupervisor(@PathVariable("supervisorId") String supervisorId){
        return null;
    }

    @PutMapping("/place/{place}/salary/{increment}")
    public ResponseEntity<List<Employee>> incrementSalary(@PathVariable("place") String place,
                                                          @PathVariable("increment") Double increment ){
        List<Employee> employees = hrmsService.provideIncrement(increment,place);
        return ResponseEntity.ok(employees);
    }

    @PostMapping
    public void post(@RequestBody Associate associate){
        hrmsService.save(EmployeeMapper.mapEmployee(associate));
    }

    /*private boolean validateSearchKey(String searchKey){
        List<String> lists = Arrays.asList("su","loc","bu");
        return Stream.of(searchKey).anyMatch(lists::contains);
    }*/

    private Predicate<String> getSearchKeyPredicate() {
        Predicate<String> place = "loc"::equalsIgnoreCase,
                supervisor = "su"::equalsIgnoreCase,
                bu = "bu"::equalsIgnoreCase;
        return bu.or(place).or(supervisor);
    }
}
