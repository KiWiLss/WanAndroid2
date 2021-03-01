package com.kiwilss.wanandroid.ui.login

import com.kiwilss.wanandroid.base.BaseViewModel
import com.kiwilss.wanandroid.ktx.core.logi
import com.kiwilss.wanandroid.ktx.livedata.OnceLiveData
import com.kiwilss.wanandroid.ui.testhttp.LoginInfo

/**
 * @author : Administrator
 * e-mail : kiwilss@163.com
 * time   : 2021/03/01
 *  desc   :
 */
class LoginViewModel: BaseViewModel() {
    val repository by lazy { LoginRepository() }

    val login = OnceLiveData<LoginInfo?>()
    fun login(username: String, password: String): OnceLiveData<LoginInfo?>{
        launchUI {
            login.setValue(repository.login(username, password).data)
        }
        return login
    }
    val exit = OnceLiveData<Any?>()
    fun exit(): OnceLiveData<Any?>{
        launchUI {
            exit.setValue(repository.exit().data)
        }
        return exit
    }

}