package com.www.kotlin.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.www.kotlin.dao.dao.ArticleDao
import com.www.kotlin.dao.entity.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    companion object {
        private val APP_DATABASE:String="kotlin_study_db"
        @Volatile
        private var Instant: AppDatabase? = null

        fun getInstant(context: Context): AppDatabase =
            Instant ?: synchronized(this) {
                Instant
                    ?: buildDatabase(context).also { Instant = it }
            }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                APP_DATABASE
            )
//                .addMigrations(MIGRATION_1_2)
                .build()
        }


//        private val MIGRATION_1_2: Migration=(object:Migration(1,2){
//            override fun migrate(database: SupportSQLiteDatabase) {
////             database.execSQL("sql")
//            }
//        })

    }

}