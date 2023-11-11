package com.mosquera.mediator

import com.mosquera.mediator.exception.ITaskNotInitialized
import com.mosquera.mediator.exception.ITaskNotFound
import org.apache.commons.collections4.CollectionUtils
import java.lang.reflect.ParameterizedType
import java.util.*

/**
 * Define a implementation of Mediator.
 *
 * Receive a list of [ITask].
 */
class MediatorImpl<T>(tasks: List<ITask<Request,Response<T>>>): Mediator<Request, Response<T>> {

    private val map :MutableMap<String, ITask<Request, Response<T>>> = mutableMapOf()

    init {
        if (CollectionUtils.isEmpty(tasks)){
            throw ITaskNotInitialized()
        }
        for (task in tasks){
            val request = (task.javaClass.genericInterfaces[0] as ParameterizedType)
                .actualTypeArguments[0].typeName
            map[request] = task
        }
    }

    @Throws(ITaskExecutionException::class)
    override fun invoke(request: Request): Response<T> {
        //map.computeIfAbsent(request.javaClass.typeName, throw ITaskNotFound())
        if (!map.containsKey(request.javaClass.typeName)){
            throw ITaskNotFound()
        }
        val iTask = map.getValue(request.javaClass.typeName)
        return iTask.invoke(request)
    }

}