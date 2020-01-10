package com.www.kotlin.retrofit.response

import com.base.kotlin.http.BaseObserver
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.warn

abstract class ApiObserver<R> : BaseObserver<Response<R>>(),AnkoLogger {


    override fun callback(response: Response<R>?) {
        if(response!!.isSuccess){
            onSuccess(response)
        }else{
            onFailure(response.errorCode,response.errorMsg)
        }
    }
    private fun onFailure(code: Int, msg: String?) {
        warn("#request_ERROR: code->$code,msg->$msg")
    }


    abstract fun onSuccess(response: Response<R>?)

}