package com.www.kotlin.ui.activity

import android.os.Bundle
import com.base.kotlin.base.BaseActivity
import com.www.kotlin.R
import com.www.kotlin.lifecycle.ShimmerFrameLifeCycle
import kotlinx.android.synthetic.main.activity_about_us.*
import kotlinx.android.synthetic.main.layout_head.*

class CollectionArticleActivity :BaseActivity(){
    override fun getContentView()=R.layout.activity_collection_article

    override fun init(savedInstanceState: Bundle?) {
        titlebar.title="我的收藏"
        titlebar.setNavigationOnClickListener { onBackPressed() }
    }

}