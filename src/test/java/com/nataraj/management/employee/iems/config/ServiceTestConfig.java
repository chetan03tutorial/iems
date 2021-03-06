package com.nataraj.management.employee.iems.config;

import com.nataraj.management.employee.iems.dao.HrmsDao;
import com.nataraj.management.employee.iems.markers.IgnoreDuringScan;
import com.nataraj.management.employee.iems.services.HrmsService;
import com.querydsl.core.annotations.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
        basePackages = {"com.nataraj.management.employee.iems.services"},
        excludeFilters = {@ComponentScan.Filter(IgnoreDuringScan.class)}
)
public class ServiceTestConfig {

    static {
        System.setProperty("env","test");
    }
    /*@Bean
    public HrmsService hrmsService(){
        return new HrmsService();
    }*/



}
