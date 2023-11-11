package com.mosquera.mediator



/**
 * Represent a Response to return result from [ITask] processing.
 */
abstract class Response(private val data:Any, private val success:Boolean, private val errorMessage:String) {

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
     * @return [Any].
     */
    fun getData():Any{
        return data
    }
}