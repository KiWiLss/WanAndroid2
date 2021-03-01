package com.kiwilss.wanandroid.ui.my

import android.os.Bundle
import android.view.View
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.jeremyliao.liveeventbus.LiveEventBus
import com.kiwilss.wanandroid.base.BaseVMFragment
import com.kiwilss.wanandroid.config.ArouterPage
import com.kiwilss.wanandroid.config.EventConstant
import com.kiwilss.wanandroid.databinding.FragmentMyBinding
import com.kiwilss.wanandroid.ktx.core.click
import com.kiwilss.wanandroid.ktx.startActivity

/**
 *@FileName: MyFragment
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/2/26
 * @desc   : {DESCRIPTION}
 */
class MyFragment: BaseVMFragment<FragmentMyBinding,MyViewModel>() {
    override fun providerVMClass(): Class<MyViewModel> = MyViewModel::class.java

    override fun initData() {
    }

    override fun initEvent() {
        binding.btnLogin.click {
            startActivity(ArouterPage.LOGIN)
        }
        binding.btnCollect.click {
            startActivity(ArouterPage.COLLECT)
        }

    }

    override fun initInterface(view: View, savedInstanceState: Bundle?) {


    }
}

