package com.demo.application.config;

import java.lang.reflect.Method;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableCaching
@Configuration
//redis缓存
@Profile("RedisCacheConfig")
public class RedisCacheConfig  {
//    @Bean 
//    public KeyGenerator keyGenerator(){  
//        return new KeyGenerator() {  
//            @Override
//            public Object generate(Object target, Method method, Object... params) {  
//                StringBuilder sb = new StringBuilder();  
//                sb.append(target.getClass().getName());  
//                sb.append(method.getName());  
//                for (Object obj : params) {  
//                    sb.append(obj.toString());  
//                }  
//                return sb.toString();  
//            }  
//        };  
//  
//    }  
    
    @Bean  
    public CacheManager cacheManager(  
            @SuppressWarnings("rawtypes") RedisTemplate redisTemplate) {  
        return new RedisCacheManager(redisTemplate);  
    }  
  
    @Bean  
    public RedisTemplate<String, String> redisTemplate(  
            RedisConnectionFactory factory) {  
        StringRedisTemplate template = new StringRedisTemplate(factory);  
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);  
        ObjectMapper om = new ObjectMapper();  
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);  
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);  
        jackson2JsonRedisSerializer.setObjectMapper(om);  
        template.setValueSerializer(jackson2JsonRedisSerializer);  
      //template.expire(key, timeout, unit) test
        template.afterPropertiesSet(); 
        return template;  
    }

       
}
