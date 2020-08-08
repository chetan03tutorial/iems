package com.nataraj.management.employee.iems.config;

import com.nataraj.management.employee.iems.markers.IgnoreDuringScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
        basePackages = {"com.nataraj.management.employee.iems.cache"},
        excludeFilters = {@ComponentScan.Filter(IgnoreDuringScan.class)}
)
public class CacheTestConfig {
}
