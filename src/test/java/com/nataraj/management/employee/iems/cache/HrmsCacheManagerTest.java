package com.nataraj.management.employee.iems.cache;

import org.junit.Test;
import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;


public class HrmsCacheManagerTest extends BaseCacheTest {

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
