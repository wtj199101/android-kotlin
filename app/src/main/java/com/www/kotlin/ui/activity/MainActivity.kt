package com.www.kotlin.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.badoo.mobile.util.WeakHandler
import com.cxz.kotlin.baselibs.base.BaseActivity
import com.www.kotlin.App
import com.www.kotlin.R
import com.www.kotlin.ui.fragments.MainFragment
import com.www.kotlin.utils.Preference
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_draw_head.*
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

class MainActivity : BaseActivity() {
    private var mainFragment: MainFragment ?=null

    private lateinit var mHandler: WeakHandler

    private   var canQuit:Boolean=false

    private val msgQuit=0

    private var menuRoot: View?=null

    override fun init(savedInstanceState: Bundle?) {
        val beginTransaction = supportFragmentManager.beginTransaction()
        mainFragment = MainFragment()
        beginTransaction.replace(R.id.layout_main, mainFragment!!)
        beginTransaction.commit()
        mHandler=WeakHandler()

        /********************设置抽屉左边页面*********************/
        //先默认插入图片和姓名
        initUser()
        //菜单的点击
        nav_view.setNavigationItemSelectedListener {
           when(it.itemId){
               R.id.menu_collection-> toast("点击了我的收藏")
               R.id.menu_setting-> toast("点击了我的设置")
               R.id.menu_about_us-> toast("点击了关于我们")
               else-> toast("未知点击")
           }
            true
        }
    }

    private fun initUser() {
        menuRoot= nav_view.getHeaderView(0)
        val imageView = menuRoot!!.find<ImageView>(R.id.img_avatar)
        val textView = menuRoot!!.find<TextView>(R.id.tv_name)
        textView.text="去登陆"
        imageView.setImageResource(R.drawable.img_avatar)
        textView.setOnClickListener {
            toast("去登录")
        }
        imageView.setOnClickListener {
            toast("换图片")
        }

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
