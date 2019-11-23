package com.example.complextest.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.complextest.fragment.DynamicFragment
import com.example.complextest.model.GoodsInfo

class MobilePagerAdapter (private val fm :FragmentManager, private val defaultList: MutableList<GoodsInfo>): FragmentPagerAdapter(fm){
    override fun getItem(position: Int) : Fragment{
            val item=defaultList[position]
        return DynamicFragment.newInstance(position,item.pic,item.desc,item.price)
    }

    override fun getCount(): Int =defaultList.size

    override fun getPageTitle(position: Int): CharSequence=defaultList[position].name
}