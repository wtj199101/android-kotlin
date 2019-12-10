package com.example.storage_test.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {


    val nowDateString:String
    get() {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return sdf.format(Date())
    }
    val nowTime: String
    get() {
        val sdf = SimpleDateFormat("HH:mm:ss")
        return sdf.format(Date())
    }
}