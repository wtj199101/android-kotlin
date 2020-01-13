package com.www.kotlin.ui.activity

import android.os.Bundle
import com.badoo.mobile.util.WeakHandler
import com.base.kotlin.base.BaseActivity
import com.www.kotlin.App
import com.www.kotlin.R
import com.www.kotlin.ui.lifecycle.ShimmerFrameLifeCycle
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity

class SplashActivity : BaseActivity(){

    private lateinit var mHandler:WeakHandler

    override fun init(savedInstanceState: Bundle?) {
        lifecycle.addObserver(ShimmerFrameLifeCycle(lifecycle,layout_shimmer))
        mHandler=WeakHandler()
        mHandler.postDelayed({
            startActivity<MainActivity>()
            finish()
        }
        , App.TIMEOUT)
    }


    override fun getContentView(): Int = R.layout.activity_splash
}