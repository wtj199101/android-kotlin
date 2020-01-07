package com.www.kotlin

import android.app.Application

class App :Application(){

    override fun onCreate() {
        super.onCreate()
    }


    companion object {
        val TIMEOUT:Long=2000L
    }
}