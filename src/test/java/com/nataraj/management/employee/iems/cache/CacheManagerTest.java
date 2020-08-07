package com.nataraj.management.employee.iems.cache;

import com.nataraj.management.employee.iems.config.RootConfig;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes =  RootConfig.class)
public class CacheManagerTest {

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void testValuePut(){
        String key = "foo";
        String bar = "world";
        cacheManager.valuePut(key,bar);
        String actualValue = (String)cacheManager.valueGet(key);
        assertEquals(bar,actualValue);

    }
}
