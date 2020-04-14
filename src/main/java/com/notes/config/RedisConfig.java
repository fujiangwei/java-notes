package com.notes.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

/**
 * descripiton: 读取redis.properties配置
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2018/6/26
 * @time: 23:03
 * @modifier:
 * @since:
 */
@Configuration
public class RedisConfig {

    private Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    /**
     * 读取application.yaml属性
     */
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.database}")
    private int database;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.timeout}")
    private int timeout;

    @Bean(name = "testJedisPoolConfig")
    @ConfigurationProperties(prefix = "spring.redis.jedis.pool")
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        return config;
    }

    @Bean
    @Autowired
    public JedisPool jedisPool(@Qualifier(value = "testJedisPoolConfig") JedisPoolConfig config){
        logger.info("JedisPool注入成功！主机地址{}:{}", host, port);
        return new JedisPool(config, host, port, timeout, password, database);
    }
}
