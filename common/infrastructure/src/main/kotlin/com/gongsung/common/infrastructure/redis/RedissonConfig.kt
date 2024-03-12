package com.gongsung.common.infrastructure.redis

import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class RedissonConfig {

    @Value("\${spring.data.redis.host}")
    lateinit var redisHost: String

    @Value("\${spring.data.redis.port}")
    lateinit var redisPort: String

    @Bean
    open fun redissonClient(): RedissonClient {
        val config: Config = Config()

        config.useSingleServer().setAddress("redis://$redisHost:$redisPort")
        return Redisson.create(config)
    }
}
