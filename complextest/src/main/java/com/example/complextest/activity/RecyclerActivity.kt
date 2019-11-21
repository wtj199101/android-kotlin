package com.example.complextest.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.complextest.R
import com.example.complextest.adapter.PlanetGridAdapter
import com.example.complextest.adapter.RecyclerLinearAdapter
import com.example.complextest.model.RecyclerInfo
import com.example.complextest.widget.SpacesItemDecoration
import kotlinx.android.synthetic.main.activity_recycler_linear.*

class RecyclerGridActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    }
}