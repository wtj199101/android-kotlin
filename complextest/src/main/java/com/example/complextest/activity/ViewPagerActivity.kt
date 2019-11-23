package com.example.complextest.activity

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import com.example.complextest.R
import com.example.complextest.adapter.GoodsPagerAdapter
import com.example.complextest.adapter.ImagePagerAdapater
import com.example.complextest.adapter.MobilePagerAdapter
import com.example.complextest.model.GoodsInfo
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.acitivity_fragment_dynamic.*
import kotlinx.android.synthetic.main.activity_tab_layout.*
import kotlinx.android.synthetic.main.activity_view_pager.*
import org.jetbrains.anko.toast

class ViewPagerActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {
    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {  }

    override fun onPageSelected(position: Int) {
        toast("您翻到的是手机${GoodsInfo.defaultList[position].name}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
        pts_tab1.setTextSize(TypedValue.COMPLEX_UNIT_SP,20f)
        pts_tab1.setTextColor(Color.GREEN)
        vp_content.adapter=ImagePagerAdapater(this, GoodsInfo.defaultList)
        vp_content.currentItem=0
        vp_content.addOnPageChangeListener(this)
    }

}
class FragmentDynamicActivity : FragmentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivity_fragment_dynamic)
        pts_tab2.setTextSize(TypedValue.COMPLEX_UNIT_SP,20f)
        vp_content2.adapter= MobilePagerAdapter(supportFragmentManager,GoodsInfo.defaultList)
        vp_content2.currentItem=0

    }
}
class TabLayoutActivity : AppCompatActivity(),TabLayout.OnTabSelectedListener{

    private val titles = mutableListOf("商品", "详情")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_layout)
        setSupportActionBar(tl_head3)
        tl_head3.setNavigationOnClickListener { finish() }
        initTabLayout()
        initViewPager()
    }

    fun initTabLayout(){
        tl_title3.addTab(tl_title3.newTab().setText(titles[0]))
        tl_title3.addTab(tl_title3.newTab().setText(titles[1]))
        tl_title3.addOnTabSelectedListener(this)
    }
    fun initViewPager(){
        vp_content3.adapter=GoodsPagerAdapter(supportFragmentManager,titles)
        vp_content3.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener(){
            override fun onPageSelected(position: Int) {
                tl_title3.getTabAt(position)!!.select()
            }
        })
    }
    override fun onTabReselected(p0: TabLayout.Tab?) { }

    override fun onTabUnselected(p0: TabLayout.Tab) { }

    override fun onTabSelected(p0: TabLayout.Tab) {vp_content3.currentItem=p0.position }
}