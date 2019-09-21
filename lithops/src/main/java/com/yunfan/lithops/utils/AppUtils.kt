package com.yunfan.lithops.utils

import android.os.Environment

object AppUtils {

    val appPublicPath: String
    get() = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()

}