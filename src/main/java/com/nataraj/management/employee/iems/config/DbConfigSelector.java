package com.nataraj.management.employee.iems.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@Configuration
@EnableJpaRepositories(
        basePackages = {"com.nataraj.management.employee.dao"},
        entityManagerFactoryRef = "hrmsEntityManager",
        transactionManagerRef = "hrmsTransactionManager"
)
public class DbConfigSelector implements ImportSelector{

    /*@Autowired
    private Environment environment;

    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        String environment = System.getProperty("env");
        switch(environment){
            case "local":
                return new String[]{};
        }
        property.equalsIgnoreCase("local")
        return new String[0];
    }*/

    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        String environment = System.getProperty("env");
        switch(environment){
            case "local":
                return new String[]{DbConfig.class.getName()};
        }
        return null;
    }
}
