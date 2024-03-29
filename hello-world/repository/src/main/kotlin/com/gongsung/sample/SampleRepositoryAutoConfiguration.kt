package com.gongsung.sample

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Import(
    SampleMemoryRepository::class,
)
@EntityScan
@EnableJpaRepositories
@AutoConfiguration
class SampleRepositoryAutoConfiguration
