package com.nataraj.management.employee.iems.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

/*@Component*/
public class ConfigSelector implements ImportSelector{

    /*@Autowired*/
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
            case "test" :
                return new String[]{DbConfig.class.getName(), RedisConfig.class.getName(),WebConfig.class.getName()};
            default:
                throw new RuntimeException("Illegal Environment Selection");
        }
    }
}
