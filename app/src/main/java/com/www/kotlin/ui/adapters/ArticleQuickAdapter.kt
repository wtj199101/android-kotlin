package com.www.kotlin.ui.adapters

import android.content.Context
import android.graphics.Color
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.www.kotlin.R
import com.www.kotlin.dao.entity.ArticleEntity
import com.www.kotlin.utils.ImageLoadUtils

class ArticleQuickAdapter constructor(content:Context, srl:SwipeRefreshLayout, rvItemId:Int) :
    BaseRecyclerAdapter<ArticleEntity, BaseViewHolder>(content,rvItemId) {




    private var srl=srl
    override fun onRefresh() {
        this.setNewData(itemList)
        srl.post {
            onRefresh()
        }
    }
    private var showCollection: Boolean = true
    var firstPage: Int = 0
    var currPage: Int=firstPage

    override fun onConvert(helper:BaseViewHolder,item: ArticleEntity) {
            helper.setText(R.id.tv_title, deal(item.title!!))
            helper.setText(R.id.tv_time, item.niceDate)
            helper.setText(R.id.tv_desc, item.desc)
            helper.setGone(R.id.tv_desc, item.desc!!.isNotEmpty())
            helper.setText(R.id.tv_author, item.author)
            helper.setText(R.id.tv_chapter, item.chapterName)
            if (showCollection) {
                helper.setImageResource(
                    R.id.img_collection, if (item.collect) {
                        R.drawable.ic_collection
                    } else {
                        R.drawable.ic_uncollection
                    }
                )
            } else {
                helper.setGone(R.id.img_collection, false)
            }
            val imgIcon = helper.getView<ImageView>(R.id.iv_icon)
            if (item.envelopePic != null && item.envelopePic!!.isNotEmpty()) {
                ImageLoadUtils.loadImage(context, item.envelopePic!!, imgIcon)
            } else {
                helper.setGone(R.id.iv_icon, true)
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




