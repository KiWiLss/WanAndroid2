package com.kiwilss.wanandroid.ui.home

import com.kiwilss.wanandroid.base.ArticleBean
import com.kiwilss.wanandroid.base.BaseViewModel
import com.kiwilss.wanandroid.ktx.livedata.OnceLiveData

/**
 *@FileName: HomeViewModel
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/2/25
 * @desc   : {DESCRIPTION}
 */
class HomeViewModel: BaseViewModel() {

    private val repository by lazy { HomeRepository() }

    private val homeBanner = OnceLiveData<List<HomeBannerBean>?>()
    fun getHomeBanner(): OnceLiveData<List<HomeBannerBean>?>{
        launchUI {
            homeBanner.setValue(repository.getHomeBanner().data)
        }
        return homeBanner
    }
    private val topArticle = OnceLiveData<List<ArticleBean>?>()
    fun getTopArticle(): OnceLiveData<List<ArticleBean>?>{
        launchUI {
            topArticle.setValue(repository.getTopArticle().data)
        }
        return topArticle
    }

    val articleBean = OnceLiveData<HomeArticalBean?>()
    fun getArticle(page: Int): OnceLiveData<HomeArticalBean?>{
        launchUI {
            articleBean.setValue(repository.getHomeArticle(page).data)
        }
        return articleBean
    }

}