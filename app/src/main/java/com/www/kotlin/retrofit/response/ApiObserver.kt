package com.www.kotlin.retrofit.response

import android.util.Log
import com.base.kotlin.http.BaseObserver
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.warn

abstract class ApiObserver<R> : BaseObserver<Response<R>>() {
    override fun callback(response: Response<R>?) {
        if(response!!.isSuccess){
            onSuccess(response)
        }else{
            onFailure(response.errorCode,response.errorMsg)
        }
    }
     protected open fun onFailure(code: Int, msg: String?) {
        Log.w("ApiObserver","#request_ERROR: code->$code,msg->$msg")
    }
    abstract fun onSuccess(response: Response<R>?)

}