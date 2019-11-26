package com.example.complextest.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.complextest.R
import com.example.complextest.model.RecyclerInfo
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_recycler_linear.*

class RecyclerSwipeAdapter (context: Context,private val infos: MutableList<RecyclerInfo>):RecyclerBaseAdapter<RecyclerView.ViewHolder>(context){
    override fun getItemCount(): Int =infos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = inflater.inflate(R.layout.item_recycler_linear, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var vh=holder as ItemHolder
        vh.bind(infos[position],itemClickListener,itemLongClickListener,itemDeleteClickListener )
    }
      class ItemHolder(override val containerView: View):RecyclerView.ViewHolder(containerView),LayoutContainer{
            fun bind(item: RecyclerInfo,
                     clickListener: RecyclerExtras.OnItemClickListener?,
                     longClickListener: RecyclerExtras.OnItemLongClickListener?,
                     deleteClickListener: RecyclerExtras.OnItemDeleteClickListener?){
                iv_pic.setImageResource(item.pic_id)
                tv_title.text=item.title
                tv_desc.text=item.desc
                tv_delete.visibility=if (item.pressed)View.VISIBLE else View.GONE
                tv_delete.setOnClickListener {v ->
                    deleteClickListener?.onItemDeleteClick(v,adapterPosition)
                }
                ll_item.setOnClickListener { v->
                    clickListener?.onItemClick(v,adapterPosition)
                 }
                ll_item.setOnLongClickListener { v->
                    longClickListener?.onItemLongClick(v,adapterPosition)
                   true
                }
            }

      }

}