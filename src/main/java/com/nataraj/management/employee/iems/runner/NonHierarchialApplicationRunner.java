package com.nataraj.management.employee.iems.runner;

import com.nataraj.management.employee.iems.config.ConfigSelector;
import com.nataraj.management.employee.iems.config.RootConfig;
import com.nataraj.management.employee.iems.config.WebConfig;
import com.nataraj.management.employee.iems.markers.IgnoreDuringScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


public class NonHierarchialApplicationRunner {

    public static void main(String args[]) {
        SpringApplication app = new SpringApplication(RootConfig.class);
        ConfigurableApplicationContext rootContext = app.run(args);
    }
}
