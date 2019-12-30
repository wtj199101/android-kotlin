package com.example.android_study_test.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android_study_test.db.domain.User
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface UserDao{
    @Query("select * from users where user_id=:id")
    fun getUserById(id: String):Flowable<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User):Completable
}