package com.kiwilss.wanandroid

import android.app.Application
import android.content.Context

/**
 *@FileName: MyApp
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/2/20
 * @desc   : {DESCRIPTION}
 */
class MyApp : Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        context = this.applicationContext

        //使用 startup 初始化

    }

    companion object {
        lateinit var context: Context
        lateinit var instance: MyApp
    }
}