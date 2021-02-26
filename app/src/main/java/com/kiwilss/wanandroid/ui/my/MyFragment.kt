package com.kiwilss.wanandroid.ui.my

import android.os.Bundle
import android.view.View
import com.kiwilss.wanandroid.base.BaseVMFragment
import com.kiwilss.wanandroid.databinding.FragmentMyBinding

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
    }

    override fun initInterface(view: View, savedInstanceState: Bundle?) {
    }
}