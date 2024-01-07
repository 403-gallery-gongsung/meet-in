package com.gonsung.sample

import com.gongsung.sample.SampleIdentity
import com.gongsung.sample.SampleLookUpService
import com.gongsung.sample.SampleModel
import com.gongsung.sample.SampleService
import com.gonsung.sample.dto.SampleCreateRequest
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/hello-world")
class SampleApi(
    private val sampleService: SampleService,
    private val sampleLookUpService: SampleLookUpService,
) {
    private val log = KotlinLogging.logger { }

    @GetMapping("/samples/{sampleId}")
    fun get(
        @PathVariable("sampleId") sampleId: Long,
    ): SampleModel {
        return sampleId
            .let(SampleIdentity.Companion::of)
            .run(sampleLookUpService::get)
    }

    @PostMapping("/samples")
    fun create(
        @RequestBody sampleCreateRequest: SampleCreateRequest,
    ): SampleModel {
        return sampleService.create(sampleCreateRequest)
    }
}
