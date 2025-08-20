package com.quanti_learn.user_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
    public JedisConnectionFactory connectionFactory() { // LettuceConnectionFactory can be used for async and reactive programming
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(); // creating a standalone config
        // redis standalone is for single instance of redis
        // we can also use sentinel, for adding multiple instance of redis, if the master fails, another instance is taken over
        // cluster, sharding across multiple nodes, used for horizontal scaling.
        configuration.setHostName("localhost");
        configuration.setPort(6379);
        return new JedisConnectionFactory(configuration);
    }

    @Bean
    public RedisTemplate<String, Object> template() {
        RedisTemplate<String, Object> template = new RedisTemplate<>(); // create a template that has String as key and Object as value
        template.setConnectionFactory(connectionFactory()); // setting the connection config
        template.setKeySerializer(new StringRedisSerializer()); // level 1 key user:{}
        template.setHashKeySerializer(new JdkSerializationRedisSerializer()); // how to serialize key within hash map, level 2 key, user:{name:""}
        template.setValueSerializer(new JdkSerializationRedisSerializer()); // how to serialize value within hash map
        template.setEnableTransactionSupport(true); // enable to run multiple commands at sametime
        template.afterPropertiesSet(); // ensure the template is fully initialised after setting the properties
        return template; // return the template
    }
}
