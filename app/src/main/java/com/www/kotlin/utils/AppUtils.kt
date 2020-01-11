package com.www.kotlin.utils

import android.content.Context
import android.os.Environment
import java.io.File
import java.math.BigDecimal


object AppUtils {


    /**
     * 获取总文件大小
     */
    fun getTotalCacheSize(context: Context): String {
        var folderSize = getFolderSize(context.cacheDir)
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            folderSize += getFolderSize(context.externalCacheDir)
        }
        return getFormatSize(folderSize.toDouble())
    }

    fun clearAllCache(context:Context ) {
        deleteDir(context.cacheDir)
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            deleteDir(context.externalCacheDir)
        }
    }


    /**
     *  获取文件夹大小
     */
    private fun getFolderSize(file: File): Long {
        var size: Long = 0
        try {
            val fileList = file.listFiles()
            for (i in fileList.indices) {
                if (fileList[i].isDirectory) {
                    size += getFolderSize(fileList[i])
                } else {
                    size += fileList[i].length()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return size
    }

    /**
     * 获取格式化后文件大小
     */
    private fun getFormatSize(size: Double): String {
        val kiloByte = size / 1024
        if (kiloByte < 1) {
            return size.toString() + "Byte"
        }
        val megaByte = kiloByte / 1024
        var result: BigDecimal = BigDecimal.ZERO
        if (megaByte < 1) {
            result = BigDecimal(kiloByte.toString())
            return result.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB"
        }
        val gigaByte = megaByte / 1024
        if (gigaByte < 1) {
            result = BigDecimal(megaByte.toString())
            return result.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB"
        }
        val teraByte = gigaByte / 1024
        if (teraByte < 1) {
            result = BigDecimal(gigaByte.toString())
            return result.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB"
        }
        result = BigDecimal(teraByte.toString()).setScale(2, BigDecimal.ROUND_HALF_UP)
        return result.toPlainString() + "TB"
    }



    /**
     * 删除指定目录下所有文件及文件夹
     */
   private fun deleteDir(dir: File): Boolean {
        if (dir == null && !dir.exists()) {
            return false
        }
        if (dir.isDirectory) {
            for (file in dir.listFiles()!!) {
                if (!deleteDir(file)) {
                    return false
                }
            }
        }
        return dir.delete()
    }

}