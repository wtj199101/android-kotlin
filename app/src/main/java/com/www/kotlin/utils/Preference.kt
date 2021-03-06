package com.www.kotlin.utils

import android.content.Context
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * sharePreference 工具类
 */
class Preference<T>(private val  context: Context,private val name: String,private val default: T):ReadWriteProperty<Any?,T>{

   private val pref: SharedPreferences by lazy { context.getSharedPreferences("default",Context.MODE_PRIVATE) }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return findPreference(name,default)
    }
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
              putPreference(name,value)
    }
    @Suppress("UNCHECKED_CAST")
    private fun findPreference(name: String, default: T): T = with(pref) {
        val res:Any = when(default){
            is Long -> getLong(name,default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }
      return res as T
    }
    private fun putPreference(name: String, value: T) = with(pref.edit()){
        when(value){
            is Long -> putLong(name,value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }.apply()
    }

}