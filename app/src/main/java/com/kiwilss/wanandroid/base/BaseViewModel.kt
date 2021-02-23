package com.kiwilss.wanandroid.base

import androidx.lifecycle.*
import com.kiwilss.wanandroid.config.HttpConstant
import com.kiwilss.wanandroid.ktx.livedata.OnceLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

/**
 *@FileName: BaseViewModel
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/2/22
 * @desc   : {DESCRIPTION}
 */
open class BaseViewModel : ViewModel(), LifecycleObserver {

    private val error by lazy { OnceLiveData<Exception>() }

    private val finally by lazy { OnceLiveData<Int>() }

    //运行在UI线程的协程
    fun launchUI(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch {
        try {
            withTimeout(HttpConstant.DEFAULT_TIMEOUT * 1000) {
                block()
            }
        } catch (e: Exception) {
            //此处接收到BaseRepository里的request抛出的异常，直接赋值给error
            error.setValue(e)
        } finally {
            finally.setValue(200)
        }
    }





    /**
     * 请求失败，出现异常
     */
    fun getError(): LiveData<Exception> = error

    /**
     * 请求完成，在此处做一些关闭操作
     */
    fun getFinally(): LiveData<Int> = finally


}