package com.www.kotlin.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.www.kotlin.retrofit.service.MainRetrofitService
import javax.inject.Inject

class HomeViewModel @Inject constructor(var homeService: MainRetrofitService) :
    ViewModel() {

    fun getBanners() = homeService.getBanners()

    fun getIndexArticles(currPage: Int)=homeService.getIndexArticles(currPage)
    fun getKnowledgeTree() =homeService.getKnowledgeTree()


}
