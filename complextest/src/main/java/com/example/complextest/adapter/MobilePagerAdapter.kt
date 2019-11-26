package com.example.complextest.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.complextest.fragment.BroadcastFragment
import com.example.complextest.model.GoodsInfo

class MobilePagerAdapter (private val fm :FragmentManager, private val defaultList: MutableList<GoodsInfo>): FragmentStatePagerAdapter(fm){
    override fun getItem(position: Int) : Fragment{
            val item=defaultList[position]
        return BroadcastFragment.newInstance(position,item.pic,item.desc)
    }

    override fun getCount(): Int =defaultList.size

    override fun getPageTitle(position: Int): CharSequence=defaultList[position].name
}