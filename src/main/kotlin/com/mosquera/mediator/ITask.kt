package com.mosquera.mediator

/**
 * Represents a [ITask] definition operation.
 */
interface ITask<Request,Response>{

    /**
     * Process a request and returns response
     *
     * Receive [request] to process.
     *
     * @return [Response] response of action of process request.
     */
    operator fun invoke(request: Request):Response
}