package com.example.android_study_test

import com.example.android_study_test.db.dao.UserDao
import com.example.android_study_test.db.domain.User
import com.example.android_study_test.viewmodel.UserViewModel
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


class UserViewModelTest {


    @Mock
    private lateinit var datasource:UserDao

    private lateinit var viewMode: UserViewModel

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        viewMode= UserViewModel(datasource)
    }

    @Test
    fun getUserName_whenNoUserSaved(){
    `when` (datasource.getUserById(UserViewModel.USER_ID)).thenReturn(Flowable.empty())

    viewMode.userName().test().assertNoValues()
    }
    @Test
    fun getUserName_whenUserSaved(){
        val user=User(userName = "useName11")
        `when`(datasource.getUserById(UserViewModel.USER_ID)).thenReturn(Flowable.just(user))
        viewMode.userName().test().assertValue("useName11")
    }

    @Test
    fun updateUserName_updatesNameInDataSource(){
        datasource.insertUser(User(UserViewModel.USER_ID,"userName22"))
        val userName="new User name"
        val newUser=User(UserViewModel.USER_ID,userName)
        `when`(datasource.insertUser(newUser)).thenReturn(Completable.complete())
        viewMode.updateUserName(userName).test().assertComplete()


    }



}