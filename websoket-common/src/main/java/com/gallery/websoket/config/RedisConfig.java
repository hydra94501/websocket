package com.gallery.websoket.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

/*
* RedisConfig
*
* @date 2021/6/8 17:25
*/
@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private Integer port;

    @Value("${spring.redis.timeout}")
    private Integer timeout;

    @Value("${spring.redis.database}")
    private Integer defaultDatabase;

    @Value("${spring.redis.password}")
    private String password;

    /** 当前系统的redis缓存操作对象 (主对象) **/
    @Primary
    @Bean(name = "defaultStringRedisTemplate")
    public StringRedisTemplate sysStringRedisTemplate() {
        StringRedisTemplate template = new StringRedisTemplate();
        LettuceConnectionFactory jedisConnectionFactory = new LettuceConnectionFactory();
        jedisConnectionFactory.setHostName(host);
        jedisConnectionFactory.setPort(port);
        jedisConnectionFactory.setTimeout(timeout);
        if (!StringUtils.isEmpty(password)) {
            jedisConnectionFactory.setPassword(password);
        }
        if (defaultDatabase != 0) {
            jedisConnectionFactory.setDatabase(defaultDatabase);
        }
        jedisConnectionFactory.afterPropertiesSet();
        template.setConnectionFactory(jedisConnectionFactory);
        return template;
    }

}
