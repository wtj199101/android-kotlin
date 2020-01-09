package com.www.kotlin

import android.app.Application
import com.www.kotlin.dagger.DaggerApplicationComponent

class App :Application(){

    val appComponent=DaggerApplicationComponent.create()

    override fun onCreate() {
        super.onCreate()
    }
    companion object {
        val TIMEOUT:Long=2000L
    }
}