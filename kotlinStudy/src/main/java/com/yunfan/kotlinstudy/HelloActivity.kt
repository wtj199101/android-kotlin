package com.yunfan.kotlinstudy

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_hello.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast

class HelloActivity : BaseAcitivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello)
        hello_textView.text="你好！"
        shortClick.setOnClickListener{hello_textView.text="点击了shortclik"}
        longClick.setOnClickListener{hello_textView.text="点击了longClick"}

        button3.setOnClickListener{toast("你点击了一下button3")}
        button4.setOnLongClickListener{longToast("你点击了longToast");true}
    }
}