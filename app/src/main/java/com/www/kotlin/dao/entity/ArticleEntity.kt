package com.www.kotlin.dao.entity

import android.os.Parcelable
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticleEntity(
    private var apkLink: String? = null,
    private var author: String? = null,
    private var chapterId: Int = 0,
    private var chapterName: String? = null,
    private var collect: Boolean = false,
    private var courseId: Int = 0,
    private var desc: String? = null,
    private var envelopePic: String? = null,
    private var fresh: Boolean = false,
    @PrimaryKey
    private var id: Int = 0,
    private var link: String? = null,
    private var niceDate: String? = null,
    private var origin: String? = null,
    private var projectLink: String? = null,
    private var publishTime: Long = 0,
    private var superChapterId: Int = 0,
    private var superChapterName: String? = null,
    private var title: String? = null,
    private var type: Int = 0,
    private var userId: Int = 0,
    private var visible: Int = 0,
    private var zan: Int = 0,
    @Ignore
    private var tags: List<TagEntity>? = null,
    private var typeSize: Int = 0,
    private var typePos: Int = 0
) : Parcelable