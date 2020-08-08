package com.nataraj.management.employee.iems.services;

import com.nataraj.management.employee.iems.cache.EmployeeCacheManager;
import com.nataraj.management.employee.iems.dao.HrmsDao;
import com.nataraj.management.employee.iems.dao.HrmsDslQueryDao;
import com.nataraj.management.employee.iems.entities.Employee;
import com.nataraj.management.employee.iems.model.response.SalaryRangeResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

public class HrmsServiceTest extends BaseServiceTest {


    @MockBean
    private EmployeeCacheManager employeeCacheManager;

    @MockBean
    private HrmsDao hrmsDao;

    @MockBean
    private HrmsDslQueryDao hrmsDslQueryDao;

    @Autowired
    private HrmsService hrmsService;


    @Before
    public void setup(){
        Employee e1 = new Employee(Long.valueOf(1), "Mehta Tarak","Mumbai", "Senior Artist", "Comedy Show", Double.valueOf(1000));
        Employee e2 = new Employee(Long.valueOf(1), "Gupta Jitendra","Mumbai", "Senior Engineer", "Instrumentation", Double.valueOf(1000));
        List<Employee> employees = Arrays.asList(new Employee[]{e1,e2});

        when(hrmsDao.findByLocation("Mumbai")).thenReturn(employees);
        when(hrmsDao.updateSalary(anyDouble(),anyLong())).thenReturn(1);
        when(hrmsDslQueryDao.getMaxSalaryByBU(anyString())).
                thenReturn(Double.valueOf(Double.valueOf(1000)));
        when(hrmsDslQueryDao.getMaxSalaryByLocation(anyString())).
                thenReturn(Double.valueOf(Double.valueOf(1000)));


    }

    @Test
    public void testSuccess_whenSalaryIsIncremented(){
        Double incPercentage = Double.valueOf(10);
        String place = "Mumbai";
        List<Employee> employees = hrmsService.provideIncrement(incPercentage,place);
        employees.stream().forEach((Employee e) ->
                assertEquals(e.getSalary(),Double.valueOf(1100)));
    }

    @Test
    public void testSuccess_WhenMaxSalaryIsFound(){
        Double expectedSalary = Double.valueOf(1000);
        assertEquals(expectedSalary,hrmsService.findMaxSalary("bu","Senior Artist").getMax());
        assertEquals(expectedSalary,hrmsService.findMaxSalary("loc","Senior Artist").getMax());
    }

    @Test
    public void testSuccess_whenSalaryRangeIsSearched(){
        SalaryRangeResponse expected = new SalaryRangeResponse(Double.valueOf(100), Double.valueOf(1000));
        when(hrmsDao.findSalaryRangeByTitle(anyString())).thenReturn(expected);
        SalaryRangeResponse actual = hrmsService.findSalaryRangeByTitle("Senior Artist");
        assertEquals(expected.getMin(),Double.valueOf(100));
        assertEquals(expected.getMax(),Double.valueOf(1000));
    }

}
