package com.gongsung.sample

import com.gongsung.sample.lookup.SampleLookUpServiceImpl
import com.gongsung.sample.mutation.SampleServiceImpl
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.context.annotation.Import

@Import(
    SampleServiceImpl::class,
    SampleLookUpServiceImpl::class,
)
@AutoConfiguration
class SampleServiceAutoConfiguration
