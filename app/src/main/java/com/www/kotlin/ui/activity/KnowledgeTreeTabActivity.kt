package com.www.kotlin.ui.activity

import android.os.Bundle
import com.base.kotlin.base.BaseActivity
import com.www.kotlin.R
import com.www.kotlin.dao.entity.TreeEntity
import kotlinx.android.synthetic.main.layout_head.*

class KnowledgeTreeTabActivity:BaseActivity(){
        val KEY_DATA = "data"
        val KEY_POSITION = "position"
    override fun getContentView()= R.layout.activity_knowledge_tree_tab

    private lateinit var entity:TreeEntity

    override fun init(savedInstanceState: Bundle?) {
        setSupportActionBar(titlebar)
        titlebar.setNavigationOnClickListener { onBackPressed() }
          entity = savedInstanceState!!.getParcelable(KEY_DATA)
          var position:Int = savedInstanceState!!.getInt(KEY_POSITION,0)
        //TODO 不做了 tablay+viewPage 实现tab页效果
        titlebar.title= entity.name
    }

}