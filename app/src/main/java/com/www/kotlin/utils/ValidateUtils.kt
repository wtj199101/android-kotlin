package com.www.kotlin.utils

import android.widget.EditText

object ValidateUtils {

    /**
     * false 参数错误
     * true 通过
     */
    fun validateEditText(editText: EditText,msg:String="参数错误"):Boolean{
        var value=editText.text
        if(value==null||value.isEmpty()){
            editText.error=msg
            return false
        }
       return true

    }
}