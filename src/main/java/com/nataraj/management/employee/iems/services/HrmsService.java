package com.nataraj.management.employee.iems.services;

import com.nataraj.management.employee.iems.dao.HrmsDao;
import com.nataraj.management.employee.iems.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HrmsService {

    @Autowired
    private HrmsDao hrmsDao;

    @Transactional("hrmsTransactionManager")
    public void save(Employee employee){
        System.out.println("Employee details are " + employee);
        System.out.println("Employee DAO is " + hrmsDao);
        try{
            hrmsDao.save(employee);
        } catch (Exception ex){
            ex.printStackTrace();
        }

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

    @Transactional("hrmsTransactionManager")
    public List<Employee> provideIncrement(Double increment,String place){
        List<Employee> employees = hrmsDao.findByLocation(place);
        /**
         * Saving by using DAO method as it would automatically call em.persist()
         * or em.save() behind the scene. However, custom query is useful when
         * we want to update only a few fields
         * employees.stream().map((e) -> e.updateSalary(increment)).forEach(hrmsDao::save);
         */
        employees.stream().map((e)-> e.updateSalary(increment)).forEach(
                (e) -> hrmsDao.updateSalary(e.getSalary(),e.getId())
        );
        return employees;

    }

    @Transactional
    public void findMaxSalary(String searchKey, String searchValue){


    }


}
