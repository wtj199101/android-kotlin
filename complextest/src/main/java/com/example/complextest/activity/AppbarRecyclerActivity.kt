package com.example.complextest.activity

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.complextest.R
import com.example.complextest.adapter.RecyclerCommonAdapter
import kotlinx.android.synthetic.main.acitivity_appbar_collapsepin.*
import kotlinx.android.synthetic.main.acitivity_appbar_recycler.rv_main
import kotlinx.android.synthetic.main.acitivity_appbar_recycler.tl_title
import kotlinx.android.synthetic.main.activity_appbar_nested.*
import kotlinx.android.synthetic.main.activity_toolbar.*

class AppbarRecyclerActivity : AppCompatActivity(){
    private val yearArray = arrayListOf("鼠年", "牛年", "虎年", "兔年", "龙年", "蛇年", "马年", "羊年", "猴年", "鸡年", "狗年", "猪年")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivity_appbar_recycler)
        setSupportActionBar(tl_title)
        rv_main.layoutManager=LinearLayoutManager(this)
        rv_main.adapter=RecyclerCommonAdapter(this,R.layout.item_collapse,yearArray,{view,item->
            val tv_seq=view.findViewById<TextView>(R.id.tv_seq)
            val tv_title=view.findViewById<TextView>(R.id.tv_title)
            tv_seq.text="1"
            tv_title.text=item
        })
    }
}
class AppbarNestedActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appbar_nested)
        setSupportActionBar(tb_title)

    }
}
class CollapsePinActivity : AppCompatActivity(){
    private val years = arrayListOf("鼠年", "牛年", "虎年", "兔年", "龙年", "蛇年", "马年", "羊年", "猴年", "鸡年", "狗年", "猪年")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivity_appbar_collapsepin)
        ctl_title.setBackgroundColor(Color.RED)
        setSupportActionBar(tl_head)
        ctl_title.title="欢乐中国人"
        rv_main.layoutManager=LinearLayoutManager(this)
        rv_main.adapter=RecyclerCommonAdapter(this, R.layout.item_collapse,years,{view,item->
            val tv_seq=view.findViewById<TextView>(R.id.tv_seq)
            val tv_title=view.findViewById<TextView>(R.id.tv_title)
            tv_seq.text="1"
            tv_title.text=item
        })
    }
}