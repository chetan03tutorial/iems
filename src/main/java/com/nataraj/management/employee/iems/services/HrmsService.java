package com.nataraj.management.employee.iems.services;

import com.nataraj.management.employee.iems.dao.HrmsDao;
import com.nataraj.management.employee.iems.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class HrmsService {

    @Autowired
    private HrmsDao hrmsDao;

    @Transactional("hrmsTransactionManager")
    public void save(Employee employee){
        hrmsDao.save(employee);
    }

    @Transactional("hrmsTransactionManager")
    public Employee get(int employeeId){
        Optional<Employee> movie = hrmsDao.findById(employeeId);
        if(movie.isPresent()){
            return movie.get();
        }
        throw new RuntimeException("Employee Not Found");
    }

    @Transactional("hrmsTransactionManager")
    public Employee update(Employee employee){
        Optional<Employee> movie = hrmsDao.findById(1);
        if(movie.isPresent()){
            return movie.get();
        }
        throw new RuntimeException("Employee Not Found");
    }

    public void provideIncrement(Double increment,String place){
        //hrmsDao.listByLocation(place);
        //hrmsDao.updateSalary(increment,place);
        //hrmsDao.findByLocation();
    }



}
