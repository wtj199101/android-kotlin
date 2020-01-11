package com.www.kotlin.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerBaseAdapter<VH : RecyclerView.ViewHolder> (val content:Context):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    val inflater: LayoutInflater = LayoutInflater.from(content)

    //获得列表项的个数，需要子类重写
    override abstract fun getItemCount() :Int

    //根据布局文件创建视图持有者，需要子类重写
    override abstract fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    //绑定视图持有者中的各个控件对象，需要子类重写
    override abstract fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)

    override fun getItemId(position: Int)=position.toLong()

}