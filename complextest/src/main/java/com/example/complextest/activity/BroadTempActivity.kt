package com.example.complextest.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Bundle
import android.text.format.DateUtils
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import com.example.complextest.R
import com.example.complextest.adapter.MobilePagerAdapter
import com.example.complextest.fragment.BroadcastFragment
import com.example.complextest.model.GoodsInfo
import com.example.complextest.utils.DateUtil
import kotlinx.android.synthetic.main.activity_broadcast_system.*
import kotlinx.android.synthetic.main.activity_broadcast_temp.*

class BroadTempActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast_temp)
        pts_tab.setTextSize(TypedValue.COMPLEX_UNIT_SP,20f)
        vp_content.adapter= MobilePagerAdapter(supportFragmentManager, GoodsInfo.defaultList)
        vp_content.currentItem=0
    }
    override fun onStart() {
        super.onStart()
        bgChangeReceiver=  BgChangeReceiver()
        val filter = IntentFilter(BroadcastFragment.EVENT)
        registerReceiver(bgChangeReceiver,filter)
    }
    override fun onStop() {
        unregisterReceiver(bgChangeReceiver)
        super.onStop()
    }
    private var bgChangeReceiver: BgChangeReceiver?=null

    private inner class BgChangeReceiver : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            if(intent!=null){
                val color = intent.getIntExtra("color", Color.WHITE)
                ll_broad.setBackgroundColor(color)
            }
        }
    }
}


class BroadSystemActivity :AppCompatActivity(){
    var desc = "开始侦听分钟广播，请稍等。注意要保持屏幕亮着，才能正常收到广播"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast_system)
        tv_system.text=desc
    }


    override fun onStart() {
        super.onStart()
        timeReceiver=TimeReceiver()
        val intentFilter=IntentFilter(Intent.ACTION_TIME_TICK)
        registerReceiver(timeReceiver,intentFilter)
    }

    override fun onStop() {
        unregisterReceiver(timeReceiver)
        super.onStop()
    }

    private var timeReceiver: TimeReceiver? = null
    inner class TimeReceiver:BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
             if(intent!=null){
                 desc="$desc\n${DateUtil.nowTime} 收到一个${intent.action}广播"
                 tv_system.text=desc
             }
        }

    }
}