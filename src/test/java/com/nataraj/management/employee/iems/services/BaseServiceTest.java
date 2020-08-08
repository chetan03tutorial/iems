package com.nataraj.management.employee.iems.services;

import com.nataraj.management.employee.iems.config.ServiceTestConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceTestConfig.class)
public class BaseServiceTest {

    static {
        System.setProperty("env","test");
    }

}
