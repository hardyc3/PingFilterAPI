package com.hardycherry.config;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.support.http.HttpClient;
import io.lettuce.core.support.http.HttpClientResources;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

    @Value("${redis.password:admin}")
    String redisPass;
    @Value("${redis.host:localhost}")
    String redisHost;
    @Value("${redis.port:6379}")
    Integer redisPort;

    @Bean
    public RedisCommands<String, String> redisClient() {
        return RedisClient.create(RedisURI.builder()
                        .withHost(redisHost)
                        .withPort(redisPort)
                        .withPassword(redisPass)
                        .build())
                .connect()
                .sync();
    }
}
