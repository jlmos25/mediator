package com.mosquera.mediator.utils

import com.mosquera.mediator.ITask

/**
 * Represents a successful task.
 */
class ItaskTest: ITask<TestRequest, TestResponse> {
    override fun invoke(request: TestRequest): TestResponse {
        return TestResponse("test Response")
    }
}