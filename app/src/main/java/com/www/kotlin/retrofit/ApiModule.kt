package com.www.kotlin.retrofit

import com.www.kotlin.retrofit.service.LoginRegisterRetrofitService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule{

    @Provides
    @Singleton
    fun provideLoginRegisterService(): LoginRegisterRetrofitService{
        return Retrofit.Builder()
            .baseUrl("https://example.com")
            .build()
            .create(LoginRegisterRetrofitService::class.java)

    }
}