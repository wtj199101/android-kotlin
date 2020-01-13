package com.www.kotlin.dao.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

/**
 * Created by arvinljw on 2018/10/31 15:09
 *  * children : []
 * courseId : 13
 * id : 421
 * name : 互联网侦察
 * order : 190010
 * parentChapterId : 407
 * userControlSetTop : false
 * visible : 1
 * Function：
 * Desc：公众号信息
 */
@Parcelize
data class PublicNumberEntity (
    var courseId: Int = 0,
    var id: Int = 0,
    var name: String? = null,
    var order: Int = 0,
    var parentChapterId: Int = 0,
    var isUserControlSetTop: Boolean = false,
    var visible: Int = 0,
    var children: @RawValue List<Any>? = null
) : Parcelable {

}
