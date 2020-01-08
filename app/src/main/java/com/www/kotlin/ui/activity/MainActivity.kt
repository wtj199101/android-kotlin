package com.www.kotlin.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import com.badoo.mobile.util.WeakHandler
import com.www.kotlin.base.BaseActivity
import com.www.kotlin.App
import com.www.kotlin.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_main.*
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

class MainActivity : BaseActivity() {

    override fun getContentView()=R.layout.activity_main

    private lateinit var mHandler: WeakHandler

    private   var canQuit:Boolean=false

    private val msgQuit=0

    private lateinit var menuRoot: View

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun init(savedInstanceState: Bundle?) {
        mHandler=WeakHandler()
        navController=findNavController(R.id.layout_nav_host)
        appBarConfiguration = AppBarConfiguration(navController.graph,drawerlayout_main)
//        layout_main.setupWithNavController(navController)
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
        //底层导航
        tab_bottom_nav.setOnNavigationItemSelectedListener {
            it.onNavDestinationSelected(navController)||super.onOptionsItemSelected(it)
        }
    }

    private fun initUser() {
        menuRoot= nav_view.getHeaderView(0)
        val imageView = menuRoot.find<ImageView>(R.id.img_avatar)
        val textView = menuRoot.find<TextView>(R.id.tv_name)
        textView.text="去登陆"
        imageView.setImageResource(R.drawable.img_avatar)
        textView.setOnClickListener {
            toast("去登录")
        }
        imageView.setOnClickListener {
            toast("换图片")
        }

    }



    override fun onBackPressed() {
        super.onBackPressed()
        if(!canQuit){
            canQuit=true
            toast("再按一次返回键退出～")
            mHandler.sendEmptyMessageDelayed(msgQuit, App.TIMEOUT)
        }
    }
}
