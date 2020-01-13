package com.www.kotlin.dao.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by arvinljw on 2018/11/16 17:07
 * Function：
 *   * courseId : 13
 * id : 150
 * name : 开发环境
 * order : 1
 * parentChapterId : 0
 * visible : 1
 * Desc：
 */
@Parcelize
data class TreeEntity constructor( var children: List<TreeEntity>? = null,
                                           var courseId: Int = 0,
                                           var id: Int = 0,
                                           var name: String? = null,
                                           var order: Int = 0,
                                           var parentChapterId: Int = 0,
                                           var visible: Int = 0) : Parcelable, IPageTitle{
    override val title: String
        get() = this.name!!


}
