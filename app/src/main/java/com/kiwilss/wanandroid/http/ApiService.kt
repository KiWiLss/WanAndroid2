package com.kiwilss.wanandroid.http

import androidx.lifecycle.LiveData
import com.kiwilss.wanandroid.base.BaseBean
import com.kiwilss.wanandroid.ui.testhttp.HomeBanner
import com.kiwilss.wanandroid.ui.testhttp.LoginInfo
import com.kiwilss.wanandroid.ui.testhttp.Testt
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

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
    suspend fun getHomeBanner2(): LiveData<BaseBean<List<Testt>>>

    /**
     * 登录
     * http://www.wanandroid.com/user/login
     * @param username
     * @param password
     */
    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(@Field("username")username: String,
                      @Field("password")password: String): BaseBean<LoginInfo>


}