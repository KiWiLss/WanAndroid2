package com.kiwilss.wanandroid.ui.splash

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.kiwilss.wanandroid.base.BaseActivity
import com.kiwilss.wanandroid.config.ArouterPage
import com.kiwilss.wanandroid.ktx.core.postDelay
import com.kiwilss.wanandroid.ktx.startActivity

/**
 *@FileName: SplashActivity
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/2/23
 * @desc   : {DESCRIPTION}
 */
class SplashActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        postDelay (2000){
            startActivity(ArouterPage.MAIN)
            finish()
        }

    }
}