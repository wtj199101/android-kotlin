package com.example.complextest.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.complextest.fragment.AppliancesFragment
import com.example.complextest.fragment.ClothesFragment

class ChannelPagerAdapter (fm: FragmentManager,var titles:List<String>):FragmentStatePagerAdapter(fm){
    override fun getItem(position: Int): Fragment =when(position) {
        0-> ClothesFragment()
        1-> AppliancesFragment()
        else -> ClothesFragment()
    }

    override fun getCount(): Int =titles.size

    override fun getPageTitle(position: Int): CharSequence? =titles[position]

    companion object {
        const val EVENT="com.example.complextest.adapter.ChannelPagerAdapter"
    }

}