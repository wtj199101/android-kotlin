package com.base.kotlin.retrofit

import com.base.kotlin.http.interceptor.HeaderInterceptor
import com.github.leonardoxh.livedatacalladapter.LiveDataCallAdapterFactory
import com.github.leonardoxh.livedatacalladapter.LiveDataResponseBodyConverterFactory
import com.www.kotlin.retrofit.service.RetrofitService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.Proxy

class BaseService {

     private var retrofit:Retrofit?=null

     companion object{
         val instant: BaseService by lazy (mode = LazyThreadSafetyMode.SYNCHRONIZED){
             BaseService()
         }
     }
     init {
         val  baseUrl=""

         retrofit= Retrofit.Builder()
             .baseUrl(baseUrl)
             .client(getHttpClinet())
             .addCallAdapterFactory(getDefaultCallAdapterFactory())
             .addConverterFactory(LiveDataResponseBodyConverterFactory.create())
             .addConverterFactory(getDefaultConvertFactory())
             .build()
     }

    private var retrofitService: RetrofitService?=null

    /**
     * 获取service
     */
    fun getRetrofitService(): RetrofitService {
        return if(retrofitService==null)retrofit!!.create(RetrofitService::class.java) else retrofitService!!
    }


    /**
     * Gson 解析器
     */
    private fun getDefaultConvertFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    /**
     * LiveData 解析器
     */
    private fun getDefaultCallAdapterFactory(): CallAdapter.Factory {
        return   LiveDataCallAdapterFactory.create()
    }



    private fun getHttpClinet(): OkHttpClient {
     var builder=OkHttpClient.Builder().addInterceptor(getDefaultInterceptor())
        addHttpConfig(builder)
        return builder.proxy(Proxy.NO_PROXY).build()
    }


    protected fun addHttpConfig(builder: OkHttpClient.Builder) {

    }

    private fun getDefaultInterceptor(): Interceptor {
        return HeaderInterceptor()
    }
}