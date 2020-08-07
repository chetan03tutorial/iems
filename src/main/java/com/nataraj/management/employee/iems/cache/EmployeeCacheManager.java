package com.nataraj.management.employee.iems.cache;

import com.nataraj.management.employee.iems.constants.RedisKeyEmployee;
import com.nataraj.management.employee.iems.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Component
public class EmployeeCacheManager {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    public EmployeeCacheManager(CacheManager cacheManager){
        this.cacheManager = cacheManager;
    }

    public String employeeKey(Employee emp){
        return RedisKeyEmployee.EMPLOYEE.toString().concat(String.valueOf(emp.getId()));
    }

    public String placeKey(Employee emp){
        return RedisKeyEmployee.PLACE.toString().concat(String.valueOf(emp.getPlace()));
    }

    public void cacheEmployee(Employee employee){
        cacheManager.valuePut(employeeKey(employee),employee);
        /*System.out.println("Key is " + EmployeeConstants.EMPLOYEE_KEY.getValue() + "EMPLOYEE_"+employee.getId());*/
        cacheManager.setAdd(placeKey(employee),employeeKey(employee));
    }


    public List<Employee> getEmployeeByPlace(String place){
        Set<String> empIdKeySet = cacheManager.setMembers(place);
        return empIdKeySet.stream().map((s)->cacheManager.valueGet(s)).
                map(Employee.class::cast).collect(Collectors.toList());
    }

}
