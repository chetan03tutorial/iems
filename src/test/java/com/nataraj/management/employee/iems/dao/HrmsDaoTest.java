package com.nataraj.management.employee.iems.dao;

import com.nataraj.management.employee.iems.entities.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase
public class HrmsDaoTest extends BaseDaoTest{
    @Autowired
    private TestEntityManager tEm;

    @Autowired
    private HrmsDao hrmsDao;

    @Test
    public void testSuccess_WhenFetchingEmployeeByPlace(){
        Employee e1 = new Employee(Long.valueOf(1), "Mehta Tarak","Mumbai", "Senior Artist", "Comedy Show", Double.valueOf(1000));
        Employee e2 = new Employee(Long.valueOf(2), "Gupta Jitendra","Mumbai", "Senior Engineer", "Instrumentation", Double.valueOf(1000));
        List<Employee> employees = Arrays.asList(new Employee[]{e1,e2});
        hrmsDao.save(e1);
    }
}
