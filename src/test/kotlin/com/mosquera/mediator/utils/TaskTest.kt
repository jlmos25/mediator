package com.mosquera.mediator.utils

import com.mosquera.mediator.ITask

/**
 * Represents a successful task.
 */
class TaskTest: ITask<TestRequest, TestResponse> {
    override suspend fun invoke(request: TestRequest): TestResponse {
        return TestResponse("test Response")
    }
}