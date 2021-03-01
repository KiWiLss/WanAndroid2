package com.kiwilss.wanandroid.ui.login

import android.os.Bundle
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.kiwilss.wanandroid.base.BaseVMActivity
import com.kiwilss.wanandroid.config.ArouterPage
import com.kiwilss.wanandroid.databinding.ActivityLoginBinding
import com.kiwilss.wanandroid.ktx.core.click
import com.kiwilss.wanandroid.ktx.toastE
import com.kiwilss.wanandroid.ktx.toastS

/**
 * @author : Administrator
 * e-mail : kiwilss@163.com
 * time   : 2021/03/01
 *  desc   :
 */

@Route(path = ArouterPage.LOGIN)
class LoginActivity : BaseVMActivity<ActivityLoginBinding, LoginViewModel>() {

    override fun providerVMClass(): Class<LoginViewModel> = LoginViewModel::class.java

    override fun initData() {

    }

    override fun initEvent() {
        binding.btnLogin.click {
            loginListener()
        }

        binding.btnExit.click {
            exitLogin()
        }
    }

    private fun exitLogin() {
        viewModel.exit().observe(this, Observer {
            toastS("退出成功")
        })
    }

    private fun loginListener() {
        if (binding.etAccount.text.toString().isEmpty()
            || binding.etPwd.text.toString().isEmpty()) {
            toastE("输入信息")
            return
        }
        viewModel.login(binding.etAccount.text.toString(),binding.etPwd.text.toString()).observe(this, Observer {
            toastS("登录成功")
        })
    }

    override fun initInterface(savedInstanceState: Bundle?) {

    }
}