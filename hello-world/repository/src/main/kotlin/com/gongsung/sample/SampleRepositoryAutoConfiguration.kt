package com.gongsung.sample

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Import
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Import(
    SampleRepositoryImpl::class,
)
@EntityScan
@EnableJpaRepositories
@AutoConfiguration
class SampleRepositoryAutoConfiguration
