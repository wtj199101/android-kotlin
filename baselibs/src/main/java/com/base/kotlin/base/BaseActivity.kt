package com.base.kotlin.base

import android.os.Bundle
import android.transition.Fade
import android.view.Window
import androidx.appcompat.app.AppCompatActivity

/**
 * @desc BaseActivity
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate( savedInstanceState: Bundle?) {
        //页面的进入进出动画
        with(window){
            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
            enterTransition= Fade(Fade.IN)
            exitTransition= Fade(Fade.OUT)
        }
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
        init(savedInstanceState)
    }
    abstract fun getContentView(): Int

    abstract fun init(savedInstanceState: Bundle?)



}