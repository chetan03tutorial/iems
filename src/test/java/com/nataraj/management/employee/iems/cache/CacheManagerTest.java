package com.nataraj.management.employee.iems.cache;

import com.nataraj.management.employee.iems.services.BaseServiceTest;
import org.junit.Test;
import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;


public class CacheManagerTest extends BaseServiceTest {

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
