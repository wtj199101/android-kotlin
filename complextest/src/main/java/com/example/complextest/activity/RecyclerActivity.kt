package com.example.complextest.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.example.complextest.R
import com.example.complextest.adapter.RecyclerCommonAdapter
import com.example.complextest.adapter.RecyclerGridAdapter
import com.example.complextest.adapter.RecyclerLinearAdapter
import com.example.complextest.model.RecyclerInfo
import com.example.complextest.widget.SpacesItemDecoration
import kotlinx.android.synthetic.main.activity_recycler_gird.*
import kotlinx.android.synthetic.main.activity_recycler_linear.*
import kotlinx.android.synthetic.main.activity_recycler_stagger.*

class RecyclerGridActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_gird)
        rv_gird.layoutManager=GridLayoutManager(this,5)
        val adapter=RecyclerGridAdapter(this,RecyclerInfo.defaultGrid)
        rv_gird.adapter=adapter
        adapter.setOnItemClickListener(adapter)
        adapter.setOnItemLongClickListener(adapter)
        rv_gird.itemAnimator=DefaultItemAnimator()
        rv_gird.addItemDecoration(SpacesItemDecoration(1))

    }
}

class RecyclerLinearActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_linear)
        rv_linear.layoutManager=LinearLayoutManager(this)
        val adapter= RecyclerLinearAdapter(this, RecyclerInfo.defaultList)
        adapter.setOnItemClickListener(adapter)
        adapter.setOnItemLongClickListener(adapter)
        rv_linear.adapter=adapter
        rv_linear.itemAnimator=DefaultItemAnimator()
        rv_linear.addItemDecoration(SpacesItemDecoration(1))
    }
}

class RecyclerStaggeredActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_stagger)
        rv_stagger.layoutManager=StaggeredGridLayoutManager(3,VERTICAL)
        val adapter=RecyclerCommonAdapter(this,R.layout.item_recycler_stagger,RecyclerInfo.defaultStag,{view: View, recyclerInfo: RecyclerInfo ->
            val iv_pic=view.findViewById<ImageView>(R.id.iv_pic)
            val tv_title=view.findViewById<TextView>(R.id.tv_title)


        })



    }
}