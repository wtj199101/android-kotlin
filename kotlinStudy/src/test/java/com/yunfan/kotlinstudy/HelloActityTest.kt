package com.yunfan.kotlinstudy

import android.os.Build
import com.yunfan.kotlinstudy.grammar.HelloActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith( RobolectricTestRunner::class)
@Config(minSdk = Build.VERSION_CODES.N,maxSdk = Build.VERSION_CODES.Q)
class HelloActityTest {

    var mainAcitivity: HelloActivity? = null

    @Before
     fun setup(){
         mainAcitivity = Robolectric.buildActivity(HelloActivity::class.java).create().get()
    }

    @Test
    fun testShow(){
        assert(mainAcitivity!=null)
    }
}