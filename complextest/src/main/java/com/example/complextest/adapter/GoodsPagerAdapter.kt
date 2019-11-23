package com.example.complextest.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.complextest.fragment.BookCoverFragment
import com.example.complextest.fragment.BookDetailFragment

class GoodsPagerAdapter (fm: FragmentManager,private val titles: MutableList<String>):FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment =when(position){
        0 -> BookCoverFragment()
        1 -> BookDetailFragment()
        else->BookDetailFragment()
    }

    override fun getCount(): Int =titles.size

    override fun getPageTitle(position: Int): CharSequence? =titles[position]
}