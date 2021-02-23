package com.kiwilss.wanandroid.viewModel

import com.kiwilss.wanandroid.base.BaseViewModel
import com.kiwilss.wanandroid.repository.CommonRepository

/**
 *@FileName: CommonViewModel
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/2/23
 * @desc   : {DESCRIPTION}
 */
class CommonViewModel: BaseViewModel() {
    private val repository by lazy { CommonRepository() }


    fun test(){
        launchUI {

        }
    }

}