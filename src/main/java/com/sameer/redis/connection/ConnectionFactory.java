package com.sameer.redis.connection;

import com.sameer.redis.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class ConnectionFactory {
  @Bean
  JedisConnectionFactory jedisConnectionFactory() {

    JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
    return jedisConnectionFactory;
  }

  @Bean
  public RedisTemplate<String, User> getRedisTemplate() {
    RedisTemplate redisTemplate = new RedisTemplate<String, User>();
    redisTemplate.setConnectionFactory(jedisConnectionFactory());
    return redisTemplate;
  }

}
