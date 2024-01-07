package com.gongsung.sample

import com.gongsung.sample.entity.Sample
import org.springframework.data.jpa.repository.JpaRepository

interface SampleMySqlRepository : JpaRepository<Sample, Long>
