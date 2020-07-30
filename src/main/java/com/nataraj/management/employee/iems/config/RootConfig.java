package com.nataraj.management.employee.iems.config;

import com.nataraj.management.employee.iems.markers.IgnoreDuringScan;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.lang.Nullable;

import java.io.IOException;

@Configuration
@ComponentScan(
        basePackages = {"com.nataraj.management.employee.iems.services"},
        excludeFilters = {@ComponentScan.Filter(IgnoreDuringScan.class)}
        )
@Import(DbConfig.class)
@EnableAutoConfiguration
public class RootConfig {


    /*@Bean
    public static PropertyPlaceholderConfigurer getPropertyPlaceholderConfigurer()
            throws IOException {
        String env = System.getProperty("env");
        String resource = "classpath:/%1$s/**.properties";
        String resourcePath = String.format(resource,env);
        //resourcePath = "classpath:/dev/**.properties";
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setLocations(new PathMatchingResourcePatternResolver().getResources(resourcePath));
        return ppc;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties(){
        PropertySourcesPlaceholderConfigurer pspc
                = new PropertySourcesPlaceholderConfigurer();

        String env = System.getProperty("env");
        String resource = "/%1$s/**.properties";
        String resourcePath = String.format(resource,env);
        Resource[] resources = new ClassPathResource[ ]
                { new ClassPathResource( resourcePath ) };
        pspc.setLocations( resources );
        pspc.setIgnoreUnresolvablePlaceholders( true );
        return pspc;
    }*/
}
