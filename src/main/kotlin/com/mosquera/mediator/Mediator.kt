package com.mosquera.mediator

/**
 * Define Mediator operation.
 */
interface Mediator<Request,Response> {

    /**
     * Receive an [Request], then processing and return [Response]
     */
    fun invoke(request: Request):Response
}