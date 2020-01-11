package com.www.kotlin.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import com.base.kotlin.base.BaseActivity
import com.www.kotlin.App
import com.www.kotlin.R
import com.www.kotlin.utils.AppUtils
import com.www.kotlin.utils.ImageLoadUtils
import com.www.kotlin.utils.Preference
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.layout_head.*
import org.jetbrains.anko.email
import org.jetbrains.anko.toast

class SettingActivity :BaseActivity(), View.OnClickListener,CompoundButton.OnCheckedChangeListener {
    override fun getContentView()= R.layout.activity_setting

    private   var isNoImage:Boolean by Preference(this,"isNoImage",false)
    private   var isDarkStyle :Boolean  by Preference(this,"isDarkStyle",false)
    private  var appToken by Preference(this,"appToken","")

    override fun init(savedInstanceState: Bundle?) {
        titlebar.title="设置"
        titlebar.setNavigationOnClickListener { onBackPressed() }

        tv_clear_cache.setOnClickListener(this)
        tv_upgrade.setOnClickListener(this)
        tv_feedback.setOnClickListener(this)
        cb_dark_style.isChecked=isDarkStyle
        cb_no_image.isChecked=isNoImage
        cb_dark_style.setOnCheckedChangeListener(this)
        cb_no_image.setOnCheckedChangeListener(this)

        btn_logOut.visibility=(if(appToken.isEmpty())View.GONE else View.VISIBLE)
        btn_logOut.setOnClickListener(this)
        tv_clear_size.text=AppUtils.getTotalCacheSize(applicationContext)
        tv_version.text=("v${App.getVersionName()}")


    }



    override fun onClick(v: View?) {
            when(v!!.id){
                R.id.tv_clear_cache->{
                    AppUtils.clearAllCache(this)
                    toast("清除缓存成功")
                    tv_clear_size.text=AppUtils.getTotalCacheSize(applicationContext)
                }
                R.id.tv_upgrade ->{
                }
                R.id.tv_feedback -> email("422434668@qq.com","反馈app问题")

                R.id.btn_logOut -> {
                    appToken=""
                    finish()
                }
            }

    }
    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        when(buttonView!!.id){
            R.id.cb_dark_style->{
                isDarkStyle=isChecked
                val mode:Int=if(isChecked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
                AppCompatDelegate.setDefaultNightMode(mode)
                finish()
            }
            R.id.cb_no_image->{
                ImageLoadUtils.isNoImage=isChecked
                isNoImage=isChecked
            }
        }
    }

}