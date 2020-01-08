package com.www.kotlin.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @desc BaseActivity
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate( savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
        init(savedInstanceState)
    }

    abstract fun init(savedInstanceState: Bundle?)

    abstract fun getContentView(): Int

}