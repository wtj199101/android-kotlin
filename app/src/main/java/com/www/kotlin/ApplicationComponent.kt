package com.www.kotlin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.example.github.di.ViewModelKey
import com.github.leonardoxh.livedatacalladapter.LiveDataCallAdapterFactory
import com.github.leonardoxh.livedatacalladapter.LiveDataResponseBodyConverterFactory
import com.www.kotlin.retrofit.service.LoginRegisterRetrofitService
import com.www.kotlin.retrofit.service.MainRetrofitService
import com.www.kotlin.ui.activity.LoginRegisterActivity
import com.www.kotlin.ui.activity.MainActivity
import com.www.kotlin.ui.fragments.HomeFragment
import com.www.kotlin.ui.fragments.KnowledgeFragment
import com.www.kotlin.ui.fragments.NavigationFragment
import com.www.kotlin.ui.fragments.ProjectFragment
import com.www.kotlin.ui.viewmodel.AppViewModeFactory
import com.www.kotlin.ui.viewmodel.HomeViewModel
import com.www.kotlin.ui.viewmodel.LoginRegisterViewModel
import dagger.*
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.Proxy
import javax.inject.Singleton


/**
 * 全局 component
 * AndroidInjectionModule dagger2-android module
 * ActivityModule 管理 activity module
 * ApiModule 对接口和数据的module
 * ViewModeModule 全局 viewMode 的module
 */
@Component(modules = [AndroidInjectionModule::class,ApiModule::class, MainFragmentModule::class,ViewModeModule::class,ActivityModule::class ])
@Singleton
interface ApplicationComponent {
    fun inject(application: App)
}

/**
 * activit 和fragment 依赖
 */
@Module
interface ActivityModule {
    @ContributesAndroidInjector
    abstract fun MainActivityInjector(): MainActivity

    @ContributesAndroidInjector
    abstract fun LoginRegisterActivityInjector(): LoginRegisterActivity
}

@Module
interface MainFragmentModule {
    @ContributesAndroidInjector
    abstract fun HomeFragmentInjector(): HomeFragment

    @ContributesAndroidInjector
    abstract fun KnowledgeFragmentInjector(): KnowledgeFragment

    @ContributesAndroidInjector
    abstract fun NavigationFragmentInjector(): NavigationFragment

    @ContributesAndroidInjector
    abstract fun ProjectFragmentInjector(): ProjectFragment
}

/**
 * viewMode 依赖
 */
@Module
abstract class ViewModeModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(LoginRegisterViewModel::class)
    abstract fun bindLoginRegisterViewModel(loginRegisterViewModel: LoginRegisterViewModel): ViewModel

    @Binds
    @Singleton
    abstract fun bindViewModelFactory(factory:AppViewModeFactory): ViewModelProvider.Factory
}


/**
 * Retrofit 或其他依赖
 */
@Module(includes = [RetrofitProvice::class])
class ApiModule {
    @Provides
    @Singleton
    fun provideLoginRegisterService(retrofit: Retrofit): LoginRegisterRetrofitService {
        return retrofit.create(LoginRegisterRetrofitService::class.java)
    }

    @Provides
    @Singleton
    fun provideMainRetrofitService(retrofit: Retrofit): MainRetrofitService {
        return retrofit.create(MainRetrofitService::class.java)
    }

}

@Module
class RetrofitProvice {
    private val baseUrl = "https://wanandroid.com/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getHttpClient())
            .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
            .addConverterFactory(LiveDataResponseBodyConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getHttpClient(): OkHttpClient {
        var builder = OkHttpClient.Builder().addInterceptor(getDefaultInterceptor())
        addHttpConfig(builder)
        return builder.proxy(Proxy.NO_PROXY).build()
    }

    private fun addHttpConfig(builder: OkHttpClient.Builder) {

    }

    private fun getDefaultInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
    }
}