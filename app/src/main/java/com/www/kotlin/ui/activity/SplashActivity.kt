package com.www.kotlin.ui.activity

import android.os.Bundle
import com.badoo.mobile.util.WeakHandler
import com.base.kotlin.base.BaseActivity
import com.facebook.shimmer.ShimmerFrameLayout
import com.www.kotlin.App
import com.www.kotlin.R
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity

class SplashActivity : BaseActivity(){
    private lateinit  var  layoutShimmer: ShimmerFrameLayout

    private lateinit var mHandler:WeakHandler

    override fun init(savedInstanceState: Bundle?) {
        layoutShimmer=layout_shimmer
        mHandler=WeakHandler()
        mHandler.postDelayed({
            startActivity<MainActivity>()
            finish()
        }
        , App.TIMEOUT)

    }


    override fun getContentView(): Int = R.layout.activity_splash

    override fun onResume() {
        super.onResume()
        layoutShimmer.startShimmer()
    }

    override fun onPause() {
        layoutShimmer.stopShimmer()
        super.onPause()
    }
}