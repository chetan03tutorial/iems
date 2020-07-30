package com.nataraj.management.employee.iems.dao;

import com.nataraj.management.employee.iems.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HrmsDao extends JpaRepository<Employee,Integer> {
}
