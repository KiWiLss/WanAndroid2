package com.kiwilss.wanandroid.ui.home

import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.LogUtils
import com.kiwilss.wanandroid.base.BaseVMFragment
import com.kiwilss.wanandroid.databinding.FragmentHomeBinding

/**
 *@FileName: HomeFragment
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/2/25
 * @desc   : {DESCRIPTION}
 */
class HomeFragment: BaseVMFragment<FragmentHomeBinding,HomeViewModel>() {

    var isFirstLoad = true

    override fun providerVMClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun initData() {
    }

    override fun initEvent() {
    }

    override fun initInterface(view: View, savedInstanceState: Bundle?) {


    }

    override fun onResume() {
        super.onResume()
        if (isFirstLoad){
            isFirstLoad = false
            initLoadData()
        }
    }

    /**
     * 懒加载数据使用
    */
    private fun initLoadData() {

    }
}