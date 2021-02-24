package com.kiwilss.wanandroid.ui.testhttp

import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.LogUtils
import com.kiwilss.wanandroid.base.BaseViewModel
import com.kiwilss.wanandroid.ktx.livedata.OnceLiveData
import com.kiwilss.wanandroid.ktx.livedata.SingleLiveEvent

/**
 *@FileName: TestHttpViewModel
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/2/24
 * @desc   : {DESCRIPTION}
 */
class TestHttpViewModel: BaseViewModel() {

    private val repository by lazy { TestHttpRepository() }

    private val homeBannerLive = OnceLiveData<List<Testt>?>()
    fun getHomeBanner(): OnceLiveData<List<Testt>?>{
        launchUI {
            val response = repository.getHomeBanner()
            homeBannerLive.setValue(response.data)
            LogUtils.e(Thread.currentThread())
        }
        return homeBannerLive
    }

    private val loginData = OnceLiveData<LoginInfo?>()
//    private val loginData2 = SingleLiveEvent<LoginInfo?>()
    fun login(username: String,pwd: String): OnceLiveData<LoginInfo?>{
        launchUI {
//            val response = repository.login(username, pwd)
            loginData.setValue(repository.login(username, pwd).data)
            //loginData2.value = repository.login(username, pwd).data
        }
        return loginData
    }

}