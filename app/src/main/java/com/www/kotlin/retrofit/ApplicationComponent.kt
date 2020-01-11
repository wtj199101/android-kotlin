package com.www.kotlin.retrofit

import com.github.leonardoxh.livedatacalladapter.LiveDataCallAdapterFactory
import com.github.leonardoxh.livedatacalladapter.LiveDataResponseBodyConverterFactory
import com.www.kotlin.ui.activity.LoginRegisterActivity
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.Proxy
import javax.inject.Singleton

@Component(modules = [ApiModule::class,RetrofitModule::class])
@Singleton
interface ApplicationComponent {

    fun inject(loginRegisterActivity: LoginRegisterActivity )
}

@Module
class ApiModule{
    @Provides
    @Singleton
    fun provideLoginRegisterService(retrofit:Retrofit): LoginRegisterRetrofitService {
        return retrofit.create(LoginRegisterRetrofitService::class.java)
    }
}
@Module
class RetrofitModule{
    /**
     */
    private val baseUrl="https://wanandroid.com/"

    @Provides
    @Singleton
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getHttpClinet())
            .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
            .addConverterFactory(LiveDataResponseBodyConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getHttpClinet(): OkHttpClient {
        var builder= OkHttpClient.Builder().addInterceptor(getDefaultInterceptor())
        addHttpConfig(builder)
        return builder.proxy(Proxy.NO_PROXY).build()
    }

    private fun addHttpConfig(builder: OkHttpClient.Builder) {

    }
    private fun getDefaultInterceptor(): Interceptor {
        return  HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
    }
}