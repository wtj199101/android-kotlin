package com.www.kotlin.viewmodel

import com.www.kotlin.retrofit.service.LoginRegisterRetrofitService
import javax.inject.Inject

class LoginRegisterViewModel @Inject constructor(private val loginRegisterService: LoginRegisterRetrofitService)