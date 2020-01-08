package com.www.kotlin.dao.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginResultEntity(
    var username: String? = null,
    var password: String? = null,
    var icon: String? = null,
    var type: Int? = null,
    var collectIds: List<Int>? = null
) : Parcelable