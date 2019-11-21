package com.example.complextest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.complextest.R
import com.example.complextest.model.Planet
import org.jetbrains.anko.toast

class PlanetGridAdapter(private val context: Context, private val planetList:MutableList<Planet>, private  var background: Int) : BaseAdapter(),AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {

    override fun getCount() = planetList.size

    override fun getItem(position: Int) = planetList[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        var holder: ViewHolder
        if (view==null){
           view = LayoutInflater.from(context).inflate(R.layout.item_grid_view, null)
            holder=ViewHolder(view)
            view.tag=holder
        }else{
            holder=view.tag as ViewHolder
        }
        val planet=planetList[position]
        holder.ll_item.setBackgroundColor(background)
        holder.iv_icon.setImageResource(planet.image)
        holder.tv_name.text=planet.name
        holder.tv_desc.text=planet.desc
        return view!!
    }

    inner class ViewHolder(val view: View) {
        val  ll_item: LinearLayout = view.findViewById(R.id.ll_item)
        val  iv_icon: ImageView = view.findViewById(R.id.iv_icon)
        val  tv_name: TextView = view.findViewById(R.id.tv_name)
        val  tv_desc: TextView = view.findViewById(R.id.tv_desc)
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val desc="你点击了${position+1}个行星,它的名字是${planetList[position].name}"
        context.toast(desc)
    }

    override fun onItemLongClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long): Boolean {
        val desc="你长按了${position+1}个行星,它的名字是${planetList[position].name}"
        context.toast(desc)
        return true
    }

}