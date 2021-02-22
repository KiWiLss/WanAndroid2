package com.kiwilss.wanandroid.app

import android.content.Context
import androidx.multidex.BuildConfig
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.coder.zzq.smartshow.core.SmartShow
import com.dylanc.loadinghelper.LoadingHelper
import com.dylanc.loadinghelper.ViewType
import com.kiwilss.wanandroid.config.Constant
import com.kiwilss.wanandroid.ktx.AndroidKTX
import com.kiwilss.wanandroid.statuslayout.EmptyAdapter
import com.kiwilss.wanandroid.statuslayout.LoadingAdapter
import com.rousetime.android_startup.AndroidStartup

/**
 *@FileName: FirstStartup
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/2/21
 * @desc   : {DESCRIPTION}
 */
class FirstStartup: AndroidStartup<String>() {
    override fun callCreateOnMainThread(): Boolean = false

    override fun create(context: Context): String? {
        AndroidKTX.init(context)
        Utils.init(MyApp.instance)
        LogUtils.getConfig().setLogSwitch(Constant.isShowLog).globalTag = "MMM"
        //toast,topbar,snackbar初始化
        SmartShow.init(MyApp.instance)
        MultiDex.install(context)
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(MyApp.instance) // 尽可能早，推荐在Application中初始化
        initStatusLayout()
        return this.javaClass.simpleName
    }

    private fun initStatusLayout() {
        val adapterPool: LoadingHelper.AdapterPool.() -> Unit = {
            register(ViewType.LOADING, LoadingAdapter())
            register(ViewType.EMPTY, EmptyAdapter())
        }
        LoadingHelper.setDefaultAdapterPool(adapterPool)
    }

    override fun waitOnMainThread(): Boolean = false
}