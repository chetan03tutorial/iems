package com.nataraj.management.employee.iems.services;

import com.nataraj.management.employee.iems.cache.EmployeeCacheManager;
import com.nataraj.management.employee.iems.dao.HrmsDao;
import com.nataraj.management.employee.iems.dao.HrmsDslQueryDao;
import com.nataraj.management.employee.iems.entities.Employee;
import com.nataraj.management.employee.iems.model.response.SalaryRangeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HrmsService {

    @Autowired
    private EmployeeCacheManager employeeCacheManager;
    @Autowired
    private HrmsDao hrmsDao;
    @Autowired
    private HrmsDslQueryDao hrmsDslQueryDao;

    /*@Autowired
    public HrmsService(HrmsDao hrmsDao, HrmsDslQueryDao hrmsDslQueryDao, EmployeeCacheManager employeeCacheManager){
        this.hrmsDao = hrmsDao;
        this.hrmsDslQueryDao = hrmsDslQueryDao;
        this.employeeCacheManager = employeeCacheManager;
    }*/

    @Transactional("hrmsTransactionManager")
    public void save(Employee employee){
        System.out.println("Employee details are " + employee);
        System.out.println("Employee DAO is " + hrmsDao);
        try{
            hrmsDao.save(employee);
            employeeCacheManager.cacheEmployee(employee);

        } catch (Exception ex){
            ex.printStackTrace();
        }

    }

    /*@Transactional("hrmsTransactionManager")
    public Employee get(int employeeId){
        return hrmsDao.findById(employeeId).orElseGet(Employee::new);
    }*/

    //TODO :- Think for bulk update to reduce the network call. Hibernate dirty checking
    // is a good way to begin and end the transaction in this case.
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
        // Persist each employee in employee:id => employee_string and place:place_name => employee:id
        employees.stream().forEach(employeeCacheManager::cacheEmployee);
        return employees;
    }

    @Transactional
    public Double findMaxSalary(String searchKey, String searchValue){
        if("bu".equalsIgnoreCase(searchKey)){
            return hrmsDslQueryDao.getMaxSalaryByBU(searchValue.toLowerCase());
        }else if("loc".equalsIgnoreCase(searchKey)){
            return hrmsDslQueryDao.getMaxSalaryByLocation(searchValue.toLowerCase());
        }
        return Double.valueOf(-1);
    }

    @Transactional
    public SalaryRangeResponse findSalaryRangeByTitle(String title){
        return hrmsDao.findSalaryRangeByTitle(title.toLowerCase());
    }

    @Transactional
    public List<Employee> getEmployeeByPlace(String place){
        // Check the cache for the employee by place, otherwise fetch it from the DB
        List<Employee> employees = null;
        employees = Optional.of(employeeCacheManager.getEmployeeByPlace(place)).orElseGet(ArrayList::new);

        if(employees.isEmpty()){
            System.out.println("Did not find the employees in cache");
            System.out.println("Fetching the employees from DB");
            System.out.println("Persisting the Employee in the Cache");
            employees = Optional.ofNullable(hrmsDao.findByLocation(place)).orElseGet(ArrayList::new);
            employees.stream().forEach(employeeCacheManager::cacheEmployee);
        }else{
            System.out.println("Found the employee in cache");
        }
        return employees;
    }


    /*@Transactional
    public void findMaxSalary(String searchKey, String searchValue){
        PredicateBuilder<Employee> predicateBuilder = PredicateBuilder.getPredicateBuilder(Employee.class);
        predicateBuilder.predicate(searchKey,searchValue,":");
        Predicate predicate = predicateBuilder.buildPredicate();
        //hrmsDao.count(EmployeeCondition.fromPlace(searchValue));
        Iterable<Employee> employee = hrmsDao.findAll(predicate);
        employee.forEach(System.out::println);
    }*/



}
