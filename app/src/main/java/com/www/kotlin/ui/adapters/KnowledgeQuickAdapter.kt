package com.www.kotlin.ui.adapters

import android.content.Intent
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.isGone
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.material.internal.FlowLayout
import com.www.kotlin.R
import com.www.kotlin.dao.entity.TreeEntity
import com.www.kotlin.ui.activity.KnowledgeTreeTabActivity
import com.www.kotlin.ui.fragments.KnowledgeFragment

class KnowledgeQuickAdapter(@LayoutRes var recyclerView: Int) :
    BaseQuickAdapter<TreeEntity, BaseViewHolder>(recyclerView) {

    override fun convert(helper: BaseViewHolder, item: TreeEntity?) {
        if (item != null) {
            helper.setText(R.id.tv_knowledge_type, item.name)
            val view = helper.getView<FlowLayout>(R.id.flowLayout)
            view.removeAllViews()
            val children = item.children
            if (children == null || children.isEmpty()) {
                view.visibility = View.GONE
            }
            view.visibility = View.VISIBLE
            for ( (index,tree) in children!!.withIndex()){
                val child = View.inflate(context, R.layout.layout_tag_knowledge_tree, null)
                val textView = child.findViewById<TextView>(R.id.tv_tag)
                val name = tree.name
                val content = SpannableString(name)
                content.setSpan(
                    UnderlineSpan(),
                    0,
                    name!!.length,
                    SpannableString.SPAN_INCLUSIVE_INCLUSIVE
                )
                textView.text = content
                val pos:Int = index
                child.setOnClickListener {
                    val intent = Intent(context, KnowledgeTreeTabActivity::class.java)
                    intent.putExtra("data", item)
                    intent.putExtra("position", pos)
                    startActivity(context,intent,null)
                }

            }

        }

    }

}




