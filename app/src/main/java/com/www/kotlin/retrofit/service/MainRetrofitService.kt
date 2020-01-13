package com.www.kotlin.retrofit.service

import androidx.lifecycle.LiveData
import com.github.leonardoxh.livedatacalladapter.Resource
import com.www.kotlin.dao.entity.*
import com.www.kotlin.retrofit.response.Response
import retrofit2.http.*

interface MainRetrofitService{

    /******* 首页 ********/
    @GET("article/list/{page}/json")
     fun getIndexArticles(@Path("page") page: Int): LiveData<Resource<Response<PageList<ArticleEntity>>>>

    @GET("banner/json")
     fun getBanners(): LiveData<Resource<Response<List<BannerEntity>>>>

    @GET("hotkey/json")
     fun getHotKeys(): LiveData<Resource<Response<List<HotKeyEntity>>>>

    @POST("article/query/{page}/json")
    @FormUrlEncoded
     fun getSearchArticles(@Path("page") page: Int, @Field("k") k: String): LiveData<Resource<Response<PageList<ArticleEntity>>>>

/*=======知识体系======*/

    @GET("tree/json")
     fun getKnowledgeTree(): LiveData<Resource<Response<List<TreeEntity>>>>

    @GET("article/list/{page}/json")
     fun getKnowledgeTreeArticles(@Path("page") page: Int, @Query("cid") cid: Int): LiveData<Resource<Response<PageList<ArticleEntity>>>>

    /*=======导航======*/
    @GET("navi/json")
    abstract fun getNavigationInfo(): LiveData<Resource<Response<List<NavigationInfoEntity>>>>

    /*=======项目======*/
    @GET("project/tree/json")
     fun getProjects(): LiveData<Resource<Response<List<TreeEntity>>>>

    @GET("project/list/{page}/json")
     fun getProjectArticles(@Path("page") page: Int, @Query("cid") cid: Int): LiveData<Resource<Response<PageList<ArticleEntity>>>>

    /*=======公众号======*/
    @GET("wxarticle/chapters/json")
    abstract fun getPublicNumber(): LiveData<Resource<Response<List<PublicNumberEntity>>>>

    /*=======登陆注册======*/
    @POST("user/login")
    @FormUrlEncoded
     fun login(@Field("username") username: String, @Field("password") password: String): LiveData<Resource<Response<LoginResultEntity>>>

    @POST("user/register")
    @FormUrlEncoded
     fun register(
        @Field("username") username: String, @Field("password") password: String, @Field(
            "repassword"
        ) repassword: String
    ): LiveData<Resource<Response<LoginResultEntity>>>

    @GET("user/logout/json")
     fun logout(): LiveData<Resource<Response<LoginResultEntity>>>

    /*=======收藏======*/
    @GET("lg/collect/list/{page}/json")
     fun getCollectionArticles(@Path("page") page: Int): LiveData<Resource<Response<PageList<ArticleEntity>>>>

    @POST("lg/collect/{id}/json")
     fun collectArticle(@Path("id") id: Int): LiveData<Resource<Response<PageList<ArticleEntity>>>>

    @POST("lg/collect/add/json")
    @FormUrlEncoded
     fun collectOriginArticle(
        @Field("title") title: String, @Field("author") author: String, @Field(
            "link"
        ) link: String
    ): LiveData<Resource<Response<PageList<ArticleEntity>>>>

    /**
     * 取消收藏页面站内文章
     */
    @POST("lg/uncollect_originId/{id}/json")
    @FormUrlEncoded
     fun cancelCollectOriginArticle(@Path("id") id: Int, @Field("originId") originId: Int): LiveData<Resource<Response<PageList<ArticleEntity>>>>
}