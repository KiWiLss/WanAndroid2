package com.kiwilss.wanandroid.ui.collect

import com.kiwilss.wanandroid.base.ArticleBean
import com.kiwilss.wanandroid.base.BaseBeanList
import com.kiwilss.wanandroid.base.BaseViewModel
import com.kiwilss.wanandroid.ktx.livedata.OnceLiveData

/**
 * @author : Administrator
 * e-mail : kiwilss@163.com
 * time   : 2021/03/01
 *  desc   :
 */
class CollectViewModel: BaseViewModel() {
    val repository by lazy { CollectRepository() }

    val collectList = OnceLiveData<BaseBeanList<ArticleBean>?>()
    fun getCollectList(page: Int): OnceLiveData<BaseBeanList<ArticleBean>?>{
        launchUI {
            collectList.setValue(repository.getCollectList(page).data)
        }
        return collectList
    }

}