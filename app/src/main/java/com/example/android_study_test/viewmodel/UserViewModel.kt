package com.example.android_study_test.viewmodel

import androidx.lifecycle.ViewModel
import com.example.android_study_test.db.dao.UserDao
import com.example.android_study_test.db.domain.User
import io.reactivex.Completable
import io.reactivex.Flowable

class UserViewModel(private val userDao: UserDao) : ViewModel() {

    fun userName(): Flowable<String> {
        return userDao.getUserById(USER_ID).map { user -> user.userName }
    }

    fun updateUserName(username: String): Completable {
        val user = User(USER_ID, username)
        return userDao.insertUser(user)
    }

    companion object {
        // using a hardcoded value for simplicity
        const val USER_ID = "1"
    }
}