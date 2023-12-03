# Mediator

This library is based on design pattern mediator. 
The purpose of the library is to reduce dependencies when performing actions

## Scope

The scope of library is for use in Android, with kotlin language, to execute background actions, for example,
request to a remote server. This first version works with kotlin coroutines.

## How To Use

### First initialize mediator

We pass all actions to mediator for save in memory

```{kotlin}
class TaskTest: ITask<TestRequest, TestResponse> {
    override suspend fun invoke(request: TestRequest): TestResponse {
        return TestResponse("test Response")
    }
}
```

```{kotlin}
    MediatorImpl(listOf(TaskTest()))
```

When init mediator with any task, then can invoke task using task Request, and when execute, returns a 
Response with result of task execution.

```{kotlin}
class TestRequest: Request {
}

val testResponse:TestResponse = mediator.invoke(TestRequest())
```

