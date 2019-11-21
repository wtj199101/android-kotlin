package com.example.complextest.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerCommonAdapter<T>(context: Context, private val layoutId: Int, private val items:List<T>, val init: (View, T) -> Unit) : RecyclerBaseAdapter<RecyclerCommonAdapter.ItemHolder<T>>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = inflater.inflate(layoutId, parent, false)
        return ItemHolder(view,init)
    }

    override fun getItemCount()=items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val ih = holder as ItemHolder<T>
        ih.bind(items.get(position))
//        ih.iv_pic.setImageResource(infos[position].pic_id)
//        ih.tv_title.text = infos[position].title
//        ih.tv_desc.text = infos[position].desc
//        ih.ll_item.setOnClickListener {  v->
//            itemClickListener?.onItemClick(v,position)
//        }
//        ih.ll_item.setOnLongClickListener {  v->
//            itemLongClickListener?.onItemLongClick(v,position)
//                true
//        }

    }



     class ItemHolder<in T>(val view: View,val init: (View, T) -> Unit) : RecyclerView.ViewHolder(view) {
       fun bind(item: T){
           init(view,item)
       }
    }



//    override fun onItemClick(view: View, position: Int) {
////        val desc="你点击了${position+1}项,它的标题是${items[position]}"
////        context.toast(desc)
//    }
//
//    override fun onItemLongClick(view: View, position: Int) {
////        val desc="你长按了${position+1}项,它的标题是${items[position].desc}"
////        context.toast(desc)
//    }

}