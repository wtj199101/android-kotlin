package com.example.complextest.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.complextest.R
import com.example.complextest.adapter.RecyclerCommonAdapter
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.acitivity_appbar_collapsepin.*
import kotlinx.android.synthetic.main.acitivity_appbar_collapsepin.ctl_title
import kotlinx.android.synthetic.main.acitivity_appbar_recycler.rv_main
import kotlinx.android.synthetic.main.acitivity_appbar_recycler.tl_title
import kotlinx.android.synthetic.main.activity_appbar_nested.*
import kotlinx.android.synthetic.main.activity_scroll_flag.*
import kotlinx.android.synthetic.main.activity_toolbar.*
import org.jetbrains.anko.selector

class AppbarRecyclerActivity : AppCompatActivity(){
    private val yearArray = arrayListOf("鼠年", "牛年", "虎年", "兔年", "龙年", "蛇年", "马年", "羊年", "猴年", "鸡年", "狗年", "猪年")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivity_appbar_recycler)
        setSupportActionBar(tl_title)
        rv_main.layoutManager=LinearLayoutManager(this)
        rv_main.adapter=RecyclerCommonAdapter(this,R.layout.item_collapse,yearArray,{view,item->
            val tv_seq=view.findViewById<TextView>(R.id.tv_seq)
            val tv_title=view.findViewById<TextView>(R.id.tv_title)
            tv_seq.text="1"
            tv_title.text=item
        })
    }
}
class AppbarNestedActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appbar_nested)
        setSupportActionBar(tb_title)

    }
}
class CollapsePinActivity : AppCompatActivity(){
    private val years = arrayListOf("鼠年", "牛年", "虎年", "兔年", "龙年", "蛇年", "马年", "羊年", "猴年", "鸡年", "狗年", "猪年")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivity_appbar_collapsepin)
        ctl_title.setBackgroundColor(Color.RED)
        setSupportActionBar(tl_head)
        ctl_title.title="欢乐中国人"
        rv_main.layoutManager=LinearLayoutManager(this)
        rv_main.adapter=RecyclerCommonAdapter(this, R.layout.item_collapse,years,{view,item->
            val tv_seq=view.findViewById<TextView>(R.id.tv_seq)
            val tv_title=view.findViewById<TextView>(R.id.tv_title)
            tv_seq.text="1"
            tv_title.text=item
        })
    }
}

class CollapseParallaxActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //跟上一个类似
    }
}

class ImageFadeActivity :AppCompatActivity(){
    private val years = arrayListOf("鼠年", "牛年", "虎年", "兔年", "龙年", "蛇年", "马年", "羊年", "猴年", "鸡年", "狗年", "猪年")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_fade)
        ctl_title.setBackgroundColor(Color.WHITE)
        setSupportActionBar(tl_head)
        ctl_title.title="欢乐中国人"
        ctl_title.setExpandedTitleColor(Color.BLACK)
        ctl_title.setCollapsedTitleTextColor(Color.RED)
        rv_main.layoutManager=LinearLayoutManager(this)
        rv_main.adapter=RecyclerCommonAdapter(this, R.layout.item_collapse,years,{view,item->
            val tv_seq=view.findViewById<TextView>(R.id.tv_seq)
            val tv_title=view.findViewById<TextView>(R.id.tv_title)
            tv_seq.text="1"
            tv_title.text=item
        })

    }
}

class ScrollFlagActivity :AppCompatActivity(){
    private val years = arrayListOf("鼠年", "牛年", "虎年", "兔年", "龙年", "蛇年", "马年", "羊年", "猴年", "鸡年", "狗年", "猪年")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_flag)
        ctl_title.setBackgroundColor(Color.WHITE)
        setSupportActionBar(tl_head)
        ctl_title.title="欢乐中国人"
        rv_main.layoutManager=LinearLayoutManager(this)
        rv_main.adapter=RecyclerCommonAdapter(this, R.layout.item_collapse,years,{view,item->
            val tv_seq=view.findViewById<TextView>(R.id.tv_seq)
            val tv_title=view.findViewById<TextView>(R.id.tv_title)
            tv_seq.text="1"
            tv_title.text=item
        })
        initFlagSpinner()
    }
    private val descs = listOf("scroll", "scroll|enterAlways", "scroll|exitUntilCollapsed", "scroll|enterAlways|enterAlwaysCollapsed", "scroll|snap")
    private val flags = intArrayOf(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL, AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS, AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED, AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED, AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP)

    private fun initFlagSpinner() {
        sp_flag.visibility=View.GONE
        tv_spinner.visibility=View.VISIBLE
        tv_spinner.text=descs[0]
        tv_spinner.setOnClickListener {
            selector("请选择滚动类型",descs){i->
                tv_spinner.text=descs[i]
              val param=  ctl_title.layoutParams as AppBarLayout.LayoutParams
                param.scrollFlags=flags[i]
            }
        }
    }
}
