package com.example.complextest.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.complextest.R
import com.example.complextest.adapter.RecyclerCommonAdapter
import com.example.complextest.model.LifeItem
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.acitivity_appbar_collapsepin.*
import kotlinx.android.synthetic.main.acitivity_appbar_collapsepin.ctl_title
import kotlinx.android.synthetic.main.acitivity_appbar_recycler.rv_main
import kotlinx.android.synthetic.main.acitivity_appbar_recycler.tl_title
import kotlinx.android.synthetic.main.acitivity_scroll_alipay.*
import kotlinx.android.synthetic.main.activity_appbar_nested.*
import kotlinx.android.synthetic.main.activity_scroll_flag.*
import kotlinx.android.synthetic.main.activity_toolbar.*
import kotlinx.android.synthetic.main.life_pay.*
import kotlinx.android.synthetic.main.toolbar_collapse.*
import kotlinx.android.synthetic.main.toolbar_expand.*
import org.jetbrains.anko.px2dip
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
class ScrollAlipayActivity :AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {
    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        val offset = Math.abs(verticalOffset)
        val total = appBarLayout.totalScrollRange
        val alphaIn = (px2dip(offset) * 2).toInt()
        val alphaOut = if (200 - alphaIn < 0) 0 else 200 - alphaIn
        //计算淡入时候的遮罩透明度
        val maskColorIn = Color.argb(alphaIn, Color.red(mMaskColor),
                Color.green(mMaskColor), Color.blue(mMaskColor))
        //工具栏下方的频道布局要加速淡入或者淡出
        val maskColorInDouble = Color.argb(alphaIn * 2, Color.red(mMaskColor),
                Color.green(mMaskColor), Color.blue(mMaskColor))
        //计算淡出时候的遮罩透明度
        val maskColorOut = Color.argb(alphaOut * 3, Color.red(mMaskColor),
                Color.green(mMaskColor), Color.blue(mMaskColor))
        if (offset <= total*0.45) { //偏移量小于一半，则显示展开时候的工具栏
            t1_expand.visibility = View.VISIBLE
            tl_collapse.visibility = View.GONE
            v_expand_mask.setBackgroundColor(maskColorInDouble)
        } else { //偏移量大于一半，则显示缩小时候的工具栏
            t1_expand.visibility = View.GONE
            tl_collapse.visibility = View.VISIBLE
            v_collapse_mask.setBackgroundColor(maskColorOut)
        }
        v_pay_mask.setBackgroundColor(maskColorIn)
    }

    private var mMaskColor: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivity_scroll_alipay)
        setSupportActionBar(tl_title)
        mMaskColor = resources.getColor(R.color.blue_dark)
        abl_title2.addOnOffsetChangedListener(this)
        rv_main.layoutManager=GridLayoutManager(this,4)
        rv_main.adapter=RecyclerCommonAdapter(this, R.layout.item_life, LifeItem.default,{ view, item->
            val iv_pic=view.findViewById<ImageView>(R.id.iv_pic)
            val tv_title=view.findViewById<TextView>(R.id.tv_title)
            iv_pic.setImageResource(item.pic_id)
            tv_title.text=item.title
        })

    }
}
