package com.nataraj.management.employee.iems.services;

import com.nataraj.management.employee.iems.TestConfig;
import com.nataraj.management.employee.iems.cache.EmployeeCacheManager;
import com.nataraj.management.employee.iems.dao.HrmsDao;
import com.nataraj.management.employee.iems.dao.HrmsDslQueryDao;
import com.nataraj.management.employee.iems.entities.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
public class HrmsServiceTest {


    @MockBean
    private EmployeeCacheManager employeeCacheManager;

    @MockBean
    private HrmsDao hrmsDao;

    @MockBean
    private HrmsDslQueryDao hrmsDslQueryDao;

    @Autowired
    private HrmsService hrmsService;

    private List<Employee> employees;

    static {
        System.setProperty("env","test");
    }


    @Before
    public void setup(){
        Employee e1 = new Employee(Long.valueOf(1), "Mehta Tarak","Mumbai", "Senior Artist", "Comedy Show", Double.valueOf(1000));
        Employee e2 = new Employee(Long.valueOf(1), "Gupta Jitendra","Mumbai", "Senior Engineer", "Instrumentation", Double.valueOf(1000));
        List<Employee> employees = Arrays.asList(new Employee[]{e1,e2});

        when(hrmsDao.findByLocation("Mumbai")).thenReturn(employees);
        when(hrmsDao.updateSalary(anyDouble(),anyLong())).thenReturn(1);

    }

    @Test
    public void testSuccess_whenSalaryIsIncremented(){
        Double incPercentage = Double.valueOf(10);
        String place = "Mumbai";
        List<Employee> employees = hrmsService.provideIncrement(incPercentage,place);
        employees.stream().forEach((Employee e) ->
                assertEquals(e.getSalary(),Double.valueOf(1100)));
    }

}
