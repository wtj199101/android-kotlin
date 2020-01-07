package com.www.kotlin.ui.fragments

import android.os.Bundle
import com.cxz.kotlin.baselibs.base.BaseFragment
import com.www.kotlin.R

class MainFragment : BaseFragment(){

    override fun getContentView()= R.layout.fragment_main

    private var homeFragment:HomeFragment?=null

    override fun init(savedInstanceState: Bundle?) {
        val beginTransaction = activity!!.supportFragmentManager.beginTransaction()
        homeFragment = HomeFragment()
        beginTransaction.replace(R.id.layout_main_content, homeFragment!!)
        beginTransaction.commit()
    }

}