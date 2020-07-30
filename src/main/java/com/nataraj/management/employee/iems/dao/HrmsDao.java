package com.nataraj.management.employee.iems.dao;

import com.nataraj.management.employee.iems.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.stream.Stream;



public interface HrmsDao extends JpaRepository<Employee,Integer> {

    //public Stream<Employee> listByLocation(String place);

    //public void updateSalary(Double _increment, String place);

    //public void findByLocation();
}
