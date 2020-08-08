package com.nataraj.management.employee.iems.dao;

import com.nataraj.management.employee.iems.config.DBTestConfig;
import com.nataraj.management.employee.iems.config.DbConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DBTestConfig.class)
public class BaseDaoTest {
    static {
        System.setProperty("env","test");
    }
}
