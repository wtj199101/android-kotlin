package com.www.kotlin.retrofit.response

import android.util.Log
import com.base.kotlin.http.BaseObserver

abstract class ApiObserver<R> : BaseObserver<Response<R>>() {

    override fun callback(response: Response<R>?) {
        //预处理
        preAction(response)
        if (response!!.isSuccess) {
            onSuccess(response)
        } else {
            onError(response.errorCode, response.errorMsg)
        }
    }

    protected open fun onError(code: Int, msg: String?) {
        Log.w("ApiObserver", "#request_ERROR: code->$code,msg->$msg")
    }

    /**
     * 预处理
     */
    protected open fun preAction(response: Response<R>?) {

    }

    abstract fun onSuccess(response: Response<R>?)

}