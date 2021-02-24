package com.kiwilss.wanandroid.base

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.NetworkUtils
import com.kiwilss.wanandroid.R
import com.kiwilss.wanandroid.ktx.toast
import com.kiwilss.wanandroid.ktx.toastE
import retrofit2.HttpException
import java.util.concurrent.CancellationException

/**
 *@FileName: BaseVMActivity
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/2/22
 * @desc   : {DESCRIPTION}
 */
abstract class BaseVMActivity<VB: ViewBinding,VM: BaseViewModel>: BaseActivity<VB>() {

    protected lateinit  var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        //初始化 ViewModel
        initVM()
        //处理请求异常
        handlerError()
        super.onCreate(savedInstanceState)
    }

    open fun handlerError() {
        viewModel.run {
            getError().observe(this@BaseVMActivity){
                dismissLoadingDiloag()
                requestError(it)
            }
            getFinally().observe(this@BaseVMActivity){
                requestFinally(it)
            }
        }
    }

    open fun requestError(it: Exception?) {
        LogUtils.e(it)
        if (!NetworkUtils.isConnected()) {
            toastE(R.string.network_exception)
        }else{
            it?.run {
                when(it){
                    //请求取消
                    is CancellationException -> LogUtils.e("请求取消--$it")
                    is HttpException -> {
                        if (it.code() == 504 ){
                            toastE(R.string.network_exception)
                        }else{
                            toastE(it.message())
                        }
                    }
                    else -> {
                        toastE(it.message)
                    }
                }
            }
        }
    }

    open fun requestFinally(it: Int?) {

    }

    abstract fun providerVMClass(): Class<VM>

    private fun initVM() {
        providerVMClass().let {
            viewModel = ViewModelProvider(this).get(it)
            //mViewModel = ViewModelProviders.of(this).get(it)
            viewModel.let(lifecycle::addObserver)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.let {
            lifecycle.removeObserver(it)
        }
    }
}