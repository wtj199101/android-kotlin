package com.www.kotlin.retrofit

import com.www.kotlin.ui.activity.LoginRegisterActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ApiModule::class])
@Singleton
interface ApplicationComponent {

    fun inject(loginRegisterActivity: LoginRegisterActivity )
}