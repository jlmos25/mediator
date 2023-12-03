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
class MediatorImpl(tasks: List<ITask<Request,Response>>): Mediator<Request, Response> {

    private val map :MutableMap<String, ITask<Request, Response>> = mutableMapOf()

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

    override suspend fun invoke(request: Request): Response {
        if (!map.containsKey(request.javaClass.typeName)){
            throw ITaskNotFound()
        }
        val iTask = map.getValue(request.javaClass.typeName)
        return iTask.invoke(request)
    }

}
