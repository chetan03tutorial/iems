package com.nataraj.management.employee.iems.controllers;

import com.nataraj.management.employee.iems.config.ControllerTestConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ControllerTestConfig.class)
public class BaseControllerTest {

    static {
        System.setProperty("env","test");
    }

}
