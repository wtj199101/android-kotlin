package com.yunfan.lithops.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * 日期工具类
 */
object DateUtils {
       private const val DATETIME:String="yyyy-MM-dd HH:mm:ss"
       private const val DATE:String="yyyy-MM-dd"

    val nowDateTime:String
    get() {
        return SimpleDateFormat(DATETIME).format(Date())
    }
    val nowDate:String
        get() {
            return SimpleDateFormat(DATE).format(Date())
        }
    /**
     * 获取时间格式化
     * 默认format: yyyy-MM-dd HH:mm:ss
     */
    fun getFormateDate(date: Date,format:String=DATETIME):String {
        return SimpleDateFormat(format).format(date)
    }
}