package com.example.android_study_test.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android_study_test.db.dao.UserDao
import com.example.android_study_test.db.domain.User

@Database(entities = arrayOf(User::class),version = 1)
abstract class UserDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao

    companion object {
            private var instant:UserDatabase?=null
            fun  getInstant(context: Context):UserDatabase= instant ?: synchronized(this){
                instant?:BuildDatebase(context).also { instant=it }
            }
        private fun BuildDatebase(context: Context):UserDatabase=
        Room.databaseBuilder(context.applicationContext,UserDatabase::class.java,"test.db").build()
    }
}