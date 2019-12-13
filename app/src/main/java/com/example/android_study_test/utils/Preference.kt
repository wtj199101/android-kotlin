package com.cxz.kotlin.baselibs.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import kotlin.reflect.KProperty

/**
 * kotlin委托属性+SharedPreference实例
 */
class Preference<T>(val context: Context,val name: String, private val default: T) {

    val prefs: SharedPreferences by lazy { context.getSharedPreferences(fileName, Context.MODE_PRIVATE) }


    companion object {
          const val fileName = "android_study"
//        private val prefs: SharedPreferences by lazy {  .getSharedPreferences(fileName, Context.MODE_PRIVATE) }

//        /**
//         * 删除全部数据
//         */
//        fun clearPreference() {
//            getPrefs().edit().clear().apply()
//        }
//
//        /**
//         * 根据key删除存储数据
//         */
//        fun clearPreference(key: String) {
//            prefs.edit().remove(key).apply()
//        }
//
//        /**
//         * 查询某个key是否已经存在
//         *
//         * @param key
//         * @return
//         */
//        fun contains(key: String): Boolean {
//            return prefs.contains(key)
//        }
//
//        /**
//         * 返回所有的键值对
//         *
//         * @param context
//         * @return
//         */
//        fun getAll(): Map<String, *> {
//            return prefs.all
//        }
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return getSharedPreferences(name, default)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putSharedPreferences(name, value)
    }

    @SuppressLint("CommitPrefEdits")
    private fun putSharedPreferences(name: String, value: T) = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("参数不支持")
        }.apply()
    }

    private fun getSharedPreferences(name: String, default: T): T = with(prefs) {
        val res: Any = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("参数不支持")
        }!!
        return res as T
    }



}
