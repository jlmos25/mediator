package com.mosquera.mediator.exception

/**
 * Exception that throws when mediator not receive any [ITask].
 */
class ITaskNotInitialized(message: String = "Mediator not receive any task when init"): Exception(message)
