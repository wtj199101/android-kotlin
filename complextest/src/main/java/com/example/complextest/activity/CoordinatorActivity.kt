package com.example.complextest.activity

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.complextest.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_coordinator.*
import kotlinx.android.synthetic.main.activity_toolbar.*

class CoordinatorActivity : AppCompatActivity(){
    private  var floating_show=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coordinator)
        btn_snackbar.setOnClickListener {
            Snackbar.make(cl_main,"这是一个提示",Snackbar.LENGTH_LONG).show()
        }
        btn_floating.setOnClickListener {
            if(floating_show){
                fab_btn.hide()
                btn_floating.text="显示悬浮按钮"
            }else{
                fab_btn.show()
                btn_floating.text= "隐藏悬浮按钮"
            }
            floating_show=!floating_show
        }
    }
}
class ToolbarActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbar)
        tl_head.title="这是工具栏的主标题"
        tl_head.setTitleTextColor(Color.BLUE)
        tl_head.setLogo(R.drawable.ic_launcher)
        tl_head.subtitle="这是副标题"
        tl_head.setSubtitleTextColor(Color.YELLOW)
        //设置工具栏的背景
        tl_head.setBackgroundResource(R.color.blue_light)
        setSupportActionBar(tl_head)
        tl_head.setNavigationIcon(R.drawable.ic_back)
        tl_head.setNavigationOnClickListener { finish() }

    }
}