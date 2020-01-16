package com.www.kotlin.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.www.kotlin.R
@GlideModule
class AppNameGlideModule:AppGlideModule(){
      override fun applyOptions(context: Context, builder: GlideBuilder) {
            super.applyOptions(context, builder)
            builder.apply { RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).signature(
                  ObjectKey(System.currentTimeMillis().toShort())
            ) }
      }
}

object ImageLoadUtils{
      private val DEFAULT_ERROR_RES_ID = R.drawable.image_error
      private val DEFAULT_PLACE_RES_ID = R.drawable.image_place

      var isNoImage = false
      fun loadImage(context: Context, url: String, imageView: ImageView, vararg resIds: Int) {
            load(context, url, imageView, false, 0, *resIds)
      }

      fun displayImage(context: Context, path: String, imageView: ImageView, vararg resIds: Int){
            load(context, path, imageView, false, 0)
      }
      /**
       * @param isCircle 是否加载为圆形
       * @param cornerPx 在isCircle为false时：
       * cornerPx需要大于0表示加载为圆角图片
       * 小于等于0表示直接加载图片centerCrop
       * @param resIds   在图片加载中和加载失败的占用资源
       * 传入值为null，会使用默认的占用图资源和失败图资源
       * 传入值为一个资源时，会将占用图资源和失败图资源都设置为该资源
       * 传入值为两个资源时，第一个是占用图资源，第二个是失败图资源
       * 传入值为超过两个资源时，只会使用前两个
       */
      fun load(
            context: Context,
            url: Any,
            imageView: ImageView,
            isCircle: Boolean,
            cornerPx: Int,
            vararg resIds: Int
      ) {
            val ids = getResIds(*resIds)
            if (isNoImage) {
                  imageView.setImageResource(ids[0])
                  return
            }
            val options = RequestOptions().placeholder(ids[0]).error(ids[1])
            Glide.with(context)
                  .load(dealUrl(url))
                  .apply(
                        if (isCircle)
                              options.circleCrop()
                        else
                              if (cornerPx > 0)
                                    options.transform(RoundedCorners(cornerPx))
                              else
                                    options.centerCrop()
                  )
                  .into(imageView)
      }
      private fun dealUrl(url: Any?): Any {
            var url = url
            if (url == null) {
                  url = ""
            }
            return url
      }

      private fun getResIds(vararg resIds: Int): IntArray {
            return if (resIds.isEmpty()) {
                  intArrayOf(DEFAULT_PLACE_RES_ID, DEFAULT_ERROR_RES_ID)
            } else intArrayOf(resIds[0], if (resIds.size >= 2) resIds[1] else resIds[0])
      }
}

