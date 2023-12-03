package com.mosquera.mediator.exception

/**
 * Exception that throws when mediator not found [ITask] for received [Request].
 */
class ITaskNotFound(message:String="Task not found on mediator"):Exception(message)