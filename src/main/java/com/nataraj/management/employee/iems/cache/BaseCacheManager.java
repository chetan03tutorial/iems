package com.nataraj.management.employee.iems.cache;

import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Optional;

abstract public class BaseCacheManager<K,V> {
    private final K cache_key;

    public BaseCacheManager(K key){
        this.cache_key = key;
    }

    /*public BaseCacheManager of(@NotNull  K key){
        return Optional.of(key).filter(ObjectUtils::isEmpty).map((it)-> {
            return new EmployeeCacheManager(it);
        }).orElseGet(()-> {
            return new EmployeeCacheManager(EmployeeCacheManager.class.getName());
        });
    }*/


}
