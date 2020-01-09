package com.www.kotlin.retrofit.service

import androidx.lifecycle.LiveData
import com.github.leonardoxh.livedatacalladapter.Resource
import com.www.kotlin.dao.entity.LoginResultEntity
import com.www.kotlin.retrofit.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginRegisterRetrofitService {
    /*=======登陆注册======*/
    @POST("user/login")
    @FormUrlEncoded
    abstract fun login(@Field("username") username: String, @Field("password") password: String): LiveData<Resource<Response<LoginResultEntity>>>

    @POST("user/register")
    @FormUrlEncoded
    abstract fun register(
        @Field("username") username: String, @Field("password") password: String, @Field(
            "repassword"
        ) repassword: String
    ): LiveData<Resource<Response<LoginResultEntity>>>

}