package com.nataraj.management.employee.iems.controllers;

import com.nataraj.management.employee.iems.config.ControllerTestConfig;
import com.nataraj.management.employee.iems.entities.Employee;
import com.nataraj.management.employee.iems.services.HrmsService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.*;



@WebMvcTest(value = HrmsRestController.class)
public class HrmsControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private HrmsService hrmsService;

    protected List<Employee> employees;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        Employee e1 = new Employee(Long.valueOf(1), "Mehta Tarak","Mumbai", "Senior Artist", "Comedy Show", Double.valueOf(1000));
        Employee e2 = new Employee(Long.valueOf(1), "Gupta Jitendra","Mumbai", "Senior Engineer", "Instrumentation", Double.valueOf(1000));
        employees = Arrays.asList(new Employee[]{e1,e2});
    }

    @Test
    public void testSucess_employeesByPlace() throws Exception{

        when(hrmsService.getEmployeeByPlace("Mumbai")).thenReturn(employees);
        ResultActions result = mockMvc.perform(get("/hrms/employees/Mumbai").contentType(MediaType.APPLICATION_JSON));


                result.andExpect(status().isOk()).
                andExpect(jsonPath("$", hasSize(2))).
                andExpect(jsonPath("$[0].name", is(employees.get(0).getName())));
    }

    @Test
    public void testSuccess_incrementSalary() throws Exception{
        when(hrmsService.provideIncrement(Double.valueOf(10),"Mumbai")).thenReturn(employees);
        mockMvc.perform(put("/hrms/employees/place/Mumbai/salary/10").
                contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect(jsonPath("$", hasSize(2))).
                andExpect(jsonPath("$[0].salary", is(Double.valueOf(1000))));
    }


}
