package com.gongsung.sample

import com.gongsung.sample.dto.SampleCreateInternalRequest
import com.gongsung.sample.dto.SampleCreateInternalResponse
import com.gongsung.sample.mutation.SampleService
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(name = "sample.internal.api.client", url = "/api-internal/v1/hello-world")
interface SampleInternalApiClient : SampleService {
    @PostMapping("/samples")
    fun create(sampleCreateInternalRequest: SampleCreateInternalRequest): SampleCreateInternalResponse

    override fun create(sampleProps: SampleProps): SampleModel =
        create(sampleProps)
}
