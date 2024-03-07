package com.gonsung.sample

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.context.annotation.Import

@Import(
    SampleApi::class,
)
@AutoConfiguration
class SampleApiAutoConfiguration
