package com.www.kotlin

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.www.kotlin.utils.Preference
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App :Application(),HasAndroidInjector{
    @Inject lateinit var dispatchingAndroidInjector:DispatchingAndroidInjector<Any>
    override fun androidInjector() =dispatchingAndroidInjector


    private   var isDarkStyle :Boolean  by Preference(this,"isDarkStyle",false)

    override fun onCreate(){
        super.onCreate()
        DaggerApplicationComponent.create().inject(this)
        AppCompatDelegate.setDefaultNightMode(if(isDarkStyle)AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)

    }

    companion object {
        const val TIMEOUT:Long=2000L
        fun getVersionName():String=BuildConfig.VERSION_NAME
    }
}