package com.www.kotlin.dao.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by arvinljw on 2018/11/16 17:32
 * Function：
 * Desc：
 */
@Parcelize
data class NavigationInfoEntity(
    var articles: List<ArticleEntity>? = null,
    var cid: Int = 0,
    var name: String? = null,
    var isSelected: Boolean = false
) : Parcelable