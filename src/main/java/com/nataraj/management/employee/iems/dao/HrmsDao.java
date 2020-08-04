package com.nataraj.management.employee.iems.dao;

import com.nataraj.management.employee.iems.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.stream.Stream;



public interface HrmsDao extends JpaRepository<Employee,Integer> {

    //public Stream<Employee> listByLocation(String place);

    //public void updateSalary(Double _increment, String place);

    @Query("select e from Employee e where e.place = :place")
    public List<Employee> findByLocation(@Param("place") String location);

    @Modifying
    @Query("update Employee e set e.salary = :salary where e.id = :id ")
    public int updateSalary(@Param("salary") Double salary, @Param("id") Long id);

}
