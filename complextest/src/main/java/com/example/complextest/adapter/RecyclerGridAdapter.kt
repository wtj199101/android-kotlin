package com.example.complextest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.complextest.R
import com.example.complextest.model.RecyclerInfo
import org.jetbrains.anko.toast

class RecyclerGridAdapter(private val context: Context, private val infos:MutableList<RecyclerInfo>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), RecyclerExtras.OnItemClickListener, RecyclerExtras.OnItemLongClickListener {
    val inflater: LayoutInflater= LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = inflater.inflate(R.layout.item_recycle_gird, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount()=infos.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val ih = holder as ItemHolder
        ih.iv_pic.setImageResource(infos[position].pic_id)
        ih.tv_title.text=infos[position].title
        ih.ll_item.setOnClickListener {  v->
            itemClickListener?.onItemClick(v,position)
        }
        ih.ll_item.setOnLongClickListener {  v->
            itemLongClickListener?.onItemLongClick(v,position)
                true
        }

    }


    private var itemClickListener : RecyclerExtras.OnItemClickListener? =null
    fun setOnItemClickListener(listener: RecyclerExtras.OnItemClickListener){
        this.itemClickListener=listener
    }
    private var itemLongClickListener: RecyclerExtras.OnItemLongClickListener?=null
    fun setOnItemLongClickListener(listener: RecyclerExtras.OnItemLongClickListener){
        this.itemLongClickListener=listener
    }

    inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ll_item = view.findViewById<LinearLayout>(R.id.ll_item)
        var iv_pic = view.findViewById<ImageView>(R.id.iv_pic)
        var tv_title = view.findViewById<TextView>(R.id.tv_title)
        var tv_desc = view.findViewById<TextView>(R.id.tv_desc)
    }


    override fun onItemClick(view: View, position: Int) {
        val desc="你点击了${position+1}项,它的标题是${infos[position].title}"
        context.toast(desc)
    }

    override fun onItemLongClick(view: View, position: Int) {
        val desc="你长按了${position+1}项,它的标题是${infos[position].desc}"
        context.toast(desc)
    }

}