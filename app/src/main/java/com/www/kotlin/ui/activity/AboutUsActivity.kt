package com.www.kotlin.ui.activity

import android.os.Bundle
import com.base.kotlin.base.BaseActivity
import com.www.kotlin.App
import com.www.kotlin.R
import com.www.kotlin.lifecycle.ShimmerFrameLifeCycle
import kotlinx.android.synthetic.main.activity_about_us.*
import kotlinx.android.synthetic.main.layout_head.*

class AboutUsActivity :BaseActivity(){
    override fun getContentView()=R.layout.activity_about_us

    override fun init(savedInstanceState: Bundle?) {
        titlebar.title="关于我们"
        titlebar.setNavigationOnClickListener { onBackPressed() }
        tv_version.text="v${App.getVersionName()}"
        lifecycle.addObserver(ShimmerFrameLifeCycle(lifecycle,layout_shimmer))

    }

}