package com.gongsung.sample

import com.gongsung.sample.dto.SampleCreateInternalRequest
import com.gongsung.sample.lookup.SampleLookUpService
import com.gongsung.sample.mutation.SampleService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api-internal/v1/hello-world")
class SampleInternalApi(
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
        @RequestBody sampleCreateInternalRequest: SampleCreateInternalRequest,
    ): SampleModel {
        return sampleService.create(sampleCreateInternalRequest)
    }
}
