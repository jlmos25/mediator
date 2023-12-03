package com.mosquera.mediator

import com.mosquera.mediator.exception.ITaskNotFound
import com.mosquera.mediator.exception.ITaskNotInitialized
import com.mosquera.mediator.utils.ITaskTestFailed
import com.mosquera.mediator.utils.TaskTest
import com.mosquera.mediator.utils.TestRequest
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import org.mockito.Mockito

/**
 * Test of class [MediatorImpl].
 */
internal class MediatorImplTest{

    @DisplayName("Given mediator not receive task then return exception")
    @Test(expected = ITaskNotInitialized::class)
    fun given_mediator_When_init_and_not_receive_task_Then_return_exception() = runTest{

        val mediator : Mediator<Request,Response> = MediatorImpl(emptyList())
        mediator.invoke(Mockito.mock(Request::class.java))
    }

    @DisplayName("Given mediator not found task from request then return exception")
    @Test(expected = ITaskNotFound::class)
    fun given_mediator_When_execute_invoke_and_not_found_ITask_Then_return_exception() = runTest{

        val element = TaskTest()
        val tasks: List<ITask<Request,Response>> = listOf(element) as List<ITask<Request, Response>>

        val mediator: Mediator<Request,Response> = MediatorImpl(tasks)
        mediator.invoke(Mockito.mock(Request::class.java))
    }

    @DisplayName("Given mediator execute task but throw error then return failure response")
    @Test
    fun given_mediator_When_execute_invoke_throws_exception_Then_return_failure_response() = runTest{

        val element = ITaskTestFailed() as ITask<Request, Response>
        val tasks: List<ITask<Request,Response>> = listOf(element)

        val mediator: Mediator<Request,Response> = MediatorImpl(tasks)
        val response: Response = mediator.invoke(TestRequest())
        Assert.assertEquals("Itask error Execution",response.getErrorMessage())
        Assert.assertFalse(response.isSuccess())

    }

    @DisplayName("Given mediator execute successfully then return valid response")
    @Test
    fun given_mediator_When_execute_invoke_Then_return_task_response() = runTest{

        val element:ITask<Request,Response> = TaskTest() as ITask<Request,Response>
        val tasks: List<ITask<Request,Response>> = listOf(element)

        val mediator: Mediator<Request,Response> = MediatorImpl(tasks)
        val response: Response = mediator.invoke(TestRequest())
        Assert.assertEquals("test Response",response.getData())
        Assert.assertTrue(response.isSuccess())

    }

}