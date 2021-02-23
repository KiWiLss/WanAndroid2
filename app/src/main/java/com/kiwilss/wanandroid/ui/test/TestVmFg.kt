package com.kiwilss.wanandroid.ui.test

import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import com.kiwilss.wanandroid.base.BaseVMFragment
import com.kiwilss.wanandroid.databinding.ActivityTestBinding
import com.kiwilss.wanandroid.viewModel.CommonViewModel

/**
 *@FileName: TestVmFg
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/2/23
 * @desc   : {DESCRIPTION}
 */
class TestVmFg: BaseVMFragment<ActivityTestBinding,CommonViewModel>() {

    override fun providerVMClass(): Class<CommonViewModel> = CommonViewModel::class.java

    override fun initData() {
    }

    override fun initEvent() {
    }

    override fun initInterface(view: View, savedInstanceState: Bundle?) {


    }

    override fun handlerError() {
        super.handlerError()
    }

    override fun requestError(it: Exception?) {
        super.requestError(it)
    }
}