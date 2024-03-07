package com.gongsung.user

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories
@EnableJpaAuditing
@EntityScan(basePackages = ["com.gongsung.user.entity"])
@Configuration
class RepositoryConfiguration
