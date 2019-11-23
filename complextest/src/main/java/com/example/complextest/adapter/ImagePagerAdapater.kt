package com.example.complextest.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.example.complextest.model.GoodsInfo

class ImagePagerAdapater (private val context: Context,private val goodsList: MutableList<GoodsInfo>) : PagerAdapter(){
    private val views= mutableListOf<ImageView>()

    init {
        for (item in goodsList) {
            val view = ImageView(context)
            view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            view.setImageResource(item.pic)
            view.scaleType = ImageView.ScaleType.FIT_CENTER
            views.add(view)
        }
    }

    override fun isViewFromObject(view: View, arg2: Any): Boolean = (view==arg2)
    override fun getCount(): Int =views.size
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(views[position])
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        container.addView(views[position])
        return views[position]
    }

    override fun getPageTitle(position: Int): CharSequence?  =goodsList[position].name
}