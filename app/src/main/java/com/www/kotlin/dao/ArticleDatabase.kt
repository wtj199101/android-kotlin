package com.www.kotlin.dao

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = ArticleDao)
abstract class ArticleDatabase : RoomDatabase(){

}