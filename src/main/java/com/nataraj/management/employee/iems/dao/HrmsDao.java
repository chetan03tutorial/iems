package com.nataraj.management.employee.iems.dao;

import com.nataraj.management.employee.iems.entities.Employee;
import com.nataraj.management.employee.iems.entities.QEmployee;
import com.nataraj.management.employee.iems.model.response.SalaryRangeResponse;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


@Repository
public interface HrmsDao extends JpaRepository<Employee,Integer>, QuerydslPredicateExecutor<Employee>{

    @Query("select e from Employee e where e.place = :place")
    public List<Employee> findByLocation(@Param("place") String location);

    @Modifying
    @Query("update Employee e set e.salary = :salary where e.id = :id ")
    public int updateSalary(@Param("salary") Double salary, @Param("id") Long id);

    @Query("select new com.nataraj.management.employee.iems.model.response.SalaryRangeResponse(MIN(e.salary), MAX(e.salary)) from Employee e where lower(e.title) = :title")
    public SalaryRangeResponse findSalaryRangeByTitle(@Param("title") String title);
}
