package com.kiwilss.wanandroid.ui.home

import com.kiwilss.wanandroid.base.BaseRepository
import com.kiwilss.wanandroid.http.RetrofitClient

/**
 *@FileName: HomeRepository
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/2/25
 * @desc   : {DESCRIPTION}
 */
class HomeRepository: BaseRepository() {

    suspend fun getHomeBanner() = apiCall { RetrofitClient.service.getHomeBanner3() }

    suspend fun getTopArticle() = apiCall { RetrofitClient.service.getTopArticle() }

    suspend fun getHomeArticle(page: Int)
            = apiCall { RetrofitClient.service.getHomeArticles(page) }



}