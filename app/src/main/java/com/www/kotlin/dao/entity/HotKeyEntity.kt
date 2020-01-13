package com.www.kotlin.dao.entity

/**
 * Created by arvinljw on 2018/11/16 17:02
 * Function：
 * Desc：
 * id : 6
 * link :
 * name : 面试
 * order : 1
 * visible : 1
 */
data class HotKeyEntity(
    var id: Int = 0,
    var link: String? = null,
    var name: String? = null,
    var order: Int = 0,
    var visible: Int = 0
)
