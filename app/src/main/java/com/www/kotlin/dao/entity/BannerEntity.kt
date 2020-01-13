package com.www.kotlin.dao.entity;

import android.util.SparseArray
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arvinljw on 2018/11/16 17:00
 * Function：
 *      * desc : 一起来做个App吧
 *      * id : 10
 *      * imagePath : http://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png
 *      * isVisible : 1
 *      * order : 3
 *      * title : 一起来做个App吧
 *      * type : 0
 *      * url : http://www.wanandroid.com/blog/show/2
 * Desc：
 */
data class BannerEntity (
  var desc:String,
  var id:Int,
  var imagePath:String,
  var isVisible:Int,
  var order:Int,
  var title:String,
  var type:Int,
  var url:String
){
 companion object{
       fun  toImages( banners:kotlin.collections.List<BannerEntity>):MutableList<String>{
        var images = mutableListOf<String>()
         for ( banner in banners) {
             images.add(banner.imagePath)
         }
         return images;
     }
 }


}
