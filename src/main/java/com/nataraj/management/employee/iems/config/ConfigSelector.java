package com.nataraj.management.employee.iems.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;


public class ConfigSelector implements ImportSelector{

    public ConfigSelector(){
        System.out.println("Initializing CONFIG_SELECTOR");
    }

    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        String environment = System.getProperty("env");
        switch(environment){
            case "dev" :
                return new String[]{DbConfig.class.getName(), RedisConfig.class.getName(), WebConfig.class.getName()};
            case "uat" :
                return new String[]{DbConfig.class.getName(), RedisConfig.class.getName(),WebConfig.class.getName()};
            case "prod" :
                return new String[]{DbConfig.class.getName(), RedisConfig.class.getName(),WebConfig.class.getName()};
            default:
                throw new RuntimeException("Illegal Environment Selection");
        }
    }
}
