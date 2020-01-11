package com.www.kotlin

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.www.kotlin.retrofit.DaggerApplicationComponent
import com.www.kotlin.utils.Preference

class App :Application(){

    val appComponent= DaggerApplicationComponent.create()!!
    private   var isDarkStyle :Boolean  by Preference(this,"isDarkStyle",false)

    override fun onCreate(){
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(if(isDarkStyle)AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)
    }

    companion object {
        const val TIMEOUT:Long=2000L

        fun getVersionName():String=BuildConfig.VERSION_NAME

    }
}