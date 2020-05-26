package com.app.station.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

@Configuration
public class RedisConfig {
    @Value("${redis.maxIdle}")
    int maxIdle;
    @Value("${redis.maxWait}")
    int maxWait;
    @Value("${redis.testOnBorrow}")
    boolean testOnBorrow;
    @Value("${redis.hostname}")
    String hostName;
    @Value("${redis.port}")
    int port;
    @Value("${redis.password}")
    String password;
    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory factory) {
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(factory);
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration
                .defaultCacheConfig()
                .entryTtl(Duration.ofHours(1));
        return RedisCacheManager
                .builder(redisCacheWriter)
                .cacheDefaults(redisCacheConfiguration)
                .build();
    }

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWait);
        config.setTestOnBorrow(testOnBorrow);
        return config;
    }
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory(jedisPoolConfig());
        factory.setHostName(hostName);
        factory.setPort(port);
        if (password != null) {
            factory.setPassword(password);
        }
        return factory;
    }

    @Bean
    public RedisTemplate<Object,Object> objectObjectRedisTemplate() {
        RedisTemplate<Object,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        template.setDefaultSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        template.setEnableDefaultSerializer(true);
        return template;
    }
}
