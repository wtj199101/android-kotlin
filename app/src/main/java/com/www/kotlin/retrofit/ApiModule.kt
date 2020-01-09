package com.www.kotlin.retrofit

import com.www.kotlin.retrofit.service.LoginRegisterRetrofitService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApiModule{

    @Provides
    fun provideLoginRegisterService(): LoginRegisterRetrofitService{
        return Retrofit.Builder()
            .baseUrl("https://example.com")
            .build()
            .create(LoginRegisterRetrofitService::class.java)

    }
}