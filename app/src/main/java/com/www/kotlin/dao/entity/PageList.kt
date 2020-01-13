package com.www.kotlin.dao.entity;

import android.util.SparseArray
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by arvinljw on 2018/11/16 16:53
 *
 * curPage : 2
 * offset : 20
 * over : false
 * pageCount : 284
 * size : 20
 * total : 5680
 * Function：
 * Desc：
 */
data class PageList<T> constructor(
    @SerializedName("datas")
     var data:MutableList<T>,
     var curPage: Int,
     var offset: Int,
     var over: Boolean,
     var pageCount: Int,
     var size: Int,
     var total: Int

){
 fun hasNextStartWithZero(): Boolean {
  return curPage + 1 < pageCount && notEmpty()
 }
 fun notEmpty(): Boolean {
  return data != null && data.size > 0
 }
}
