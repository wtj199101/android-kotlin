package com.www.kotlin.dao.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TagEntity( var name: String,  var url: String) : Parcelable