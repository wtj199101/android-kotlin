package com.example.complextest.fragment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.complextest.R
import org.jetbrains.anko.selector

class BroadcastFragment : Fragment() {
    private var ctx: Context? = null
    private var mPosition: Int = 0
    private var mImageId: Int = 0
    private var mDesc: String? = null
    lateinit var sp_bg: Spinner
    lateinit var tv_spinner: TextView
    private var mSeg: Int=0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
          ctx = activity
            if(arguments!=null){
                mPosition= arguments!!.getInt("position", 0)
                mImageId= arguments!!.getInt("image_id", 0)
                mDesc= arguments!!.getString("desc")
            }
        val  view = inflater.inflate(R.layout.fragment_broadcast, container, false)
        val iv_pic= view.findViewById<ImageView>(R.id.iv_pic)
        val iv_desc= view.findViewById<TextView>(R.id.tv_desc)
          sp_bg= view.findViewById<Spinner>(R.id.sp_bg)
        tv_spinner= view.findViewById<TextView>(R.id.tv_spinner)
        iv_pic.setImageResource(mImageId)
        iv_desc.text=mDesc
        return view
    }
    private val colorNames = listOf("红色", "黄色", "绿色", "青色", "蓝色")
    private val colorIds = intArrayOf(Color.RED, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE)

    private fun initSpinner(){
        sp_bg.visibility=View.GONE
        tv_spinner.visibility=View.VISIBLE
        tv_spinner.text=colorNames[mSeg]
        tv_spinner.setOnClickListener {
            ctx!!.selector("请选择页面背景色",colorNames){i->
                tv_spinner.text=colorNames[i]
                mSeg=i
                val intent=Intent(BroadcastFragment.EVENT)
                intent.putExtra("seq", i)
                intent.putExtra("color", colorIds[i])
                ctx!!.sendBroadcast(intent)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("start","onStart")
        initSpinner()
        val intentFilter=IntentFilter(EVENT)
        ctx!!.registerReceiver(bgChangeReceiver,intentFilter)
    }

    override fun onStop() {
        Log.i("stop","onstop")
        ctx!!.unregisterReceiver(bgChangeReceiver)
        super.onStop()

    }
    private var bgChangeReceiver: BgChangeReceiver? = null

    private inner class BgChangeReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            if (intent != null) {
                mSeg = intent.getIntExtra("seq", 0)
                tv_spinner.text = colorNames[mSeg]
            }
        }
    }
    companion object {
        const val EVENT="com.example.complextest.fragment.BroadcastFragment"
        fun newInstance(position: Int, image_id: Int, desc: String):BroadcastFragment{
            val broadcastFragment=BroadcastFragment()
            val bundle=Bundle()
            bundle.putInt("position", position)
            bundle.putInt("image_id", image_id)
            bundle.putString("desc", desc)
            broadcastFragment.arguments=bundle
            return broadcastFragment
        }
    }
}
