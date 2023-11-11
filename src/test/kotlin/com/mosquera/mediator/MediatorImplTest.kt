package com.mosquera.mediator

import com.mosquera.mediator.exception.ITaskNotInitialized
import com.mosquera.mediator.exception.ITaskNotFound
import com.mosquera.mediator.utils.ITaskTestFailed
import com.mosquera.mediator.utils.ItaskTest
import com.mosquera.mediator.utils.TestRequest
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
    fun given_mediator_When_init_and_not_receive_task_Then_return_exception(){

        val mediator : Mediator<Request,Response<Any>> = MediatorImpl(emptyList())
        mediator.invoke(Mockito.mock(Request::class.java))
    }

    @DisplayName("Given mediator not found task from request then return exception")
    @Test(expected = ITaskNotFound::class)
    fun given_mediator_When_execute_invoke_and_not_found_ITask_Then_return_exception(){

        val element = ItaskTest()
        val tasks: List<ITask<Request,Response<String>>> = listOf(element) as List<ITask<Request, Response<String>>>

        val mediator: Mediator<Request,Response<String>> = MediatorImpl(tasks)
        mediator.invoke(Mockito.mock(Request::class.java))
    }

    @DisplayName("Given mediator execute task but throw error then return failure response")
    @Test
    fun given_mediator_When_execute_invoke_throws_exception_Then_return_failure_response(){

        val element = ITaskTestFailed()
        val tasks: List<ITask<Request,Response<String>>> = listOf(element) as List<ITask<Request, Response<String>>>

        val mediator: Mediator<Request,Response<String>> = MediatorImpl(tasks)
        val response: Response<String> = mediator.invoke(TestRequest())
        Assert.assertEquals("Itask error Execution",response.getErrorMessage())
        Assert.assertFalse(response.isSuccess())

    }

    @DisplayName("Given mediator execute successfully then return valid response")
    @Test
    fun given_mediator_When_execute_invoke_Then_return_task_response(){

        val element = ItaskTest()
        val tasks: List<ITask<Request,Response<String>>> = listOf(element) as List<ITask<Request, Response<String>>>

        val mediator: Mediator<Request,Response<String>> = MediatorImpl(tasks)
        val response: Response<String> = mediator.invoke(TestRequest())
        Assert.assertEquals("test Response",response.getData())
        Assert.assertTrue(response.isSuccess())

    }

}