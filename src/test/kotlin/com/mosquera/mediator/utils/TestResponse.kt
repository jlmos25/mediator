package com.mosquera.mediator.utils

import com.mosquera.mediator.Response


/**
 * Represents a test response.
 */
class TestResponse(data: String,success:Boolean=true,errorMessages:String="") :
    Response<String>(data,success,errorMessages)