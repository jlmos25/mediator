package com.mosquera.mediator



/**
 * Represent a Response to return result from [ITask] processing.
 */
abstract class Response<T>(private val data:T, private val success:Boolean, private val errorMessage:String) {

    /**
     * Indicates if Itask execute correct or failed.
     */
    fun isSuccess():Boolean = success

    /**
     * Returns error message if Itask not execute successfully.
     */
    fun getErrorMessage():String = errorMessage

    /**
     * Return data.
     *
     * @return [T].
     */
    fun getData():T{
        return data
    }
}