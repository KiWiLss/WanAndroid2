package com.kiwilss.wanandroid.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.blankj.utilcode.util.LogUtils
import com.kiwilss.wanandroid.R
import com.kiwilss.wanandroid.ktx.toastE
import retrofit2.HttpException
import java.util.concurrent.CancellationException

/**
 *@FileName: BaseVMFragment
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/2/23
 * @desc   : {DESCRIPTION}
 */
abstract class BaseVMFragment<VB : ViewBinding, VM : BaseViewModel> : BaseFragment<VB>() {

    protected lateinit var viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initVM()
        //请求网络失败处理
        handlerError()
        super.onViewCreated(view, savedInstanceState)
    }

    open fun handlerError() {
        viewModel.run {
            getError().observe(viewLifecycleOwner) {
                dismissLoadingDiloag()
                requestError(it)
            }
            getFinally().observe(viewLifecycleOwner) {
                requestFinally(it)
            }
        }
    }

    open fun requestError(it: Exception?) {
        LogUtils.e(it)
        it?.run {
            when (it) {
                //请求取消
                is CancellationException -> LogUtils.e("请求取消--$it")
                is HttpException -> {
                    if (it.code() == 504) {
                        toastE(R.string.network_exception)
                    } else {
                        toastE(it.message())
                    }
                }
                else -> {
                    toastE(it.message)
                }
            }
        }

    }

    open fun requestFinally(it: Int?) {

    }

    private fun initVM() {
        providerVMClass().let {
            viewModel = ViewModelProvider(this).get(it)
            viewModel.run {
                lifecycle.addObserver(this)
            }
        }
    }

    abstract fun providerVMClass(): Class<VM>
}