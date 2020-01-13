package com.www.kotlin.ui.activity

import android.Manifest
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import com.badoo.mobile.util.WeakHandler
import com.base.kotlin.base.BaseActivity
import com.base.kotlin.utils.PermissionUtil
import com.www.kotlin.App
import com.www.kotlin.R
import com.www.kotlin.dao.entity.LoginResultEntity
import com.www.kotlin.utils.Preference
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_main.*
import net.arvin.selector.SelectorHelper
import org.jetbrains.anko.*

class MainActivity : BaseActivity(),AnkoLogger {


    private  var appToken by Preference(this,"appToken","")

    private  var userAvatar by Preference(this,"userAvatar","")

    override fun getContentView() = R.layout.activity_main

    private lateinit var mHandler: WeakHandler

    private var canQuit: Boolean = false

    private lateinit var menuRoot: View

    private lateinit var navController: NavController

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun init(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        mHandler = WeakHandler()
        //设置标题
        setSupportActionBar(titlebar)
        titlebar.inflateMenu(R.menu.top_toolbar_menu)
        titlebar.setNavigationOnClickListener {
            //左滑
            drawerlayout_main.openDrawer(GravityCompat.START)
        }

        navController = findNavController(R.id.layout_nav_host)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerlayout_main)
        /********************设置抽屉左边页面*********************/
        //先默认插入图片和姓名
        initUser()
        //菜单的点击
        nav_view.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_collection ->
                    if (hasLogin()){
                        navController.navigate(R.id.collectionArticleActivity)
                }else{
                        navController.navigate(R.id.loginRegisterActivity)
                    }
                R.id.menu_setting ->   navController.navigate(R.id.settingActivity)
                R.id.menu_about_us ->  navController.navigate(R.id.aboutUsActivity)
                else -> toast("别点了，疼")
            }
            true
        }
        /********************设置底层导航*********************/
        tab_bottom_nav.setOnNavigationItemSelectedListener {
            titlebar.title = it.title
            it.onNavDestinationSelected(navController) || super.onOptionsItemSelected(it)
        }
    }

    private fun changeAvatar() {
        getPermissionUtils(this).request(
            "修改头像需要访问相机和本地媒体文件",
            PermissionUtil.asArray(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        ) { granted, isAlwaysDenied -> showChangeAvatarDialog() }
    }

    private val REQ_CODE_CHANGE_AVATAR = 1001

    private fun showChangeAvatarDialog() {
        alert("换个帅气的头像吧～"){
            positiveButton("确定"){
                SelectorHelper.selectPicture(
                    this@MainActivity,
                    true,
                    true,
                    REQ_CODE_CHANGE_AVATAR
                )}
            negativeButton("取消"){
            }
        }.show()
    }

      fun getPermissionUtils(fragment: FragmentActivity):PermissionUtil {
      return  PermissionUtil.Builder().with(fragment) .setTitleText("提示")//弹框标题
            .setEnsureBtnText("确定")//权限说明弹框授权按钮文字
            .setCancelBtnText("取消")//权限说明弹框取消授权按钮文字
            .setSettingEnsureText("设置")//打开设置说明弹框打开按钮文字
            .setSettingCancelText("取消")//打开设置说明弹框关闭按钮文字
            .setSettingMsg("当前应用缺少必要权限。\n请点击\"设置\"-\"权限\"-打开所需权限。")//打开设置说明弹框内容文字
            .setInstallAppMsg("允许安装来自此来源的应用")//打开允许安装此来源的应用设置
            .setShowRequest(true)//是否显示申请权限弹框
            .setShowSetting(true)//是否显示设置弹框
            .setShowInstall(true)//是否显示允许安装此来源弹框
            .setRequestCancelable(true)//申请权限说明弹款是否cancelable
            .setSettingCancelable(true)//打开设置界面弹款是否cancelable
            .setInstallCancelable(true)//打开允许安装此来源引用弹款是否cancelable
            .setTitleColor(Color.BLACK)//弹框标题文本颜色
            .setMsgColor(Color.GRAY)//弹框内容文本颜色
            .setEnsureBtnColor(Color.BLACK)//弹框确定文本颜色
            .setCancelBtnColor(Color.BLACK)//弹框取消文本颜色
            .build()
    }
    /**
     * true login
     * false not login
     */
    fun hasLogin(): Boolean = appToken != null

    private fun initUser() {
        menuRoot = nav_view.getHeaderView(0)
        menuRoot.find<ImageView>(R.id.img_avatar ).setOnClickListener {
            if(hasLogin()){
                changeAvatar()
            }else{
                navController.navigate(R.id.loginRegisterActivity)
            }
        }
        menuRoot.find<TextView>(R.id.tv_name).setOnClickListener {
            navController.navigate(R.id.loginRegisterActivity)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_toolbar_menu, menu)
        return true
    }

    override fun onBackPressed() {
        if (!canQuit) {
            canQuit = true
            toast("再按一次返回键退出～")
            return
        }
        super.onBackPressed()
    }
}
