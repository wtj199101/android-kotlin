package com.www.kotlin.ui.adapters

import android.graphics.Color
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.www.kotlin.R
import com.www.kotlin.dao.entity.ArticleEntity

class ArticleQuickAdapter(var layoutId:Int=R.layout.item_home_article) : BaseQuickAdapter<ArticleEntity, BaseViewHolder>(layoutId){



    override fun convert(helper: BaseViewHolder, item: ArticleEntity?) {
        if(item!=null){
            helper.setText(R.id.iv_icon,deal(item.title!!))
            helper.setText(R.id.tv_time, item.niceDate)
            helper.setText(R.id.tv_desc, item.desc)
            helper.setGone(R.id.tv_desc, item.desc!!.isNotEmpty())
            helper.setText(R.id.tv_author, item.author)
            helper.setText(R.id.tv_chapter, item.chapterName)

        }

    }


    private fun deal(title: String): SpannableString {
        var title = title
        val keyStart = "<em class='highlight'>"
        val keyEnd = "</em>"
        if (!title.contains(keyStart) && !title.contains(keyEnd)) {
            return SpannableString(title)
        }
        val start = title.indexOf(keyStart)
        val end = title.indexOf(keyEnd) - keyStart.length
        title = title.replace(keyStart, "")
        title = title.replace(keyEnd, "")
        val sb = SpannableString(title)
        sb.setSpan(
            ForegroundColorSpan(Color.parseColor("#F44336")),
            start,
            end,
            SpannableString.SPAN_INCLUSIVE_INCLUSIVE
        )
        return sb
    }
}

