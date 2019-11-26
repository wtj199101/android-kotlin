package com.example.complextest.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.ViewPager
import com.example.complextest.R
import com.example.complextest.adapter.ChannelPagerAdapter
import com.example.complextest.adapter.RecyclerExtras
import com.example.complextest.adapter.RecyclerSwipeAdapter
import com.example.complextest.model.RecyclerInfo
import com.example.complextest.widget.SpacesItemDecoration
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.acitivity_department_channel.*
import kotlinx.android.synthetic.main.activity_swipe_recycler.*
import org.jetbrains.anko.toast

class SwipeRecyclerActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener, RecyclerExtras.OnItemLongClickListener, RecyclerExtras.OnItemClickListener, RecyclerExtras.OnItemDeleteClickListener {
    private var currents = RecyclerInfo.defaultList
    lateinit var adapter: RecyclerSwipeAdapter

    override fun onItemClick(view: View, position: Int) {
        val desc = "您点击了第${position + 1}项，标题是${currents[position].title}"
        toast(desc)
    }

    override fun onItemLongClick(view: View, position: Int) {
        //长按时在该项右边弹出删除按钮
        currents[position].pressed = !(currents[position].pressed)
        //通知循环适配器在第position项发生了变更操作
        adapter.notifyItemChanged(position)
    }

    override fun onItemDeleteClick(view: View, position: Int) {
        //移除当前项
        currents.removeAt(position)
        //通知循环适配器在第position项发生了移除操作
        adapter.notifyItemRemoved(position)
    }

    private val mHandler = Handler()
    override fun onRefresh() {
        mHandler.postDelayed(mRefresh, 1000)
    }

    private val mRefresh = Runnable {
        srl_dynamic.isRefreshing = false
        val position = (Math.random() * 100 % currents.size).toInt()
        val old_item = currents[position]
        val new_item = RecyclerInfo(old_item.pic_id, old_item.title, old_item.desc)
        currents.add(new_item)
        adapter.notifyItemInserted(currents.size)
        rv_dynamic.scrollToPosition(0)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe_recycler)
        srl_dynamic.setOnRefreshListener(this)
        srl_dynamic.setColorSchemeResources(R.color.red, R.color.orange, R.color.green, R.color.blue)
        rv_dynamic.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerSwipeAdapter(this, currents)
        adapter.setOnItemClickListener(this)
        adapter.setOnItemLongClickListener(this)
        adapter.setOnItemLongClickListener(this)
        rv_dynamic.adapter = adapter
        rv_dynamic.itemAnimator = DefaultItemAnimator()
        rv_dynamic.addItemDecoration(SpacesItemDecoration(1))
    }
}

class DepartmentChannelActivity : AppCompatActivity() {
    private val titles = mutableListOf("服装", "电器")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivity_department_channel)
        tl_head.setBackgroundResource(R.color.pink)
        setSupportActionBar(tl_head)
        tl_head.setNavigationOnClickListener { finish() }

        tab_title.addTab(tab_title.newTab().setText(titles[0]))
        tab_title.addTab(tab_title.newTab().setText(titles[1]))
        tab_title.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(vp_content))

        vp_content.adapter= ChannelPagerAdapter(supportFragmentManager,titles)
        vp_content.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener(){
            override fun onPageSelected(position: Int) {
                tab_title.getTabAt(position)!!.select()
            }
        })

    }

    override fun onStart() {
        super.onStart()
        bgChangeReceiver=BgChangeReceiver()
        val intentFilter=IntentFilter(ChannelPagerAdapter.EVENT)
        registerReceiver(bgChangeReceiver,intentFilter)
    }

    override fun onStop() {
        unregisterReceiver(bgChangeReceiver)
        super.onStop()
    }

    private var bgChangeReceiver: BgChangeReceiver? = null
    private inner class BgChangeReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            if (intent != null) {
                val color = intent.getIntExtra("color", Color.WHITE)
                tl_head.setBackgroundColor(color)
            }
        }
    }
}