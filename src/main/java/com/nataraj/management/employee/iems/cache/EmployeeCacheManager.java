package com.nataraj.management.employee.iems.cache;

import com.nataraj.management.employee.iems.EmployeeConstants;
import com.nataraj.management.employee.iems.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeCacheManager {

    private CacheManager cacheManager;

    @Autowired
    public EmployeeCacheManager(CacheManager cacheManager){
        this.cacheManager = cacheManager;
    }

    public void set(Employee employee){
        cacheManager.hashPut(EmployeeConstants.EMPLOYEE_KEY.getValue(), employee.getId(),employee);
    }

    public void get(Long empId){
        cacheManager.hashGet(EmployeeConstants.EMPLOYEE_KEY.getValue(), empId);
    }

}
