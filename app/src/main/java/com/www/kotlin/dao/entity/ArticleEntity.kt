package com.www.kotlin.dao.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Article")
data class ArticleEntity(
     var apkLink: String? = null,
     var author: String? = null,
     var chapterId: Int = 0,
     var chapterName: String? = null,
     var collect: Boolean = false,
     var courseId: Int = 0,
     var desc: String? = null,
     var envelopePic: String? = null,
     var fresh: Boolean = false,
     @PrimaryKey
     var id: Int = 0,
     var link: String? = null,
     var niceDate: String? = null,
     var origin: String? = null,
     var projectLink: String? = null,
     var publishTime: Long = 0,
     var superChapterId: Int = 0,
     var superChapterName: String? = null,
     var title: String? = null,
     var type: Int = 0,
     var userId: Int = 0,
     var visible: Int = 0,
     var zan: Int = 0,
     @Ignore
     var tags: List<TagEntity>? = null,
     var typeSize: Int = 0,
     var typePos: Int = 0
) : Parcelable