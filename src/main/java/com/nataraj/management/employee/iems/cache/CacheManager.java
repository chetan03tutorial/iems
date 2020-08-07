package com.nataraj.management.employee.iems.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

@Component
public class CacheManager<K,V> {

    private RedisTemplate<K,V> redisTemplate;
    private HashOperations<K,Object,V> hashOperations;
    private ListOperations<K,V> listOperations;
    private ValueOperations<K,V> valueOperations;
    private SetOperations<K,V> setOperations;

    @Autowired
    public CacheManager(RedisTemplate<K,V> redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
        this.listOperations = redisTemplate.opsForList();
        this.valueOperations = redisTemplate.opsForValue();
        this.setOperations = redisTemplate.opsForSet();
    }

    public void hashPut(K key, Object itemKey, V value){
        hashOperations.put(key,itemKey,value);
    }

    public void hashPutAll(K key, Map<Object,V> valueMap){
        hashOperations.putAll(key,valueMap);
    }

    public void hashPutIfAbsent(K key, Object itemKey, V value){
        hashOperations.putIfAbsent(key,itemKey,value);
    }

    public V hashGet(K key, Object itemKey){
        return hashOperations.get(key,itemKey);
    }

    public Map<Object,V> hashGetAll(K key){
        return hashOperations.entries(key);
    }

    public void valuePut(K key, V value){
        valueOperations.set(key, value);
    }

    public V valueGet(K key){
        return valueOperations.get(key);
    }

    public void listSet(K key, V value){
        listOperations.rightPush(key, value);
    }

    public List<V> listGet(K key){
        return listOperations.range(key,0,-1);
    }

    public Set<V> setMembers(K key){
        return setOperations.members(key);
    }

    public void setAdd(K key, V value){
        setOperations.add(key, value);
    }
}
