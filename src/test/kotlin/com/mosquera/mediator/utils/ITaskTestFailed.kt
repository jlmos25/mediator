package com.mosquera.mediator.utils

import com.mosquera.mediator.ITask

/**
 * Represents a failed task.
 */
class ITaskTestFailed : ITask<TestRequest, TestResponse> {
    override suspend fun invoke(request: TestRequest): TestResponse {
        return TestResponse("",false,"Itask error Execution")
    }
}