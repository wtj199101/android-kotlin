package com.www.kotlin.ui.fragments

import android.os.Bundle
import com.base.kotlin.base.BaseFragment
import com.www.kotlin.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(){
    override fun init(savedInstanceState: Bundle?) {

    }
    override fun getContentView()= R.layout.fragment_home

}