package com.www.kotlin.retrofit

import com.github.leonardoxh.livedatacalladapter.LiveDataCallAdapterFactory
import com.github.leonardoxh.livedatacalladapter.LiveDataResponseBodyConverterFactory
import com.www.kotlin.ui.activity.LoginRegisterActivity
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Component(modules = [ApiModule::class])
@Singleton
interface ApplicationComponent {

    fun inject(loginRegisterActivity: LoginRegisterActivity )
}

@Module
class ApiModule{

    @Provides
    @Singleton
    fun provideLoginRegisterService(): LoginRegisterRetrofitService {
        return Retrofit.Builder()
            .baseUrl("http://wanandroid.com/")
            .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
            .addConverterFactory(LiveDataResponseBodyConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginRegisterRetrofitService::class.java)

    }
}