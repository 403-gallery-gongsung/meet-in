package com.gongsung.user

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories
@EntityScan(basePackages = ["com.gongsung.user.entity"])
class RepositoryConfiguration {
}
