package com.nataraj.management.employee.iems.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@PropertySource({
        "classpath:/${env}/redis.properties"
})
public class RedisConfig {

    @Autowired
    private Environment environment;


    @Autowired
    public RedisConfig(Environment environment) {
        System.out.println("Initializing REDIS CONFIG ");
        this.environment = environment;
    }

    @Bean
    public LettuceConnectionFactory redisConnectionFactory(){
        String host = environment.getProperty("spring.redis.host");
        Integer port = Integer.valueOf(environment.getProperty("spring.redis.port"));
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(host,port);
        //configuration.setPassword(RedisPassword.of(environment.getProperty("spring.redis.password")));
        return new LettuceConnectionFactory(configuration);
    }

    @Bean
    public RedisTemplate<String,Object> redisTemplate(){
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer()); //new GenericToStringSerializer<>(Object.class)
        template.setHashValueSerializer(new JdkSerializationRedisSerializer()); //new JdkSerializationRedisSerializer()
        template.setValueSerializer(new JdkSerializationRedisSerializer()); //new JdkSerializationRedisSerializer()
        template.setConnectionFactory(redisConnectionFactory());
        template.afterPropertiesSet();
        return template;
    }

}
