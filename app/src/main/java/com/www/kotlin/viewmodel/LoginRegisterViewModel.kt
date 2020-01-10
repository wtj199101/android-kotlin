package com.www.kotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.leonardoxh.livedatacalladapter.Resource
import com.www.kotlin.dao.entity.LoginResultEntity
import com.www.kotlin.retrofit.LoginRegisterRetrofitService
import com.www.kotlin.retrofit.response.Response
import org.jetbrains.anko.info
import javax.inject.Inject

class LoginRegisterViewModel @Inject constructor( loginRegisterService: LoginRegisterRetrofitService) :
    ViewModel() {

    private var loginService = loginRegisterService

   lateinit var user: MutableLiveData<Resource<Response<LoginResultEntity>>>

    fun loginUser(
        username: String?,
        password: String?
    ): LiveData<Resource<Response<LoginResultEntity>>> {
        return loginService.login(username!!, password!!)
    }


}
