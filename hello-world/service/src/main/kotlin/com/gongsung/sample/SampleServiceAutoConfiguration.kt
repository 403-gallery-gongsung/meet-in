package com.gongsung.sample

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.context.annotation.Import

@Import(
    SampleServiceImpl::class,
    SampleLookUpServiceImpl::class,
)
@AutoConfiguration
class SampleServiceAutoConfiguration
