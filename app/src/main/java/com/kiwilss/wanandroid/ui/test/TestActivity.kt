package com.kiwilss.wanandroid.ui.test

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.kiwilss.wanandroid.base.BaseActivity
import com.kiwilss.wanandroid.config.ArouterPage
import com.kiwilss.wanandroid.databinding.ActivityTestBinding

/**
 *@FileName: TestActivity
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/2/22
 * @desc   : {DESCRIPTION}
 */
@Route(path = ArouterPage.TEST)
class TestActivity: BaseActivity<ActivityTestBinding>() {
    override fun initData() {

    }

    override fun initEvent() {
    }

    override fun initInterface(savedInstanceState: Bundle?) {
    }
}