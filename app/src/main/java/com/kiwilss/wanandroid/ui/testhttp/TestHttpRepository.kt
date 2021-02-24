package com.kiwilss.wanandroid.ui.testhttp

import com.kiwilss.wanandroid.base.BaseBean
import com.kiwilss.wanandroid.base.BaseRepository
import com.kiwilss.wanandroid.http.RetrofitClient

/**
 *@FileName: TestRepository
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/2/24
 * @desc   : {DESCRIPTION}
 */
class TestHttpRepository: BaseRepository() {

    suspend fun getHomeBanner() : BaseBean<List<Testt>>
    = apiCall { RetrofitClient.service.getHomeBanner() }

    suspend fun login(username: String,pwd: String)
    = apiCall { RetrofitClient.service.login(username,pwd) }

}