package com.www.kotlin.retrofit

import com.www.kotlin.ui.activity.LoginRegisterActivity
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApplicationComponent {
    fun inject(loginRegisterActivity: LoginRegisterActivity )
}