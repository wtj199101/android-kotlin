package com.www.kotlin.ui.activity

import android.os.Bundle
import com.badoo.mobile.util.WeakHandler
import com.cxz.kotlin.baselibs.base.BaseActivity
import com.www.kotlin.App
import com.www.kotlin.R
import com.www.kotlin.ui.fragments.MainFragment
import org.jetbrains.anko.toast

class MainActivity : BaseActivity() {


    private var mainFragment: MainFragment ?=null

    private lateinit var mHandler: WeakHandler

    private   var canQuit:Boolean=false

    private val msgQuit=0

    override fun init(savedInstanceState: Bundle?) {
        val beginTransaction = supportFragmentManager.beginTransaction()
        mainFragment = MainFragment()
        beginTransaction.replace(R.id.layout_content, mainFragment!!)
        beginTransaction.commit()
        mHandler=WeakHandler()
    }
    override fun getContentView()=R.layout.activity_main

    override fun onBackPressed() {
        super.onBackPressed()
        if(!canQuit){
            canQuit=true
            toast("再按一次返回键退出～")
            mHandler.sendEmptyMessageDelayed(msgQuit, App.TIMEOUT)
        }
    }
}
