package com.kiwilss.wanandroid.ui.login

import com.kiwilss.wanandroid.base.BaseRepository
import com.kiwilss.wanandroid.http.RetrofitClient

/**
 * @author : Administrator
 * e-mail : kiwilss@163.com
 * time   : 2021/03/01
 *  desc   :
 */
class LoginRepository: BaseRepository() {

    suspend fun login(username: String, password: String) = apiCall { RetrofitClient.service.login(username, password) }

    suspend fun exit() = apiCall { RetrofitClient.service.logout() }

}