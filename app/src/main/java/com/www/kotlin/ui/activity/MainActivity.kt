package com.www.kotlin.ui.activity

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.cxz.kotlin.baselibs.base.BaseActivity
import com.google.android.material.navigation.NavigationView
import com.www.kotlin.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private var navController: NavController?=null

    override fun init(savedInstanceState: Bundle?) {

    }
    override fun getContentView()=R.layout.activity_main


}
