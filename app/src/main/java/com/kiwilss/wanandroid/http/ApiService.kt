package com.kiwilss.wanandroid.http

import androidx.lifecycle.LiveData
import com.kiwilss.wanandroid.base.BaseBean
import com.kiwilss.wanandroid.base.ArticleBean
import com.kiwilss.wanandroid.ui.home.HomeArticalBean
import com.kiwilss.wanandroid.ui.testhttp.LoginInfo
import com.kiwilss.wanandroid.ui.testhttp.Testt
import retrofit2.http.*

/**
 *@FileName: ApiService
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/2/22
 * @desc   : {DESCRIPTION}
 */
interface ApiService {

    /**
     *  首页轮播图https://www.wanandroid.com/banner/json
     */
    @GET("banner/json")
    suspend fun getHomeBanner(): BaseBean<List<Testt>>

    @GET("banner/json")
    suspend fun getHomeBanner3(): BaseBean<List<com.kiwilss.wanandroid.ui.home.HomeBannerBean>>

    @GET("banner/json")
    suspend fun getHomeBanner2(): LiveData<BaseBean<List<Testt>>>

    /**
     * 获取置顶文章https://www.wanandroid.com/article/top/json
     */
    @GET("article/top/json")
    suspend fun getTopArticle(): BaseBean<List<ArticleBean>>
    /**
     * 获取首页文章
     * @param page ") page: Int
     * @return BaseBean<List<HomeBean>>
     */
    @GET("article/list/{page}/json")
    suspend fun getHomeArticles(@Path("page") page: Int): BaseBean<HomeArticalBean>



    /**
     * 登录
     * http://www.wanandroid.com/user/login
     * @param username
     * @param password
     */
    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): BaseBean<LoginInfo>


}