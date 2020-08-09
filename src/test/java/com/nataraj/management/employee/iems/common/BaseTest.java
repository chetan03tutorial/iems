package com.nataraj.management.employee.iems.common;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class BaseTest {
    static {
        System.setProperty("env","test");
    }
}
