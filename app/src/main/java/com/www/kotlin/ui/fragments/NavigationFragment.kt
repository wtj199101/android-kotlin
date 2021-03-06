package com.www.kotlin.ui.fragments

import android.content.Context
import android.os.Bundle
import com.base.kotlin.base.BaseFragment
import com.www.kotlin.R
import dagger.android.support.AndroidSupportInjection

class NavigationFragment : BaseFragment(){
    override fun getContentView()= R.layout.fragment_navigation

    override fun init(savedInstanceState: Bundle?) {
    }
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}