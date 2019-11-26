package com.example.complextest.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.complextest.R
import com.example.complextest.adapter.ChannelPagerAdapter
import com.example.complextest.adapter.RecyclerStaggeredAdapter
import com.example.complextest.model.RecyclerInfo
import com.example.complextest.widget.SpacesItemDecoration
import kotlinx.android.synthetic.main.fragment_clothes.*

class ClothesFragment:Fragment(),SwipeRefreshLayout.OnRefreshListener{
    override fun onRefresh() {
        mHandler.postDelayed(mRefresh,1000)
    }
    private val mRefresh = Runnable {
        //下拉刷新结束，要把isRefreshing设置为false，以便从界面上去除转圈图标
        srl_chothes.isRefreshing = false
        val i = alls.size - 1
        var count = 0
        while (count < 5) {
            val item = alls[i]
            alls.removeAt(i)
            alls.add(0, item)
            count++
        }
        //通知循环适配器发生了数据变更
        adapter.notifyDataSetChanged()
        //让循环视图滚动到第0项的位置
        rv_chothes.scrollToPosition(0)
    }
    private var  ctx: Context?=null
    lateinit var adapter: RecyclerStaggeredAdapter
    private var alls = RecyclerInfo.defaultStag
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        ctx=activity
       var view= inflater.inflate(R.layout.fragment_clothes,container,false)
        srl_chothes.setOnRefreshListener(this)
        srl_chothes.setColorSchemeResources( R.color.red, R.color.orange, R.color.green, R.color.blue)
        rv_chothes.layoutManager=StaggeredGridLayoutManager(3,LinearLayout.VERTICAL)
        adapter= RecyclerStaggeredAdapter(ctx!!,alls)
        adapter.setOnItemClickListener(adapter)
        adapter.setOnItemLongClickListener(adapter)
        rv_chothes.itemAnimator=DefaultItemAnimator()
        rv_chothes.addItemDecoration(SpacesItemDecoration(3))

        return view
    }

    private val mHandler = Handler()

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        //如果该页是一打开的默认页，则setUserVisibleHint先于onCreateView执行，此时ctx为空,有什麼用想不通
        if (ctx != null) {
            val intent = Intent(ChannelPagerAdapter.EVENT)
            intent.putExtra("color", ctx!!.resources.getColor(R.color.pink))
            ctx!!.sendBroadcast(intent)
        }
    }
}
class AppliancesFragment:Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}