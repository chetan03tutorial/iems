package com.nataraj.management.employee.iems.config;

import com.nataraj.management.employee.iems.markers.IgnoreDuringScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
        basePackages = {"com.nataraj.management.employee.iems.dao"},
        excludeFilters = {@ComponentScan.Filter(IgnoreDuringScan.class)}
)
public class DBTestConfig {
    public DBTestConfig(){
        System.out.println("Coming here");
    }
}
