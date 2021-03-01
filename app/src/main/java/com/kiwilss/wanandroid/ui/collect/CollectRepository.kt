package com.kiwilss.wanandroid.ui.collect

import com.kiwilss.wanandroid.base.BaseRepository
import com.kiwilss.wanandroid.http.RetrofitClient

/**
 * @author : Administrator
 * e-mail : kiwilss@163.com
 * time   : 2021/03/01
 *  desc   :
 */
class CollectRepository: BaseRepository() {

    suspend fun getCollectList(page: Int) = apiCall { RetrofitClient.service.getCollectList(page) }
}