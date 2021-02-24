package com.kiwilss.wanandroid.app

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.coder.zzq.smartshow.core.SmartShow
import com.kiwilss.wanandroid.config.Constant
import com.kiwilss.wanandroid.help.ActivityManager
import com.kiwilss.wanandroid.ktx.AndroidKTX
import com.rousetime.android_startup.StartupManager

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
        StartupManager.Builder()
            .addStartup(FirstStartup())
            .build(this)
            .start()
            .await()

//        SmartShow.init(this)

        //对Activity生命周期监听
        monitorActivityLife()
    }

    private fun monitorActivityLife() {
        registerActivityLifecycleCallbacks(object :Application.ActivityLifecycleCallbacks{
            override fun onActivityCreated(p0: Activity, p1: Bundle?) {
                ActivityManager.instance.addActivity(p0)
            }

            override fun onActivityStarted(p0: Activity) {
            }

            override fun onActivityResumed(p0: Activity) {
            }

            override fun onActivityPaused(p0: Activity) {
            }

            override fun onActivityStopped(p0: Activity) {
            }

            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
            }

            override fun onActivityDestroyed(p0: Activity) {
                ActivityManager.instance.removeActivity(p0)
            }

        })
    }

    override fun onTerminate() {
        super.onTerminate()
        ARouter.getInstance().destroy()
    }

    companion object {
        lateinit var context: Context
        lateinit var instance: MyApp
    }
}